package model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import enums.EBookStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {
	@Id
	@Column(name = "id")
	private UUID id;

	@Enumerated(EnumType.STRING)
	private EBookStatus book_status;

	@Column(name = "edition")
	private int edition;

	@Column(name = "isbncode")
	private String ISBNCode;

	@Column(name = "publication_year")
	private Date publication_year;

	@Column(name = "publication_name")
	private String publication_name;

	@Column(name = "title")
	private String title;

	@ManyToOne
	@JoinColumn(name = "shelf_id", referencedColumnName = "id")
	private Shelf shelf;

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Borrower> borrowers;
}
