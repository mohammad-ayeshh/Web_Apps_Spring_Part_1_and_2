package Repository_Pattern.Server;

import Repository_Pattern.Models.AuthenticationRequest;
import Repository_Pattern.Models.Course;
import Repository_Pattern.Models.Request;
import Repository_Pattern.Services.StudentService;
import Repository_Pattern.Services.UserService;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private final UserService userService;
    private final StudentService studentService;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    AuthenticationRequest authenticationRequest;

    public ClientHandler(Socket clientSocket, UserService userService, StudentService studentService) {
        this.clientSocket = clientSocket;
        this.userService = userService;
        this.studentService = studentService;
        try {
            inputStream = new ObjectInputStream(this.clientSocket.getInputStream());
            outputStream = new ObjectOutputStream(this.clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            // Receive and process user authentication request
            Request authRequest = (Request) inputStream.readObject();
            System.out.print("server serseved =");
            System.out.println(authRequest);
            this.authenticationRequest = (AuthenticationRequest) authRequest.getRequestData();
            authenticationRequest.getEmail();
            System.out.println("sending back the isAuthenticated");
            boolean isAuthenticated = userService.authenticate(authenticationRequest);

            // Send authentication result to the client
            outputStream.writeObject(isAuthenticated);
            if (isAuthenticated == false) {
                System.out.println("client Socket close");
                clientSocket.close();
                return;
            }

            // If authentication successful, proceed to handle user option
            System.out.println("user is HIM");
            menuProcess(isAuthenticated);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("no more data in the inputStream");
            e.printStackTrace();
        }
    }

    private void menuProcess(boolean isAuthenticated) throws IOException, ClassNotFoundException {
        if (isAuthenticated) {
            while (true) {
                String email = authenticationRequest.getEmail();
                String password = authenticationRequest.getPassword();
                int studentIdByEmailAndPassword = studentService.getStudentIdByEmailAndPassword(email, password);

                Request optionRequest = (Request) inputStream.readObject();
                String option = optionRequest.getRequestType();
                System.out.println("the option is " + option);
                // Process the user option
                String response = "";
                switch (option) {
                    case "VIEW_COURSES":
                        List<Course> coursesForStudent = studentService.getCoursesForStudent(studentIdByEmailAndPassword);
                        response = coursesForStudent.toString();
                        break;
                    case "Get_Max":
                        int maxInCourse = studentService.getMaxInCourse("1");
                        response += maxInCourse;
                        break;
                    case "VIEW_MARKS":
                        Map<Course, Integer> marksForStudent = studentService.getMarksForStudent(studentIdByEmailAndPassword);
                        String string = marksForStudent.toString();
                        response = string;
                        break;
                    case "EXIT":
                        response = "get OUUUTTTT";
                        System.out.println("client Socket close");
                        clientSocket.close();
                        return;
                    default:
                        // Handle invalid option
                        response = "Invalid option";
                        break;
                }

                // Send response to the client
                outputStream.writeObject(response);
            }
        } else {
            System.out.println("user is not HIM");
        }
    }

}
