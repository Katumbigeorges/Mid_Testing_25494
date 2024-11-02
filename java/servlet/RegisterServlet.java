package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.UserController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enums.EGender;
import enums.ERole;
import model.User;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserController userController = new UserController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve form parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String genderParam = request.getParameter("gender");
        String phoneNumber = request.getParameter("phoneNumber");

        ERole role = ERole.STUDENT; // For now, setting a default role
        EGender gender = EGender.valueOf(genderParam.toUpperCase());

        // Call UserController to create account
        User user = userController.createAccount(username, password, role, firstName, lastName, gender, phoneNumber);

        if (user != null) {
            // Success, redirect to success page
            request.setAttribute("message", "Account created successfully!");
            request.getRequestDispatcher("register-success.jsp").forward(request, response);
        } else {
            // Failure, redirect to error page
            request.setAttribute("message", "Error creating account. Please try again.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
