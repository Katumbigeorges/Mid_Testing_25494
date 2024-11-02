package view;

import java.util.List;

import Controller.MShipTypeController;
import model.MemberShipType;

public class CreateAndSaveMemberShipType {
	public void saveMemberShipType() {

		MShipTypeController membershipTypeController = new MShipTypeController();
		// Example of creating a membership type
		MemberShipType newType = new MemberShipType();
		newType.setMembershipName("Striver");
		newType.setMaxBooks(2);
		newType.setPrice(50);

		MemberShipType createdType = membershipTypeController.createMembershipType(newType);
		System.out.println("Created Membership Type: " + createdType);

	}

	public void getAllTypes() {
		MShipTypeController membershipTypeController = new MShipTypeController();
		// Example of getting all membership types
		try {
			List<MemberShipType> membershipTypes = membershipTypeController.getAllMembershipTypes();
			if (membershipTypes != null && !membershipTypes.isEmpty()) {
				membershipTypes.forEach(type -> System.out.println("Membership Type: " + type.getMembershipName()
						+ ", Max Books: " + type.getMaxBooks() + ", Price: " + type.getPrice()));
			} else {
				System.out.println("No membership types found.");
			}
		} catch (Exception e) {
			System.err.println("Error retrieving membership types: " + e.getMessage());
		}
	}
}
