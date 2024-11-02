package Service;

import model.Borrower;
import util.HIbernateConfig;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

public class BorrowerDao {

	// Save or update a borrower
	public String saveBorrower(Borrower borrower) {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.saveOrUpdate(borrower);
			transaction.commit();
			return "Borrower saved successfully: " + borrower.getId();
		} catch (Exception ex) {
			return handleException(ex, "Error saving borrower");
		}
	}

	// Retrieve all borrowers
	public List<Borrower> getAllBorrowers() {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			return session.createQuery("FROM Borrower", Borrower.class).list();
		} catch (Exception ex) {
			handleException(ex, "Error retrieving borrowers");
			return List.of(); // Return an empty list instead of null
		}
	}

	Session session = HIbernateConfig.getSession().openSession();

	public Borrower getBorrowerById(UUID id) {
		try {
			Borrower borrower = session.createQuery("FROM Borrower b WHERE b.id = :id", Borrower.class)
					.setParameter("id", id).uniqueResult();

			if (borrower == null) {
//	                log.warn("Borrower not found with ID: {}", id);
				throw new EntityNotFoundException("Borrower with ID " + id + " not found.");
			}

			return borrower;
		} catch (HibernateException e) {
//	            log.error("Error retrieving borrower with ID: {}", id, e);
			throw new RuntimeException("Error retrieving borrower: " + e.getMessage(), e);
		}
	}

	// Delete a borrower by ID
	public String deleteBorrower(UUID id) {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			Transaction transaction = session.beginTransaction();
			Borrower borrower = session.get(Borrower.class, id);
			if (borrower != null) {
				session.delete(borrower);
				transaction.commit();
				return "Borrower deleted successfully";
			}
			return "Borrower not found";
		} catch (Exception ex) {
			return handleException(ex, "Error deleting borrower");
		}
	}

	// Centralized exception handling
	private String handleException(Exception ex, String message) {
		ex.printStackTrace();
		return message + ": " + ex.getMessage();
	}
}
