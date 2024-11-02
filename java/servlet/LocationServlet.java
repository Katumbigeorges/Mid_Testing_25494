package servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.LocationController;
import enums.ELocationType;
import model.Location;

@WebServlet("/LocationServlet")
public class LocationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final LocationController locationController;
    
    public LocationServlet() {
        super();
        locationController = new LocationController();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Forward to the JSP page for displaying the form
        request.getRequestDispatcher("/Location.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // Get parameters from the form
            String locationCode = request.getParameter("locationCode");
            String locationName = request.getParameter("locationName");
            String locationTypeStr = request.getParameter("locationType");
            String parentIdStr = request.getParameter("parentId");

            // Validate required fields
            if (locationCode == null || locationCode.trim().isEmpty() ||
                locationName == null || locationName.trim().isEmpty() ||
                locationTypeStr == null || locationTypeStr.trim().isEmpty()) {
                
                response.setContentType("text/html");
                response.getWriter().write("Error: All required fields must be filled out");
                return;
            }

            // Create new location object
            Location newLocation = new Location();
            newLocation.setId(UUID.randomUUID());
            newLocation.setLocationCode(locationCode.trim());
            newLocation.setLocationName(locationName.trim());
            
            try {
                newLocation.setLocationType(ELocationType.valueOf(locationTypeStr.toUpperCase()));
            } catch (IllegalArgumentException e) {
                response.setContentType("text/html");
                response.getWriter().write("Error: Invalid location type");
                return;
            }

            // Handle parent location if provided
            if (parentIdStr != null && !parentIdStr.trim().isEmpty()) {
                try {
                    UUID parentId = UUID.fromString(parentIdStr.trim());
                    Location parentLocation = locationController.getLocationById(parentId);
                    if (parentLocation == null) {
                        response.setContentType("text/html");
                        response.getWriter().write("Error: Parent location not found");
                        return;
                    }
                    newLocation.setParent(parentLocation);
                } catch (IllegalArgumentException e) {
                    response.setContentType("text/html");
                    response.getWriter().write("Error: Invalid parent location ID format");
                    return;
                }
            }

            // Save the location
            String result = locationController.saveLocation(newLocation);
            
            // Set response type and send response
            response.setContentType("text/html");
//            if (result.contains("successfully")) {
//                // Redirect to a success page or show success message
//                response.getWriter().write("Location saved successfully! Location ID: " + newLocation.getId());
//            } else {
//                response.getWriter().write("Error saving location: " + result);
//            }
            if (result.contains("successfully")) {
                // Set the message in the request
                request.setAttribute("modalMessage", "Location saved successfully! Location ID: " + newLocation.getId());
                request.getRequestDispatcher("/Location.jsp").forward(request, response);
            } else {
                response.getWriter().write("Error saving location: " + result);
            }

        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            
            // Send error response
            response.setContentType("text/html");
            response.getWriter().write("An error occurred while processing your request: " + e.getMessage());
        }
    }
}