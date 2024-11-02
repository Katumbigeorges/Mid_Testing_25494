package Service;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Membership;
import model.Room;
import model.Shelf;
import util.HIbernateConfig;

public class MembershipDao {

	public String saveMembership(Membership membership) {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			Transaction trans = session.beginTransaction();
			session.saveOrUpdate(membership);
			trans.commit();
			return "membership saved successfully";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Error saving membership";
		}
	}

	public Membership getMembershipById(UUID membershipId) {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			return session.get(Membership.class, membershipId);
		}
	}

	public Membership getMembershipByCode(String membershipCode) {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			Query<Membership> query = session.createQuery("from Membership where membershipCode = :code",
					Membership.class);
			query.setParameter("code", membershipCode);
			return query.uniqueResult();
		}
	}

	public List<Membership> getAllMembership() {

		try (Session session = HIbernateConfig.getSession().openSession()) {
			return session.createQuery("from Membership", Membership.class).list();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
