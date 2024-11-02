package model;

import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import enums.ELocationType;

import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "location")
public class Location {

	@Id
	private UUID id;

	private String locationCode;
	private String locationName;
	@Enumerated(EnumType.STRING)
	private ELocationType locationType;

	@ManyToOne
	@JoinColumn(name = "parent_id") // Foreign key to the parent location
	private Location parent; // Self-referencing parent

	@OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
	private List<User> users;

}
