package model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@ToString(includeFieldNames = true, exclude = "memberships")
//@ToString(exclude = { "borrowers","membership" })
@Table(name = "users")
public class User extends Person {
	private String password;
	@Enumerated(EnumType.STRING)
	private ERole role;
	private String userName;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Membership> membership;

	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Borrower> borrowers;

}
