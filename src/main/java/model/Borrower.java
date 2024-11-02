package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@Table(name = "borrowers")
@ToString(exclude = { "user", "book" })
@EqualsAndHashCode(callSuper = true)
public class Borrower extends Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "UUID")
	private UUID id;

	private Date dueDate; // Due date for the borrowed book
	private int fine; // Total fines incurred
	private int lateChargesFees; // Late fees specific to the borrowing
	private Date pickupDate; // Date when the book was picked up
	private Date returnDate; // Date when the book was returned

	@ManyToOne
	@JoinColumn(name = "user_id") // Foreign key linking to the User table
	private User user; // The user borrowing the book

	@ManyToOne
	@JoinColumn(name = "book_id") // Foreign key linking to the Book table
	private Book book; // The book being borrowed

}
