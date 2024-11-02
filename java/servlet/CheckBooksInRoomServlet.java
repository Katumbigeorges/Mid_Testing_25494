package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.RoomController;
import Controller.ShelfController;
import model.Room;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/CheckBooksInRoomServlet")
public class CheckBooksInRoomServlet extends HttpServlet {

    private final ShelfController shelfController = new ShelfController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomIdParam = request.getParameter("roomId");
        try {
            UUID roomId = UUID.fromString(roomIdParam); // Convert string to UUID
             RoomController roomController = new RoomController();
            Room room = roomController.getRoomById(roomId);
            // Get the book count for the specified room
            long bookCount = shelfController.countBooksInRooms(room); // Assuming Room has a constructor with UUID

            // Set the book count as a request attribute to display in JSP
            request.setAttribute("bookCount", bookCount);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Invalid room ID format.");
        } catch (Exception e) {
            request.setAttribute("error", "Could not retrieve book count. Please try again.");
        }

        // Forward back to the JSP page with the result
        request.getRequestDispatcher("checkBooksInRoom.jsp").forward(request, response);
    }
}
