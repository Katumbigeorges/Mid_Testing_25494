package model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "room")
public class Room {
	@Id
	@Column(name = "id")
	private UUID id;
	@Column(name = "room_code")
	private String room_code;

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	private List<Shelf> shelves;
}
