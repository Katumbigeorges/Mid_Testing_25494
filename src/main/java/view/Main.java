package view;

import org.hibernate.Session;
import Controller.BookController;
import Controller.BorrowerController;
import Controller.RoomController;
import Controller.ShelfController;
import Controller.UserController;
import Service.BookDao;
import Service.UserDao;
import enums.EGender;
import enums.ERole;
import model.Location;
import model.Room;
import model.Shelf;
import model.User;
import util.HIbernateConfig;

import java.util.UUID;

public class Main {
	public static void main(String[] args) {
		// Check the database connection
		try (Session session = HIbernateConfig.getSession().openSession()) {
			System.out.println("Connected to the database successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			return; // Exit the program if the connection fails
		}

		// Create shelves first (ensure the shelves are saved before adding books)
		CreateAndSaveShelves createAndSaveShelves = new CreateAndSaveShelves();
		// Uncomment the next line to create and save shelves
		// createAndSaveShelves.createAndSaveShelves();
//		createAndSaveShelves.getShelves();

		// Convert the string ID to UUID
		UUID shelfId = UUID.fromString("f1ae23d3-b5c0-4394-afad-405f2c21b85e");
//		 Shelf shelf = shelfController.getShelfById(shelfId);
		UUID roomId = UUID.fromString("7fcaf60f-7036-4881-a5a3-fa59c0238c2b");
//		 Room room = roomController.getRoomById(roomId);
//		 System.out.println("Shelf" + shelf.getBorrowed_number());

//		shelfController.assignShelfToRoom(shelf, room);
//		shelfController.displayBooksAndCountInRooms();
//		 shelfController.displayBooksInRoom(roomId);
//		System.out.println("Result" + result);

		CreateAndSaveBook createAndSaveBooks = new CreateAndSaveBook();
//		createAndSaveBooks.getAllBooks();
//		createAndSaveBooks.ApplyFine();
//		createAndSaveBooks.displayFewBooks();

//		if (shelf != null) { // Ensure the shelf was found before proceeding
//			// Create and save books associated with the retrieved shelf
//			// Uncomment the next lines to create and save books
////             createAndSaveBooks.createAndSaveBooks(shelf);
////             createAndSaveBooks.saveBook(shelf);
////            createAndSaveBooks.saveShelf();
//		} else {
//			System.out.println("Shelf not found for ID: " + shelfId);
//		}
//        createAndSaveBooks.createAndSaveBooks(shelf);
//      createAndSaveBooks.saveBook(shelf);
//     createAndSaveBooks.saveShelf();
		// Create and save room with shelves
		CreateAndSaveRoom createAndSaveRoom = new CreateAndSaveRoom();
//        createAndSaveRoom.createAndSaveRooms(); // This will save the new room and its shelves
//        createAndSaveRoom.saveRoom();

		UserController userController = new UserController();
//		userController.saveUser();
//		userController.createAccount("irving", "123", ERole.STUDENT, "Kyrie", "Irving", EGender.MALE, "0788888");
//        boolean result =  userController.authenticateUser("irving1", "123");
//		System.out.println("Result"+result);
//		userController.displayAllUser();
//		userController.getUser(UUID.fromString("b682a142-68cd-426f-b5be-5461467b0539"));

		// Save Membership
		CreateAndSaveMembership createAndSaveMembership = new CreateAndSaveMembership();
//		createAndSaveMembership.createMembership();
//		createAndSaveMembership.getMembership();

//        Save Membership

		CreateAndSaveMemberShipType CreateAndSaveMemberShipType = new CreateAndSaveMemberShipType();
//		CreateAndSaveMemberShipType.getAllTypes();
//		CreateAndSaveMemberShipType.saveMemberShipType();

		CreateAndSaveLocation createAndSaveLocation = new CreateAndSaveLocation();
//		createAndSaveLocation.createAndSaveLocation();
//		createAndSaveLocation.getProvinceNameByUserId("eb74209d-16a5-44b1-af18-608ff95a7db0");
//		Location location = createAndSaveLocation.getProvinceOfVillage(UUID.fromString("44d0776b-4d5a-43fd-ab3a-e6c22133a4d9"));
//        System.out.println("Location"+location.getLocationName());

		CreateAndSaveBorrower createAndSaveBorrowers = new CreateAndSaveBorrower();
//		createAndSaveBorrowers.createAndSaveBorrower();

		UserDao userDao = new UserDao();
//		User user = userDao.getUserById(UUID.fromString("b682a142-68cd-426f-b5be-5461467b0539"));
//		Boolean result = createAndSaveBorrowers.canUserBorrowMoreBooks(user);
//		Boolean result = createAndSaveBorrowers.hasReachedBorrowLimit(user);
//		System.out.println("Result " + result);
	}
}
