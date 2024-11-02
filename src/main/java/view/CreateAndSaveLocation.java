package view;

import java.util.UUID;
import Controller.LocationController;
import enums.ELocationType;
import model.Location;

public class CreateAndSaveLocation {
	private final LocationController locationController = new LocationController();

	public void createAndSaveLocation() {
		// Step 1: Create and save a parent location
		Location parentLocation = new Location();
		parentLocation.setId(UUID.randomUUID());
		parentLocation.setLocationCode("NY02");
		parentLocation.setLocationName("NYARUGENGE");
		parentLocation.setLocationType(ELocationType.DISTRICT);
		parentLocation.setParent(null); // No parent for the top-level location

		String parentResult = locationController.saveLocation(parentLocation);
		System.out.println(parentResult); // Confirm parent location saved

		// Step 2: Create and save a child location with the parent location set
//		Location childLocation = new Location();
//		childLocation.setId(UUID.randomUUID());
//		childLocation.setLocationCode("LOC124");
//		childLocation.setLocationName("NYAGATARE");
//		childLocation.setLocationType(ELocationType.DISTRICT);
//		childLocation.setParent(parentLocation); // Set parent reference
//
//		String childResult = locationController.saveLocation(childLocation);
//		System.out.println(childResult); // Confirm child location saved
	}

	public void getLocationById(UUID id) {
		Location location = locationController.getLocationById(id);
		System.out.println(location != null ? location : "Location not found.");
	}

	public void updateLocation(UUID id) {
		Location location = locationController.getLocationById(id);
		if (location != null) {
			location.setLocationName("Updated Library Name");
			String result = locationController.updateLocation(location);
			System.out.println(result);
		} else {
			System.out.println("Location not found for update.");
		}
	}

	public void deleteLocation(UUID id) {
		String result = locationController.deleteLocation(id);
		System.out.println(result);
	}

	public void listAllLocations() {
		locationController.getAllLocations().forEach(System.out::println);
	}

	public Location getProvinceOfVillage(UUID villageId) {
		// Retrieve the village location by ID
		Location village = locationController.getLocationById(villageId);
		if (village == null) {
			System.out.println("Village not found.");
			return null;
		}

		// Traverse up the hierarchy to find the province
		Location currentLocation = village;
		while (currentLocation != null) {
			if (currentLocation.getLocationType() == ELocationType.PROVINCE) {
				return currentLocation; // Return province location when found
			}
			currentLocation = currentLocation.getParent(); // Move up to the parent
		}

		System.out.println("Province not found for the given village.");
		return null;
	}

	public void getProvinceNameByUserId(String userId) {
		UUID userIds = UUID.fromString(userId);
		String result = locationController.getProvinceNameByPersonId(userIds);
		System.out.println("Result" + result);
	}

}
