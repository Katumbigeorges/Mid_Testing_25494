package servlet;

import Controller.BookController;
import Controller.ShelfController;
import Service.ShelfDao;
import model.Book;
import model.Shelf;
import enums.EBookStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet("/addBook")
public class BookServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String title = request.getParameter("title");
        String publicationName = request.getParameter("publicationName");
        String isbnCode = request.getParameter("isbnCode");
        String editionStr = request.getParameter("edition");
        String shelfId = request.getParameter("shelfId");

        // Validate inputs (add your validation logic as necessary)
        if (title == null || publicationName == null || isbnCode == null || editionStr == null || shelfId == null) {
            // Handle invalid input (return error or redirect)
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("addBook.jsp").forward(request, response);
            return;
        }

        // Create a new Book object
        Book book = new Book();
        book.setId(UUID.randomUUID()); // Generate a unique book ID
        book.setTitle(title);
        book.setPublication_name(publicationName);
        book.setISBNCode(isbnCode);
        book.setEdition(Integer.parseInt(editionStr));
//        Shelf shelf = new Shelf();
        UUID shelfIds = UUID.fromString(shelfId);
        ShelfController shelfController = new ShelfController();
        Shelf shelf = shelfController.getShelfById(shelfIds);
        book.setShelf(shelf); // Assuming Shelf has a constructor that takes ID

        BookController bookController = new BookController();
        // Call the controller method to save the book (implement the save logic accordingly)
        String result = bookController.saveBook(book,shelf);
        
        // Redirect or forward based on the result
        if ("success".equals(result)) {
            response.sendRedirect("success.jsp"); // Redirect to a success page
        } else {
            request.setAttribute("error", "Failed to add the book.");
            request.getRequestDispatcher("addBook.jsp").forward(request, response);
        }
    }
}
