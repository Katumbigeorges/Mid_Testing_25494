package model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import enums.EStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = { "user", "membershipType" })
@Data
@Table(name = "membership")
public class Membership {
	@Id
	@Column(name = "id")
	@GeneratedValue
	public UUID id;
	@Column(name = "expiring_time")
	public Date expiringTime;
	@Column(name = "membership_code")
	public String membershipCode;
	@Enumerated(EnumType.STRING)
	@Column(name = "membership_status")
	public EStatus membershipStatus;
	@Column(name = "registration_date")
	public Date registrationDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "membership_type_id")
	public MemberShipType membershipType;

}
