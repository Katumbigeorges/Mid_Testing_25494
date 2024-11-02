package Service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.MemberShipType;
import util.HIbernateConfig;

import java.util.List;
import java.util.UUID;

public class MemberShipTypeDao {

	public MemberShipType createMembershipType(MemberShipType membershipType) {
		Transaction transaction = null;
		try (Session session = HIbernateConfig.getSession().openSession()) {
			transaction = session.beginTransaction();
			session.save(membershipType);
			transaction.commit();
			return membershipType;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	public MemberShipType getMembershipTypeById(UUID id) {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			return session.get(MemberShipType.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<MemberShipType> getAllMembershipTypes() {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			return session.createQuery("from MemberShipType", MemberShipType.class).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public MemberShipType updateMembershipType(MemberShipType membershipType) {
		Transaction transaction = null;
		try (Session session = HIbernateConfig.getSession().openSession()) {
			transaction = session.beginTransaction();
			session.update(membershipType);
			transaction.commit();
			return membershipType;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteMembershipType(UUID id) {
		Transaction transaction = null;
		try (Session session = HIbernateConfig.getSession().openSession()) {
			transaction = session.beginTransaction();
			MemberShipType membershipType = session.get(MemberShipType.class, id);
			if (membershipType != null) {
				session.delete(membershipType);
				transaction.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		}
	}
}
