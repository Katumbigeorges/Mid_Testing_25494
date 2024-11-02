package Service;

import model.Book;
import model.Borrower;
import model.Room;
import model.Shelf;
import util.HIbernateConfig;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class BookDao {

	public String saveBook(Book book, Shelf shelf) {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			Transaction transaction = session.beginTransaction();
			book.setShelf(shelf);
			session.saveOrUpdate(book);
			transaction.commit();
			return "Book saved successfully: " + book.getId();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Error saving book: " + ex.getMessage();
		}

	}

	public List<Book> getAllBooks() {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			return session.createQuery("from Book", Book.class).list();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public Book getBookById(UUID id) {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			return session.get(Book.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public String deleteBook(UUID id) {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			Transaction trans = session.beginTransaction();
			Book book = session.get(Book.class, id);
			if (book != null) {
				session.delete(book);
				trans.commit();
				return "Book deleted successfully";
			}
			return "Book not found";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Error deleting book";
		}
	}

	public long countBooksInRooms(UUID roomId) {
		long count = 0;
		String hql = "SELECT COUNT(b) FROM Book b WHERE b.room.id = :roomId";

		try (Session session = HIbernateConfig.getSession().openSession()) {
			Transaction transaction = session.beginTransaction();
			count = session.createQuery(hql, Long.class).setParameter("roomId", roomId).uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Handle exception appropriately (e.g., log it or rethrow it)
		}
		return count;
	}

	Session session = HIbernateConfig.getSession().openSession();

	public Long countBooksInRoom(UUID roomId) {
		String hql = "SELECT COUNT(b) FROM model.Book b JOIN b.shelf s WHERE s.room.id = :roomId";
		return (Long) session.createQuery(hql).setParameter("roomId", roomId).uniqueResult();
	}

	public List<Book> getBooksByRoomId(UUID roomId) {
//		   String hql = "SELECT b FROM Book b JOIN b.shelf.id s WHERE s.room.id = :roomId";
		String hql = "SELECT b FROM model.Book b WHERE b.shelf.id IN (SELECT b.shelf.id FROM model.Shelf s WHERE s.room.id = :roomId)";
		return session.createQuery(hql, Book.class).setParameter("roomId", roomId).list();
	}

	public void displayRoomWithFewestBooks() {
		String hql = "SELECT r.id, COALESCE(COUNT(b.id), 0) AS bookCount "
				+ "FROM Room r LEFT JOIN r.shelves s LEFT JOIN s.books b " + "GROUP BY r.id "
				+ "ORDER BY bookCount ASC";

		// Retrieve a single result with the room ID and book count
		Object[] result = session.createQuery(hql, Object[].class).setMaxResults(1).uniqueResult();

		if (result != null) {
			UUID roomId = (UUID) result[0];
			Long bookCount = (Long) result[1];
			System.out.println("Room ID: " + roomId + ", Number of Books: " + bookCount);
		} else {
			System.out.println("No rooms found.");
		}
	}

	private static final int DAILY_LATE_FEE = 10; // Fee per day for late returns

	// Method to calculate and apply late fees
	public int calculateAndApplyLateFees(Borrower borrower) {
		Date dueDate = borrower.getDueDate();
		Date returnDate = borrower.getReturnDate();

		// Check if the book is overdue
		if (returnDate != null && returnDate.after(dueDate)) {
			// Calculate the number of overdue days
			long diffInMillis = returnDate.getTime() - dueDate.getTime();
			long overdueDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);

			// Calculate the late fee
			int lateFee = (int) overdueDays * DAILY_LATE_FEE;

			// Update the borrower's late charges and total fine
			borrower.setLateChargesFees(lateFee);
			borrower.setFine(borrower.getFine() + lateFee);

			// Optionally, persist the changes to the database if using an ORM
			// session.update(borrower); // Assuming Hibernate or JPA session is available

			System.out.println("Late fee applied: " + lateFee);
			return lateFee;
		} else {
			System.out.println("No late fee applied.");
			return 0;
		}
	}

}
