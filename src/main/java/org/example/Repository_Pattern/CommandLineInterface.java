package Repository_Pattern;

import Repository_Pattern.Models.AuthenticationRequest;
import Repository_Pattern.Models.Request;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class CommandLineInterface {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8000;
    private static final Scanner scanner = new Scanner(System.in);
    private static ObjectOutputStream outputStream;
    private static ObjectInputStream inputStream;
    private static Socket socket;

    private static void initializeSocket() {
        try {
            // Connect to the server
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("Error: Failed to connect to the server.");
            e.printStackTrace();
        }
    }
    private static void closeSocket() {
        try {
            // Close the socket
            if (socket != null && !socket.isClosed()) {
                outputStream.close();
                inputStream.close();
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("Error: Failed to close the socket.");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try {
            initializeSocket();

            displayUserTypeSelectionMenu();

            closeSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void displayUserTypeSelectionMenu() throws IOException {
        System.out.println("Welcome to School Management System");
        System.out.println("Select User Type:");
        System.out.println("1. Student");
        System.out.println("2. Teacher");
        System.out.println("3. Admin");
        System.out.print("Enter your choice: ");
        int userTypeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        String userType = getUserType(userTypeChoice);
        if (userType.equals("not a type")){
            displayUserTypeSelectionMenu();
            return;
        }
        authenticateUser(userType);
    }

    private static void authenticateUser(String userTypeChoice) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            // Construct authentication request
            Request authRequest = new Request("AUTHENTICATE",
                    new AuthenticationRequest(userTypeChoice, username, password));
            outputStream.writeObject(authRequest);

            // Receive authentication response
            boolean isAuthenticated = (boolean) inputStream.readObject();
            if (isAuthenticated) {
                displayMenuForUserType(userTypeChoice);
            } else {
                System.out.println("Invalid username or password. Please try again.");
                closeSocket();
                initializeSocket();
                displayUserTypeSelectionMenu();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String getUserType(int userTypeChoice) {
        switch (userTypeChoice) {
            case 1:
                return "Student";
            case 2:
                return "Teacher";
            case 3:
                return "Admin";
            default:
                System.out.println("Invalid user type.");
                return "not a type";
        }
    }

    private static void displayMenuForUserType(String userTypeChoice) throws IOException {
        switch (userTypeChoice) {
            case "Student":
                displayStudentMenu();
                break;
            case "Teacher":
                displayTeacherMenu();
                break;
            case "Admin":
                displayAdminMenu();
                break;
            default:
                System.out.println("Invalid user type.");
                displayUserTypeSelectionMenu();
        }
    }

    private static void displayStudentMenu() {
        try {
            while (true){
            // Display student menu options
            System.out.println("Student Menu Options:");
            System.out.println("1. View Courses");
            System.out.println("2. Get max mark in the first course");
            System.out.println("3. View Marks");
            System.out.println("4. Exit");

            // Get user input for menu selection
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            // Construct a request object for the selected menu option
            String requestType;

                switch (choice) {
                    case 1:
                        requestType = "VIEW_COURSES";
                        break;
                    case 2:
                        requestType = "Get_Max";
                        break;
                    case 3:
                        requestType = "VIEW_MARKS";
                        break;
                    case 4:
                        requestType = "EXIT";
                        System.out.println("left the Student Display Menu");
                        outputStream.writeObject(new Request(requestType, null));
                        closeSocket();
                        initializeSocket();
                        displayUserTypeSelectionMenu();
                        return;
                    default:
                        System.out.println("Invalid choice.");
                        displayUserTypeSelectionMenu();
                        return;
                }
                Request selectedOptionRequest = new Request(requestType, null);

                // Send the selected option request to the server
                outputStream.writeObject(selectedOptionRequest);
                String isAuthenticated = (String) inputStream.readObject();
                System.out.println(isAuthenticated);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void displayTeacherMenu() {
        // Display teacher menu options
        System.out.println("Display teacher menu options");
    }

    private static void displayAdminMenu() {
        // Display admin menu options
        System.out.println("Display admin menu options");
    }
}
