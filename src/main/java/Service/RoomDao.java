package Service;

import model.Room;
import util.HIbernateConfig;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class RoomDao {

	public String saveRoom(Room room) {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			Transaction trans = session.beginTransaction();
			session.saveOrUpdate(room);
			trans.commit();
			return "Room saved successfully";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Error saving room";
		}
	}

	public List<Room> getAllRooms() {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			return session.createQuery("from Room", Room.class).list();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public Room getRoomById(UUID id) {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			return session.get(Room.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public String deleteRoom(UUID id) {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			Transaction trans = session.beginTransaction();
			Room room = session.get(Room.class, id);
			if (room != null) {
				session.delete(room);
				trans.commit();
				return "Room deleted successfully";
			}
			return "Room not found";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Error deleting room";
		}
	}
}
