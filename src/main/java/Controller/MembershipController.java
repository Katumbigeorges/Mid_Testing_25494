package Controller;

import java.util.List;
import java.util.UUID;

import Service.MembershipDao;
import Service.RoomDao;
import model.Membership;
import model.Room;

public class MembershipController {
	private final MembershipDao membershipDao = new MembershipDao();

	public String saveMembership(Membership membership) {
		return membershipDao.saveMembership(membership);
	}

	// Retrieve membership by ID
	public Membership getMembershipById(UUID membershipId) {
		return membershipDao.getMembershipById(membershipId);
	}

	// Retrieve membership by membership code
	public Membership getMembershipByCode(String membershipCode) {
		return membershipDao.getMembershipByCode(membershipCode);
	}

	public List<Membership> getAllMembership() {
		return membershipDao.getAllMembership();
	}
}
