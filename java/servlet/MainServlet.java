package servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import util.HIbernateConfig;

public class MainServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try (Session session = HIbernateConfig.getSession().openSession()) {
            response.getWriter().println("Connected to the database successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

