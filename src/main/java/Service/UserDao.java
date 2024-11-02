package Service;

import java.util.List;
import java.util.UUID;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.MemberShipType;
import model.User;
import util.HIbernateConfig;

public class UserDao {

	public String saveUser(User user) {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			Transaction trans = session.beginTransaction();
			session.merge(user);
			trans.commit();
			return "saved";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "error";
		}
	}

	public User getUserById(UUID userId) {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			MemberShipType memberShipType = new MemberShipType();
			Hibernate.initialize(memberShipType.getMemberships());

			return session.get(User.class, userId); // Assuming you want a specific user by ID
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public List<User> displayAllUsers() {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			return session.createQuery("FROM User", User.class).list();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public User getUserByUsername(String username) {
		Session session = HIbernateConfig.getSession().openSession();
		return session.createQuery("FROM User u LEFT JOIN FETCH u.membership WHERE u.userName = :username", User.class)
				.setParameter("username", username).uniqueResult();
	}

	public User getUserWithBorrowers(UUID userId) {
		Session session = HIbernateConfig.getSession().openSession();
		User user = session.get(User.class, userId); // Or use your method to fetch the user
		Hibernate.initialize(user.getBorrowers()); // Explicitly initialize the borrowers
		return user;
	}

}
