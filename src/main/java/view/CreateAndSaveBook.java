package view;

import model.Book;
import model.Room;
import model.Shelf;
import Controller.BookController;
import Controller.RoomController;
import Controller.ShelfController; // Make sure to import your ShelfController
import Service.BookDao;
import enums.EBookStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CreateAndSaveBook {

	BookController bookController = new BookController();
	ShelfController shelfController = new ShelfController(); // Add ShelfController instance

	public void createAndSaveBooks(Shelf shelf) {
		// Create a list of books to save
		List<Book> books = new ArrayList<Book>();

		for (int i = 0; i < 5; i++) { // Creating 5 books
			Book book = new Book();
			book.setId(UUID.randomUUID()); // Generate a unique book ID
			book.setBook_status(EBookStatus.AVAILABLE); // Set book status
			book.setEdition(1); // Set edition
			book.setISBNCode("978-3-16-148410-" + (i + 1)); // Set ISBN code
			book.setPublication_year(new Date()); // Set publication year
			book.setPublication_name("Sample Publication " + (i + 1)); // Set publication name
			book.setTitle("Sample Book Title " + (i + 1)); // Set book title
			book.setShelf(shelf); // Associate the book with the provided shelf
			books.add(book);
		}

		// Save the books
		for (Book book : books) {
			String result = bookController.saveBook(book, shelf);
			System.out.println(result); // Print the result of each save operation
		}
	}

	public void saveBook(Shelf shelf) {
		Book book = new Book();
		book.setId(UUID.randomUUID()); // Generate a unique book ID
		book.setBook_status(EBookStatus.AVAILABLE); // Set book status
		book.setEdition(1); // Set edition
		book.setISBNCode("978-3-16-148510-"); // Set ISBN code
		book.setPublication_year(new Date()); // Set publication year
		book.setPublication_name("Sample Publication "); // Set publication name
		book.setTitle("Sample Book Title "); // Set book title
		book.setShelf(shelf); // Associate the book with the provided shelf
		String result = bookController.saveBook(book, shelf);
		System.out.println(result); // Print the result of each save operation
	}

	public void saveShelf() {
		Shelf shelf = new Shelf();
		shelf.setId(UUID.randomUUID()); // Generate a unique shelf ID
		shelf.setAvailable_stock(10); // Set available stock
		shelf.setBook_category("Java"); // Set book category
		shelf.setBorrowed_number(0); // Set borrowed number
		shelf.setInitial_stock(10); // Set initial stock

		// Fetch an existing room from the database instead of creating a new one
		RoomController roomController = new RoomController();
		UUID existingRoomId = UUID.fromString("d5e02deb-8207-416d-b29f-9c84b7c52db7"); // Replace with actual room ID
		Room room = roomController.getRoomById(existingRoomId); // Method to fetch the room by ID
		System.out.println("room" + room.toString());
		if (room != null) {
			shelf.setRoom(room); // Set the reference back to the existing room
			String result = shelfController.saveShelf(shelf); // Call the save method
			System.out.println("Shelf saved successfully: " + result);
		} else {
			System.out.println("Room not found with ID: " + existingRoomId);
		}

//        String result = shelfController.saveShelf(shelf); // Call the save method
//      System.out.println("Shelf saved successfully: " );
	}

	public void getAllBooks() {
		BookController bookController = new BookController();
		List<Book> books = bookController.getAllBooks();

		// Print the books
		if (books != null) {
			for (Book book : books) {
				System.out.println("Book ID: " + book.getId());
				System.out.println("Title: " + book.getTitle());
				System.out.println("ISBN: " + book.getISBNCode());
				System.out.println("Publication Year: " + book.getPublication_year());
				System.out.println("Edition: " + book.getEdition());
				System.out.println("Publication Name: " + book.getPublication_name());
				System.out.println("Status: " + book.getBook_status());
				System.out.println("Book Shelf: " + book.getShelf());
				System.out.println("------------");
			}
		} else {
			System.out.println("No books found.");
		}

	}

	public void ApplyFine() {
		BookController bookController = new BookController();

		bookController.applyCharges();
	}

	public void displayFewBooks() {
		BookDao bookDao = new BookDao();
		bookDao.displayRoomWithFewestBooks();
	}

}
