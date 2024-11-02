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

/**
 * Servlet implementation class LocationServlet
 */
@WebServlet("/LocationServlet")
public class LocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final LocationController locationController = new LocationController();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String locationCode = request.getParameter("locationCode");
		String locationName = request.getParameter("locationName");
		String locationTypeStr = request.getParameter("locationType");
		String parentIdStr = request.getParameter("parentId");

		// Convert location type to enum
		ELocationType locationType = ELocationType.valueOf(locationTypeStr.toUpperCase());
		Location parentLocation = null;

		// Find parent location if provided
		if (parentIdStr != null && !parentIdStr.isEmpty()) {
			UUID parentId = UUID.fromString(parentIdStr);
			parentLocation = locationController.getLocationById(parentId);
		}

		// Create and save the new location
		Location newLocation = new Location();
		newLocation.setId(UUID.randomUUID());
		newLocation.setLocationCode(locationCode);
		newLocation.setLocationName(locationName);
		newLocation.setLocationType(locationType);
		newLocation.setParent(parentLocation);

		String result = locationController.saveLocation(newLocation);
		response.getWriter().write(result);
	}
}
