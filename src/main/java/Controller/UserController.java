package Controller;

import model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.hibernate.Hibernate;

import Service.LocationDao;
import Service.UserDao;
import enums.EGender;
import enums.ERole;
import model.Location;

public class UserController {

	UserDao userDao = new UserDao();
	LocationDao locationDao = new LocationDao();

	public void saveUser() {
		// Step 1: Retrieve or create a location
		UUID locationId = UUID.fromString("44d0776b-4d5a-43fd-ab3a-e6c22133a4d9"); // Replace with a valid Location ID
		Location location = locationDao.getLocationById(locationId); // Assuming locationDao is available to fetch
																		// locations

		if (location == null) {
			System.out.println("Location not found!");
			return;
		}

		// Step 2: Create a User instance and set all attributes
		User user = new User();
		user.setPerson_id(UUID.randomUUID());
		user.setFirst_name("John");
		user.setLast_name("Doe");
		user.setGender(EGender.MALE);
		user.setPassword("1234");
		user.setPhone_number("0786666");
		user.setRole(ERole.STUDENT);
		user.setUserName("John");
		user.setLocation(location); // Set the retrieved location

		// Step 3: Save the User instance
		String result = userDao.saveUser(user);
		System.out.println("Result: " + result);
	}

	public void displayAllUser() {
		List<User> users = userDao.displayAllUsers();

		// Check if the list is not null or empty
		if (users != null && !users.isEmpty()) {
			for (User user : users) {
				// Initialize the lazy collection before closing the session
				Hibernate.initialize(user.getMembership());
				// Assuming User class has a toString() method that returns user details
				System.out.println("User: " + user.getMembership());
			}
		} else {
			System.out.println("No users found.");
		}
	}

	public String hashPassword(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedHash = digest.digest(password.getBytes());
			return Base64.getEncoder().encodeToString(encodedHash); // Encode to Base64 to make it readable
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error hashing password", e);
		}
	}

	public User createAccount(String username, String rawPassword, ERole role, String firstName, String lastName,
			EGender gender, String phoneNumber) {
		User user = new User();
		user.setUserName(username);

// Hash the password
		String hashedPassword = hashPassword(rawPassword);
		user.setPassword(hashedPassword);

		user.setRole(role);
		user.setFirst_name(firstName);
		user.setLast_name(lastName);
		user.setGender(gender);
		user.setPhone_number(phoneNumber);

		UUID locationId = UUID.fromString("44d0776b-4d5a-43fd-ab3a-e6c22133a4d9"); // Replace with a valid Location ID
		Location location = locationDao.getLocationById(locationId); // Assuming locationDao is available to fetch
																		// locations
		user.setLocation(location);

		userDao.saveUser(user);

		return user;
	}

	public boolean authenticateUser(String username, String rawPassword) {
		try {
			// Fetch user by username
			User user = userDao.getUserByUsername(username);

			System.out.println("Attempting authentication for username: " + username);

			// Check if user exists and if password matches
			if (user != null) {
				System.out.println("User" + user.getUserName());
				// Hash the input password
				String hashedPassword = hashPassword(rawPassword);
				System.out.println("User" + user.getPassword());
				System.out.println("User" + hashedPassword);
				// Compare hashed passwords
				return hashedPassword.equals(user.getPassword());
			}

			return false; // Authentication failed
		} catch (Exception e) {
			// Add proper logging here
			System.err.println("Authentication failed: " + e.getMessage());
			return false;
		}
	}

	public void getUser(UUID id) {
		User user = userDao.getUserById(id);
		System.out.println("User" + user);
	}

}
