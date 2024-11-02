package Controller;

import model.Book;
import model.Borrower;
import model.Shelf;

import java.util.List;
import java.util.UUID;

import Service.BookDao;

public class BookController {

	private final BookDao bookDao = new BookDao();

	public String saveBook(Book book, Shelf shelf) {
		return bookDao.saveBook(book, shelf);
	}

	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}

	public Book getBookById(UUID id) {
		return bookDao.getBookById(id);
	}

	public String deleteBook(UUID id) {
		return bookDao.deleteBook(id);
	}

	public void applyCharges() {
		BorrowerController bController = new BorrowerController();
		UUID borrowerId = UUID.fromString("4cc9c651-a001-4cc8-8e0a-0275ef65280d");

		// Fetch the borrower
		Borrower borrower = bController.getBorrowerById(borrowerId);
		System.out.println("borrower " + borrower);

		if (borrower != null) {
			BookDao borrowerService = new BookDao();
			int fee = borrowerService.calculateAndApplyLateFees(borrower);
			System.out.println("Total Late Fee Charged: " + fee);
		} else {
			System.out.println("Borrower with ID " + borrowerId + " not found.");
		}
	}

}
