package servlet;

import Controller.MembershipController;
import Service.MemberShipTypeDao;
import Service.UserDao;
import enums.EStatus;
import model.MemberShipType;
import model.Membership;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@WebServlet("/membership")
public class MembershipServlet extends HttpServlet {
    private final MembershipController membershipController = new MembershipController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String membershipCode = request.getParameter("membershipCode");
            EStatus membershipStatus = EStatus.valueOf(request.getParameter("membershipStatus").toUpperCase());
            Date registrationDate = Date.valueOf(request.getParameter("registrationDate"));
            Date expiringTime = Date.valueOf(request.getParameter("expiringTime"));
            UUID userId = UUID.fromString(request.getParameter("userId"));
            UUID membershipTypeId = UUID.fromString(request.getParameter("membershipTypeId"));

            UserDao userDao = new UserDao();
            User user = userDao.getUserById(userId);

            MemberShipTypeDao memberShipTypeDao = new MemberShipTypeDao();
            MemberShipType membershipType = memberShipTypeDao.getMembershipTypeById(membershipTypeId);

            if (user != null && membershipType != null) {
                Membership membership = new Membership();
                membership.setMembershipCode(membershipCode);
                membership.setMembershipStatus(membershipStatus);
                membership.setRegistrationDate(registrationDate);
                membership.setExpiringTime(expiringTime);
                membership.setUser(user);
                membership.setMembershipType(membershipType);

                String result = membershipController.saveMembership(membership);
                response.getWriter().write(result);
            } else {
                response.getWriter().write("User or Membership Type not found.");
            }
        } catch (Exception e) {
            response.getWriter().write("Error creating membership: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Membership> memberships = membershipController.getAllMembership();
        request.setAttribute("memberships", memberships);
        request.getRequestDispatcher("/viewMemberships.jsp").forward(request, response);
    }
}
