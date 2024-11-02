package Service;

import model.Shelf;
import util.HIbernateConfig;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.UUID;

public class ShelfDao {

	public String saveShelf(Shelf shelf) {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.saveOrUpdate(shelf);
			transaction.commit();
			return "Shelf saved successfully: " + shelf.getId();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Error saving shelf: " + ex.getMessage();
		}
	}

	public List<Shelf> getAllShelves() {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			return session.createQuery("from Shelf", Shelf.class).list();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public Shelf getShelfById(UUID id) {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			return session.get(Shelf.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public String deleteShelf(UUID id) {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			Transaction trans = session.beginTransaction();
			Shelf shelf = session.get(Shelf.class, id);
			if (shelf != null) {
				session.delete(shelf);
				trans.commit();
				return "Shelf deleted successfully";
			}
			return "Shelf not found";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Error deleting shelf";
		}
	}

	Session session = HIbernateConfig.getSession().openSession();

	public Long countBooksInRoomss(UUID roomId) {
		String hql = "SELECT COUNT(b) FROM Book b JOIN b.shelf s WHERE s.room.id = :roomId";
		Query<Long> query = session.createQuery(hql, Long.class);
		query.setParameter("roomId", roomId);
		return query.uniqueResult();
	}

	public Long countBooksInRoom(Long roomId) {
		String hql = "SELECT COUNT(b) FROM model.Book b WHERE b.room.id = :roomId";
		return (Long) session.createQuery(hql).setParameter("roomId", roomId).uniqueResult();
	}

}
