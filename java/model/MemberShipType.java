package model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "membership_type")
public class MemberShipType {
	@Id
	@GeneratedValue
	public UUID id;

	public String membershipName;
	public int maxBooks;
	public int price;

	@OneToMany(mappedBy = "membershipType", fetch = FetchType.EAGER)
	public List<Membership> memberships;

}
