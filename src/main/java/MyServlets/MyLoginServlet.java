package org.example;

import org.example.Repository_Pattern.Models.AuthenticationRequest;
import org.example.Repository_Pattern.Repositorys.UserRepository;
import org.example.Repository_Pattern.Services.UserService;
import org.example.Repository_Pattern.Services.UserServiceImpl;
import org.example.Repository_Pattern.implementations.JDBCUserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class MyLoginServlet extends HttpServlet {


    private UserRepository userRepository=new JDBCUserRepository();
    private final UserService userService = new UserServiceImpl(userRepository); // Instantiate UserService

    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/views/welcome2.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the user type, email, and password from request parameters
        String userType = request.getParameter("userType");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Create an AuthenticationRequest object
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(userType, email, password);
        System.out.println(authenticationRequest);
        // Call the authenticate method of the UserService
        boolean isAuthenticated = userService.authenticate(authenticationRequest);

        // Redirect the user based on authentication result
        if (isAuthenticated) {
            // Retrieve or create a session
            HttpSession session = request.getSession(true);

            // Set session attributes
            session.setAttribute("userType", userType);
            session.setAttribute("email", email);
            session.setAttribute("password", password);

            switch (userType) {
                case "student":
                    response.sendRedirect("student.jsp");
                    break;
                case "teacher":
                    response.sendRedirect("teacher.jsp");
                    break;
                case "admin":
                    response.sendRedirect("admin.jsp");
                    break;
                default:
                    response.sendRedirect("error.jsp");
                    break;
            }
        } else {
            response.sendRedirect("error.jsp"); // Redirect to error page if authentication fails
        }
    }
}
