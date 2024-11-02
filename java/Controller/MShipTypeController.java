package Controller;

import model.MemberShipType;

import java.util.List;
import java.util.UUID;

import Service.MemberShipTypeDao;

public class MShipTypeController {
	private final MemberShipTypeDao membershipTypeService = new MemberShipTypeDao();;

	public MemberShipType createMembershipType(MemberShipType membershipType) {
		return membershipTypeService.createMembershipType(membershipType);
	}

	public MemberShipType getMembershipTypeById(UUID id) {
		return membershipTypeService.getMembershipTypeById(id);
	}

	public List<MemberShipType> getAllMembershipTypes() {
		return membershipTypeService.getAllMembershipTypes();
	}

	public MemberShipType updateMembershipType(MemberShipType membershipType) {
		return membershipTypeService.updateMembershipType(membershipType);
	}

	public boolean deleteMembershipType(UUID id) {
		return membershipTypeService.deleteMembershipType(id);
	}
}
