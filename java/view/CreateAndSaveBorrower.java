package view;

import model.Book;
import model.Borrower;
import model.MemberShipType;
import model.Membership;
import model.Shelf;
import model.User;
import util.HIbernateConfig;
import Controller.BorrowerController;
import Service.BookDao;
import Service.ShelfDao;
import Service.UserDao;
import enums.EGender;
import enums.EStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import org.hibernate.Hibernate;
import org.hibernate.Session;

public class CreateAndSaveBorrower {

	private final BorrowerController borrowerController = new BorrowerController();
	private final Scanner scanner = new Scanner(System.in);
	UserDao userDao = new UserDao();
	BookDao bookDao = new BookDao();
	ShelfDao shelfDao = new ShelfDao();

	public void createAndSaveBorrower() {
		Borrower borrower = new Borrower();
		borrower.setId(UUID.randomUUID());

		try {
			// Set attributes inherited from Person
			borrower.setFirst_name(getStringInput("Enter the first name of the borrower: "));
			borrower.setLast_name(getStringInput("Enter the last name of the borrower: "));
			borrower.setPhone_number(getStringInput("Enter the phone number of the borrower: "));

			// Set gender with validation
			String genderInput = getStringInput("Enter the gender (MALE/FEMALE/OTHER): ").toUpperCase();
			borrower.setGender(EGender.valueOf(genderInput));

			// Set dates with validation
			borrower.setDueDate(getDateInput("Enter the due date (yyyy-mm-dd): "));
			borrower.setFine(getIntInput("Enter any fines (0 if none): "));
			borrower.setPickupDate(getDateInput("Enter the pickup date (yyyy-mm-dd): "));
			borrower.setReturnDate(getDateInput("Enter the return date (yyyy-mm-dd): "));

			// Fetch user
			UUID userId = getUUIDInput("Enter the user ID (UUID format): ");
			User user = userDao.getUserById(userId);
			if (user == null) {
				System.out.println("User not found.");
				return;
			}

			// Check borrowing limit
			if (!hasReachedBorrowLimit(user)) {
				System.out.println("User has reached their borrowing limit and cannot borrow more books.");
				return;
			}
			borrower.setUser(user);

			// Fetch book
			UUID bookId = getUUIDInput("Enter the book ID (UUID format): ");
			Book book = bookDao.getBookById(bookId);
			if (book == null) {
				System.out.println("Book not found.");
				return;
			}
			borrower.setBook(book);

			// Update shelf stock and borrowed count if available
			Shelf shelf = book.getShelf();
			if (shelf.getAvailable_stock() > 0) {
				shelf.setBorrowed_number(shelf.getBorrowed_number() + 1);
				shelf.setAvailable_stock(shelf.getAvailable_stock() - 1);

				shelfDao.saveShelf(shelf);
				book.setShelf(shelf);
				borrower.setBook(book);
				// Save borrower and update shelf
				String result = borrowerController.saveBorrower(borrower);
//	            shelfRepository.save(shelf);
				System.out.println(result);
			} else {
				System.out.println("No available stock to borrow.");
			}

		} catch (IllegalArgumentException e) {
			System.out.println("Error: Invalid input. " + e.getMessage());
		} catch (Exception e) {
			System.out.println("An unexpected error occurred: " + e.getMessage());
		}
	}

	// Helper methods
	private String getStringInput(String prompt) {
		System.out.print(prompt);
		return scanner.nextLine().trim();
	}

	private UUID getUUIDInput(String prompt) {
		System.out.print(prompt);
		return UUID.fromString(scanner.nextLine().trim());
	}

	private int getIntInput(String prompt) {
		System.out.print(prompt);
		while (!scanner.hasNextInt()) {
			System.out.println("Please enter a valid integer.");
			System.out.print(prompt);
			scanner.next();
		}
		int value = scanner.nextInt();
		scanner.nextLine(); // Clear the buffer
		return value;
	}

	private Date getDateInput(String prompt) {
		System.out.print(prompt);
		String input = scanner.nextLine().trim();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Expected date format

		try {
			return formatter.parse(input); // Parse input to Date
		} catch (ParseException e) {
			System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
			return getDateInput(prompt); // Retry if format is incorrect
		}
	}

