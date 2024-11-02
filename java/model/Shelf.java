package model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "shelf")
public class Shelf {
	@Id
	@Column(name = "id")
	private UUID id;

	private int available_stock;
	private String book_category;
	private int borrowed_number;
	private int initial_stock;

	@ManyToOne
	@JoinColumn(name = "room_id", referencedColumnName = "id")
	private Room room;

//	@OneToMany(mappedBy = "shelf")
//	private List<Book> books;
	@OneToMany(mappedBy = "shelf", fetch = FetchType.EAGER)
	private List<Book> books;

}
