package view;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import Controller.MembershipController;
import Controller.ShelfController;
import Service.MemberShipTypeDao;
import Service.UserDao;
import enums.EStatus;
import model.Book;
import model.MemberShipType;
import model.Membership;
import model.Shelf;
import model.User;

public class CreateAndSaveMembership {
	private MembershipController membershipController;

	public CreateAndSaveMembership() {
		this.membershipController = new MembershipController();
	}

	public void createMembership() {
		Scanner scanner = new Scanner(System.in);

		// Gather input for the new membership
		System.out.print("Enter membership code: ");
		String membershipCode = scanner.nextLine();

		EStatus membershipStatus = null;
		while (membershipStatus == null) {
			System.out.print("Enter membership status (ACTIVE, INACTIVE, EXPIRED): ");
			String statusInput = scanner.nextLine().trim(); // Trim whitespace

			// Debugging output
			System.out.println("Input status (raw): '" + statusInput + "'");
			System.out.println("Valid status options: " + Arrays.toString(EStatus.values())); // Print valid options

			try {
				membershipStatus = EStatus.valueOf(statusInput.toUpperCase());
			} catch (IllegalArgumentException e) {
				System.out.println("Invalid membership status entered: " + statusInput
						+ ". Please use ACTIVE, INACTIVE, or EXPIRED.");
			}
		}

		System.out.print("Enter registration date (yyyy-MM-dd): ");
		String registrationDateStr = scanner.nextLine();
		Date registrationDate = java.sql.Date.valueOf(registrationDateStr);

		System.out.print("Enter expiring time (yyyy-MM-dd): ");
		String expiringTimeStr = scanner.nextLine();
		Date expiringTime = java.sql.Date.valueOf(expiringTimeStr);

		// Get the user ID to associate with the membership
		System.out.print("Enter user ID: ");
		String userIdStr = scanner.nextLine();
		UUID userId = UUID.fromString(userIdStr);

		// Get the user ID to associate with the membership
		System.out.print("Enter MemberShipType ID: ");
		String memberShipTypeIdStr = scanner.nextLine();
		UUID memberShipTypeId = UUID.fromString(memberShipTypeIdStr);

		// Fetch the user object based on the user ID
		UserDao userDao = new UserDao(); // Assuming you have a UserDao to fetch user
		User user = userDao.getUserById(userId); // Fetch the user by ID

		if (user == null) {
			System.out.println("User not found for ID: " + userId);
			return; // Exit the method if user is not found
		}

		// Fetch the user object based on the user ID
		MemberShipTypeDao memberShipTypeDao = new MemberShipTypeDao(); // Assuming you have a UserDao to fetch user
		MemberShipType memberShipType = memberShipTypeDao.getMembershipTypeById(memberShipTypeId); // Fetch the user by
																									// ID

		if (memberShipType == null) {
			System.out.println("User not found for ID: " + userId);
			return; // Exit the method if user is not found
		}

		// Create a new Membership object
		Membership membership = new Membership();
		membership.setMembershipCode(membershipCode);
		membership.setMembershipStatus(membershipStatus);
		membership.setRegistrationDate(registrationDate);
		membership.setExpiringTime(expiringTime);
		membership.setUser(user); // Associate the fetched user with the membership
		membership.setMembershipType(memberShipType);
		// Save the membership
		String result = membershipController.saveMembership(membership);
		System.out.println(result);

		scanner.close();
	}

	public void getMembership() {
		MembershipController membershipController = new MembershipController();

		// Fetch memberships while the session is active
		List<Membership> memberships = membershipController.getAllMembership();
		System.out.println("Memberships: " + memberships);

		// Print membership information with controlled output
		if (memberships.isEmpty()) {
			System.out.println("No memberships found.");
			return;
		}

		// Print membership information
		for (Membership membership : memberships) {
			// Print the Membership ID and status
			System.out
					.println("Membership ID: " + membership.getId() + ", Status: " + membership.getMembershipStatus());

			// Print registration and expiration dates
			System.out.println("    Registration Date: " + membership.getRegistrationDate());
			System.out.println("    Expiring Time: " + membership.getExpiringTime());

			// Assuming Membership has a user and a membershipType associated with it
			if (membership.getUser() != null) {
				System.out.println("    User ID: " + membership.getUser().getPerson_id());
			} else {
				System.out.println("    No associated user.");
			}

			if (membership.getMembershipType() != null) {
				System.out.println("    Membership Type ID: " + membership.getMembershipType().getId());
			} else {
				System.out.println("    No membership type associated.");
			}
		}
	}

}
