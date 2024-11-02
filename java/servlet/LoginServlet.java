package servlet;

import Controller.UserController;
import Service.UserDao;
import model.User;
import enums.ERole;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserController userController = new UserController();
    private UserDao userDao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        // Authenticate the user
        if (userController.authenticateUser(username, password)) {
            // Fetch the user by username and get their role
            User user = userDao.getUserByUsername(username);
            ERole role = user.getRole();

            // Store role in session for authorization
            session.setAttribute("role", role.toString());

            // Redirect to dashboard
            response.sendRedirect("dashboard.jsp");
        } else {
            // Set error attribute and redirect to login page with error message
            request.setAttribute("loginError", "Invalid credentials");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
