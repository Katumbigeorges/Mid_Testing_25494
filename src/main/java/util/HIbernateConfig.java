package util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import model.Book;
import model.Borrower;
import model.Location;
import model.MemberShipType;
import model.Membership;
import model.Room;
import model.Shelf;
import model.User;

public class HIbernateConfig {

	private static SessionFactory sessionFactory = null;

	public static SessionFactory getSession() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();

				Properties property = new Properties();

				property.put(Environment.DRIVER, "org.postgresql.Driver");
				property.put(Environment.URL, "jdbc:postgresql://localhost:5432/auca_library_db");
				property.put(Environment.USER, "postgres");
				property.put(Environment.PASS, "1234");
				property.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
				property.put(Environment.SHOW_SQL, "true");
				property.put(Environment.HBM2DDL_AUTO, "update");
				// Set better connection pool settings
				configuration.setProperty("hibernate.connection.pool_size", "5");
				configuration.setProperty("hibernate.current_session_context_class", "thread");
				configuration.setProperty("hibernate.connection.release_mode", "after_transaction");

				// Connection timeouts
				configuration.setProperty("hibernate.connection.timeout", "1000");
				configuration.setProperty("hibernate.c3p0.timeout", "300");
				configuration.setProperty("hibernate.c3p0.max_statements", "50");

				// Set a shorter timeout
				configuration.setProperty("hibernate.connection.timeout", "1000");

				configuration.setProperties(property);
				configuration.addAnnotatedClass(User.class);
				configuration.addAnnotatedClass(Book.class);
				configuration.addAnnotatedClass(Shelf.class);
				configuration.addAnnotatedClass(Room.class);
				configuration.addAnnotatedClass(Membership.class);
				configuration.addAnnotatedClass(MemberShipType.class);
				configuration.addAnnotatedClass(Location.class);
				configuration.addAnnotatedClass(Borrower.class);
				sessionFactory = configuration.buildSessionFactory();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}

	// Method to close the SessionFactory
	public static void closeSessionFactory() {
		if (sessionFactory != null && sessionFactory.isOpen()) {
			sessionFactory.close();
			System.out.println("SessionFactory closed successfully.");
		}
	}
}