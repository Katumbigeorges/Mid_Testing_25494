package view;

import model.Book;
import model.Shelf;
import Controller.ShelfController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreateAndSaveShelves {
	ShelfController shelfController = new ShelfController();

	public void createAndSaveShelves() {
		// Create a ShelfController instance
		ShelfController shelfController = new ShelfController();

		// Create a list of shelves to save
		List<Shelf> shelves = new ArrayList<Shelf>();

		for (int i = 0; i < 5; i++) { // Creating 5 shelves
			Shelf shelf = new Shelf();
			shelf.setId(UUID.randomUUID()); // Generate a unique shelf ID
			shelf.setAvailable_stock(10);
			shelf.setBook_category("Fiction " + (i + 1));
			shelf.setBorrowed_number(0);
			shelf.setInitial_stock(10);
			shelves.add(shelf);
		}

		// Save the shelves
		String result = shelfController.saveShelves(shelves);
		System.out.println(result); // Print the result of the save operation
	}

	public void getShelves() {
		ShelfController shelfController = new ShelfController();

		// Fetch shelves while the session is active
		List<Shelf> shelves = shelfController.getAllShelves();
		System.out.println("Shelf" + shelves);

		// Print shelf information with controlled output
		if (shelves.isEmpty()) {
			System.out.println("No shelves found.");
			return;
		}

		// Print shelf information with controlled output
		for (Shelf shelf : shelves) {
			// Print the Shelf ID and book category
			System.out.println("Shelf ID: " + shelf.getId() + ", Category: " + shelf.getBook_category());

			// Check if the shelf has books and print them
			if (shelf.getBooks() != null && !shelf.getBooks().isEmpty()) {
				for (Book book : shelf.getBooks()) {
					// Assuming Book has getId() and getTitle() methods; adjust as necessary
					System.out.println("    Book ID: " + book.getId() + ", Title: " + book.getTitle());
				}
			} else {
				System.out.println("    No books available in this shelf.");
			}
		}
	}

}
