package Repository_Pattern.Server;

import Repository_Pattern.Repositorys.CourseRepository;
import Repository_Pattern.Repositorys.StudentRepository;
import Repository_Pattern.Repositorys.UserRepository;
import Repository_Pattern.Services.StudentService;
import Repository_Pattern.Services.StudentServiceImpl;
import Repository_Pattern.Services.UserService;
import Repository_Pattern.Services.UserServiceImpl;
import Repository_Pattern.implementations.JDBCCourseRepository;
import Repository_Pattern.implementations.JDBCStudentRepository;
import Repository_Pattern.implementations.JDBCUserRepository;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT = 8000;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        UserRepository JDBCUserRepository = new JDBCUserRepository();
        UserService UserServiceImpl = new UserServiceImpl(JDBCUserRepository);
        StudentRepository studentRepository = new JDBCStudentRepository();
        CourseRepository courseRepository = new JDBCCourseRepository();
        StudentService studentService = new StudentServiceImpl( studentRepository,  courseRepository);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Listening on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());


                ClientHandler clientHandler = new ClientHandler(clientSocket, UserServiceImpl, studentService);
                executorService.execute(clientHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
