package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import model.Location;
import util.HIbernateConfig;

@WebServlet("/TestLocationServlet")
public class TestLocationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

//    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
//            throws ServletException, IOException {
//        
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        try (Session session = HIbernateConfig.getSession().openSession()) {
//			System.out.println("Connected to the database successfully!");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return; // Exit the program if the connection fails
//		}
//        
//        try {
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Form Data Test Results</title>");
//            out.println("<style>");
//            out.println("body { font-family: Arial, sans-serif; margin: 20px; }");
//            out.println("table { border-collapse: collapse; width: 100%; }");
//            out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
//            out.println("th { background-color: #f2f2f2; }");
//            out.println("h2 { color: #333; }");
//            out.println(".back-button { margin-top: 20px; }");
//            out.println("</style>");
//            out.println("</head>");
//            out.println("<body>");
//            
//            // Header
//            out.println("<h2>Form Data Received:</h2>");
//            
//            // Table for form parameters
//            out.println("<table>");
//            out.println("<tr><th>Parameter Name</th><th>Parameter Value</th></tr>");
//            
//            // Get all parameter names
//            Enumeration<String> parameterNames = request.getParameterNames();
//            
//            // Display each parameter and its value
//            while (parameterNames.hasMoreElements()) {
//                String paramName = parameterNames.nextElement();
//                String paramValue = request.getParameter(paramName);
//                out.println("<tr>");
//                out.println("<td>" + paramName + "</td>");
//                out.println("<td>" + (paramValue != null ? paramValue : "null") + "</td>");
//                out.println("</tr>");
//            }
//            out.println("</table>");
//            
//            // Request Information
//            out.println("<h2>Request Information:</h2>");
//            out.println("<table>");
//            out.println("<tr><th>Information</th><th>Value</th></tr>");
//            out.println("<tr><td>Request Method</td><td>" + request.getMethod() + "</td></tr>");
//            out.println("<tr><td>Request URI</td><td>" + request.getRequestURI() + "</td></tr>");
//            out.println("<tr><td>Context Path</td><td>" + request.getContextPath() + "</td></tr>");
//            out.println("<tr><td>Servlet Path</td><td>" + request.getServletPath() + "</td></tr>");
//            out.println("<tr><td>Content Type</td><td>" + request.getContentType() + "</td></tr>");
//            out.println("<tr><td>Character Encoding</td><td>" + request.getCharacterEncoding() + "</td></tr>");
//            out.println("</table>");
//            
//            // Headers Information
//            out.println("<h2>Request Headers:</h2>");
//            out.println("<table>");
//            out.println("<tr><th>Header Name</th><th>Header Value</th></tr>");
//            
//            Enumeration<String> headerNames = request.getHeaderNames();
//            while (headerNames.hasMoreElements()) {
//                String headerName = headerNames.nextElement();
//                String headerValue = request.getHeader(headerName);
//                out.println("<tr>");
//                out.println("<td>" + headerName + "</td>");
//                out.println("<td>" + headerValue + "</td>");
//                out.println("</tr>");
//            }
//            out.println("</table>");
//            
//            // Back button
//            out.println("<div class='back-button'>");
//            out.println("<a href='" + request.getContextPath() + "/location.jsp'>");
//            out.println("<button type='button'>Back to Form</button>");
//            out.println("</a>");
//            out.println("</div>");
//            
//            out.println("</body>");
//            out.println("</html>");
//            
//        } finally {
//            out.close();
//        }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try (Session session = HIbernateConfig.getSession().openSession()) {
            System.out.println("Connected to the database successfully!");
            
            // Start a transaction
            session.beginTransaction();

            // Example: Persist form parameters as `Location` entities
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                String paramValue = request.getParameter(paramName);
                
                // Create Location object and set values
                Location location = new Location();
                location.setLocationCode(paramValue);
                location.setLocationName(paramValue);

                // Save to the database
                session.save(location);
            }

            // Commit the transaction
            session.getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Redirect GET requests to the form page
        response.sendRedirect(request.getContextPath() + "/location.jsp");
    }
}