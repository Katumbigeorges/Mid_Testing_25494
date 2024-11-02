package util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.hibernate.SessionFactory;

public class HibernateListener implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            SessionFactory sessionFactory = HIbernateConfig.getSession();
            event.getServletContext().setAttribute("SessionFactory", sessionFactory);
            System.out.println("Hibernate SessionFactory initialized successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        SessionFactory sessionFactory = (SessionFactory) event.getServletContext().getAttribute("SessionFactory");
        if (sessionFactory != null && sessionFactory.isOpen()) {
            sessionFactory.close();
            System.out.println("Hibernate SessionFactory closed.");
        }
    }
}
