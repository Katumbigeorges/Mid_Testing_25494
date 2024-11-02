package servlet;

import Controller.BorrowerController;
import Service.BookDao;
import Service.ShelfDao;
import Service.UserDao;
import enums.EGender;
import enums.EStatus;
import model.Book;
import model.Borrower;
import model.MemberShipType;
import model.Membership;
import model.Shelf;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet("/borrower")
public class BorrowerServlet extends HttpServlet {
    private final BorrowerController borrowerController = new BorrowerController();
    private final UserDao userDao = new UserDao();
    private final BookDao bookDao = new BookDao();
    private final ShelfDao shelfDao = new ShelfDao();
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String phoneNumber = request.getParameter("phoneNumber");
            EGender gender = EGender.valueOf(request.getParameter("gender").toUpperCase());
            Date dueDate = dateFormatter.parse(request.getParameter("dueDate"));
            int fine = Integer.parseInt(request.getParameter("fine"));
            Date pickupDate = dateFormatter.parse(request.getParameter("pickupDate"));
            Date returnDate = dateFormatter.parse(request.getParameter("returnDate"));
            UUID userId = UUID.fromString(request.getParameter("userId"));
            UUID bookId = UUID.fromString(request.getParameter("bookId"));

            User user = userDao.getUserById(userId);
            Book book = bookDao.getBookById(bookId);
            if (user != null && book != null) {
                if (canUserBorrowMoreBooks(user)) {
                    Shelf shelf = book.getShelf();
                    if (shelf.getAvailable_stock() > 0) {
                        Borrower borrower = new Borrower();
                        borrower.setId(UUID.randomUUID());
                        borrower.setFirst_name(firstName);
                        borrower.setLast_name(lastName);
                        borrower.setPhone_number(phoneNumber);
                        borrower.setGender(gender);
                        borrower.setDueDate(dueDate);
                        borrower.setFine(fine);
                        borrower.setPickupDate(pickupDate);
                        borrower.setReturnDate(returnDate);
                        borrower.setUser(user);
                        borrower.setBook(book);

                        shelf.setBorrowed_number(shelf.getBorrowed_number() + 1);
                        shelf.setAvailable_stock(shelf.getAvailable_stock() - 1);
                        shelfDao.saveShelf(shelf);
                        
                        // Introduce a 5-second delay before fetching User and Book
                        Thread.sleep(5000);

                        String result = borrowerController.saveBorrower(borrower);
                        response.getWriter().write(result);
                    } else {
                        response.getWriter().write("No available stock to borrow.");
                    }
                } else {
                    response.getWriter().write("User has reached their borrowing limit.");
                }
            } else {
                response.getWriter().write("User or Book not found.");
            }
        } catch (ParseException e) {
            response.getWriter().write("Date format error. Use yyyy-MM-dd format.");
        } catch (Exception e) {
            response.getWriter().write("Error creating borrower: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Borrower> borrowers = borrowerController.getAllBorrowers();
        request.setAttribute("borrowers", borrowers);
        request.getRequestDispatcher("/viewBorrowers.jsp").forward(request, response);
    }

    private boolean canUserBorrowMoreBooks(User user) {
        int currentBorrowedCount = user.getBorrowers().size();
        Membership activeMembership = user.getMembership().stream()
                .filter(m -> m.getMembershipStatus() == EStatus.APPROVED)
                .findFirst()
                .orElse(null);

        if (activeMembership != null) {
            int maxBooksAllowed = getMaxBooksAllowed(activeMembership.getMembershipType());
            return currentBorrowedCount < maxBooksAllowed;
        }
        return false;
    }

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
}
