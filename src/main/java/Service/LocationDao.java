package Service;

import java.util.List;
import java.util.UUID;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import model.Location;
import util.HIbernateConfig;

public class LocationDao {

	public String saveLocation(Location location) {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.saveOrUpdate(location);
			transaction.commit();
			return "Location saved successfully: " + location.getLocationName();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Error saving location: " + ex.getMessage();
		}
	}

	public Location getLocationById(UUID id) {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			return session.get(Location.class, id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public List<Location> getAllLocations() {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			Query<Location> query = session.createQuery("from Location", Location.class);
			return query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public String updateLocation(Location location) {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.update(location);
			transaction.commit();
			return "Location updated successfully: " + location.getId();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Error updating location: " + ex.getMessage();
		}
	}

	public String deleteLocation(UUID id) {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			Transaction transaction = session.beginTransaction();
			Location location = session.get(Location.class, id);
			if (location != null) {
				session.delete(location);
				transaction.commit();
				return "Location deleted successfully: " + id;
			} else {
				return "Location not found: " + id;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Error deleting location: " + ex.getMessage();
		}
	}
}
