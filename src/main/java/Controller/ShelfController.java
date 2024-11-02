package Controller;

import model.Book;
import model.Room;
import model.Shelf;
import util.HIbernateConfig;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Service.BookDao;
import Service.ShelfDao;

public class ShelfController {

	private final ShelfDao shelfDao = new ShelfDao();

	public String saveShelf(Shelf shelf) {
		return shelfDao.saveShelf(shelf);
	}

	public String saveShelves(List<Shelf> shelves) {
		StringBuilder result = new StringBuilder();
		for (Shelf shelf : shelves) {
			result.append(saveShelf(shelf)).append("\n");
		}
		return result.toString();
	}

	public List<Shelf> getAllShelves() {
		return shelfDao.getAllShelves();
	}

	public Shelf getShelfById(UUID id) {
		return shelfDao.getShelfById(id);

	}

	public String deleteShelf(UUID id) {
		return shelfDao.deleteShelf(id);
	}

	public String assignShelfToRoom(Shelf shelf, Room room) {
		if (shelf == null || room == null) {
			return "Shelf or room cannot be null";
		}

		shelf.setRoom(room); // Assign the room to the shelf
		return shelfDao.saveShelf(shelf); // Save the shelf with the assigned room
	}

	BookDao bookDao = new BookDao();

	public long countBooksInRooms(Room room) {
		if (room == null) {
			throw new IllegalArgumentException("Room cannot be null");
		}

		return bookDao.countBooksInRoom(room.getId());
	}

	public void displayBooksAndCountInRooms() {
		List<Book> books = bookDao.getAllBooks();

		for (Book book : books) {
			Shelf shelf = book.getShelf(); // Get the shelf associated with the book
			if (shelf != null) {
				Room room = shelf.getRoom(); // Get the room associated with the shelf
				if (room != null) {
					Long bookCount = bookDao.countBooksInRoom(room.getId());
					System.out.println("Room: " + room.getRoom_code() + ", Shelf: " + shelf.getId() + ", Book Title: "
							+ book.getTitle());
					System.out.println("Count of Books in Room: " + bookCount);
				} else {
					System.out.println("Shelf: " + shelf.getId() + " does not belong to any room.");
				}
			} else {
				System.out.println("Book Title: " + book.getTitle() + " is not assigned to any shelf.");
			}
		}
	}

	// Display books in a specific room by room ID
	public void displayBooksInRoom(UUID roomId) {
		List<Book> books = bookDao.getBooksByRoomId(roomId);

		if (books.isEmpty()) {
			System.out.println("No books found in the room with ID: " + roomId);
		} else {
			System.out.println("Books in Room ID: " + roomId);
			System.out.println("Total: " + books.size());
			for (Book book : books) {
				Shelf shelf = book.getShelf();
				if (shelf != null) {
					System.out.println("Shelf ID: " + shelf.getId() + ", Book Title: " + book.getTitle());
				} else {
					System.out.println("Book Title: " + book.getTitle() + " is not assigned to any shelf.");
				}
			}
		}
	}

}
