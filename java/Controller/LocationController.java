package Controller;

import java.util.List;
import java.util.UUID;
import Service.LocationDao;
import Service.UserDao;
import enums.ELocationType;
import model.Location;
import model.User;

public class LocationController {
	private final LocationDao locationDao = new LocationDao();

	public String saveLocation(Location location) {
		return locationDao.saveLocation(location);
	}

	public Location getLocationById(UUID id) {
		return locationDao.getLocationById(id);
	}

	public List<Location> getAllLocations() {
		return locationDao.getAllLocations();
	}

	public String updateLocation(Location location) {
		return locationDao.updateLocation(location);
	}

	public String deleteLocation(UUID id) {
		return locationDao.deleteLocation(id);
	}

	// Helper method to get the province for a given location
	private Location findProvince(Location location) {
		while (location != null) {
			if (location.getLocationType() == ELocationType.PROVINCE) {
				return location;
			}
			location = location.getParent();
		}
		return null;
	}

	// Method to retrieve province name for a person's location
	public String getProvinceNameByPersonId(UUID personId) {
		User user = new User();
		UserDao userDao = new UserDao();
		User person = userDao.getUserById(personId);
		if (person == null || person.getLocation() == null) {
			return "Location or person not found";
		}

		Location province = findProvince(person.getLocation());
		return province != null ? province.getLocationName() : "Province not found";
	}
}