	public boolean canUserBorrowMoreBooks(User user) {
		// Validate user
		if (user == null) {
			throw new IllegalArgumentException("User cannot be null.");
		}

		// Get the count of currently borrowed books
		int currentBorrowedCount = user.getBorrowers().size(); // Count of currently borrowed books

		// Find an active membership
		Membership activeMembership = user.getMembership().stream()
				.filter(m -> m.getMembershipStatus() == EStatus.APPROVED).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("User has no active membership."));

		// Determine max books allowed based on membership type
		int maxBooksAllowed = getMaxBooksAllowed(activeMembership.getMembershipType());

		// Return true if the number of currently borrowed books is less than the
		// maximum allowed
		return currentBorrowedCount < maxBooksAllowed;
	}

	// Helper method to get maximum allowed books based on membership type
	private int getMaxBooksAllowed(MemberShipType membershipType) {
		switch (membershipType.getMembershipName().toUpperCase()) {
		case "GOLD":
			return 5;
		case "SILVER":
			return 3;
		case "STRIVER":
			return 2;
		default:
			throw new IllegalArgumentException("Unknown membership type: " + membershipType.getMembershipName());
		}
	}

	// Method to retrieve all borrowers
	public void viewAllBorrowers() {
		List<Borrower> borrowers = borrowerController.getAllBorrowers();
		if (borrowers.isEmpty()) {
			System.out.println("No borrowers found.");
		} else {
			for (Borrower borrower : borrowers) {
				System.out.println(borrower);
			}
		}
	}

	// Method to delete a borrower by ID
	public void deleteBorrower() {
		System.out.print("Enter the ID of the borrower to delete: ");
		UUID id = UUID.fromString(scanner.nextLine());
		String result = borrowerController.deleteBorrower(id);
		System.out.println(result);
	}

	// Utility method to parse date (this should be implemented based on your
	// requirements)
	private Date parseDate(String dateString) {
		// Implement date parsing logic here
		// You can use SimpleDateFormat or any other date parsing mechanism
		return null; // Placeholder return statement
	}

	// Main method for testing purposes
	public static void main(String[] args) {
		CreateAndSaveBorrower view = new CreateAndSaveBorrower();
		while (true) {
			System.out.println("Choose an operation:");
			System.out.println("1. Create and Save Borrower");
			System.out.println("2. View All Borrowers");
			System.out.println("3. Delete Borrower");
			System.out.println("4. Exit");
			System.out.print("Enter your choice: ");

			Scanner scanner = new Scanner(System.in);
			int choice = scanner.nextInt();
			scanner.nextLine(); // Clear the buffer

			switch (choice) {
			case 1:
				view.createAndSaveBorrower();
				break;
			case 2:
				view.viewAllBorrowers();
				break;
			case 3:
				view.deleteBorrower();
				break;
			case 4:
				System.out.println("Exiting...");
				return;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	public boolean hasReachedBorrowLimit(User user) {
		try (Session session = HIbernateConfig.getSession().openSession()) {
			// Fetch the User along with membership data
			String userHql = """
					    SELECT u
					    FROM User u
					    LEFT JOIN FETCH u.membership
					    WHERE u.person_id = :personId
					""";
			User fetchedUser = session.createQuery(userHql, User.class).setParameter("personId", user.getPerson_id())
					.uniqueResult();

			if (fetchedUser == null) {
				throw new IllegalStateException("User not found");
			}

			// Initialize the membership collection if needed
			Hibernate.initialize(fetchedUser.getMembership());

			// Count the books borrowed by the fetched user
			String hql = """
					    SELECT COUNT(b)
					    FROM Borrower b
					    WHERE b.user = :user
					""";
			Long booksBorrowed = session.createQuery(hql, Long.class).setParameter("user", fetchedUser).uniqueResult();
			System.out.println("Books Borrowed: " + booksBorrowed);

			// Get the user's memberships from fetchedUser
			List<Membership> userMemberships = fetchedUser.getMembership();

			if (userMemberships.isEmpty()) {
				throw new IllegalStateException("User has no membership information");
			}

			// Assuming the first membership in the list is the one we're interested in
			Membership membership = userMemberships.get(0);
			int maxBooksAllowed = membership.getMembershipType().getMaxBooks();

			System.out.println("Max Books Allowed: " + maxBooksAllowed);

			// Return true if the user has reached or exceeded the borrowing limit
			return booksBorrowed >= maxBooksAllowed;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
