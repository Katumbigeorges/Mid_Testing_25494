package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LocationId
 */
import Controller.UserController;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getLocation")
public class LocationIdServlet extends HttpServlet {
    private UserController userController = new UserController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phoneNumber = request.getParameter("phoneNumber");
        String location = userController.getLocationByPhoneNumber(phoneNumber);

        // Set location attribute to be displayed on the JSP page
        request.setAttribute("location", location);
        request.getRequestDispatcher("LocationId.jsp").forward(request, response);
    }
}

