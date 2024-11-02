package servlet;

import model.Borrower;
import Service.BookDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/lateFees")
public class LateFeeServlet extends HttpServlet {
    private BookDao bookDao = new BookDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the list of overdue borrowers (assuming a method exists in BookDao)
        List<Borrower> overdueBorrowers = bookDao.getOverdueBorrowers();
        
        // Calculate late fees for each overdue borrower
        for (Borrower borrower : overdueBorrowers) {
            int lateFee = bookDao.calculateAndApplyLateFees(borrower);
            borrower.setLateChargesFees(lateFee); // Set the calculated late fee in the borrower object
        }

        // Set the overdue borrowers in the request
        request.setAttribute("overdueBorrowers", overdueBorrowers);

        // Forward the request to the JSP page for display
        request.getRequestDispatcher("/lateFees.jsp").forward(request, response);
    }
}
