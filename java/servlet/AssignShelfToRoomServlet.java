package servlet;

import model.Room;
import model.Shelf;
import Service.RoomDao;
import Service.ShelfDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.ShelfController;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/assignShelfToRoom")
public class AssignShelfToRoomServlet extends HttpServlet {

    private final ShelfDao shelfDao = new ShelfDao();
    private final RoomDao roomDao = new RoomDao();
    private final ShelfController shelfController = new ShelfController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get list of shelves and rooms to display in the dropdowns
        request.setAttribute("shelves", shelfDao.getAllShelves());
        request.setAttribute("rooms", roomDao.getAllRooms());

        // Forward to JSP page
        request.getRequestDispatcher("assignShelfToRoom.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String shelfIdStr = request.getParameter("shelfId");
        String roomIdStr = request.getParameter("roomId");

        if (shelfIdStr == null || roomIdStr == null || shelfIdStr.isEmpty() || roomIdStr.isEmpty()) {
            request.setAttribute("error", "Both shelf and room selection are required.");
            doGet(request, response);
            return;
        }

        // Parse the IDs
        UUID shelfId = UUID.fromString(shelfIdStr);
        UUID roomId = UUID.fromString(roomIdStr);

        // Fetch Shelf and Room objects
        Shelf shelf = shelfDao.getShelfById(shelfId);
        Room room = roomDao.getRoomById(roomId);

        // Assign the shelf to the room
        String result = shelfController.assignShelfToRoom(shelf, room);

        // Check the result and redirect or display error
        if (result.equals("success")) {
            response.sendRedirect("success.jsp");
        } else {
            request.setAttribute("error", "Failed to assign shelf to room.");
            doGet(request, response);
        }
    }
}
