package alexander.ivanov.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Logger;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Logger.getGlobal().warning("STARTING  ContextListener");
        /*sce.getServletContext().setAttribute("jspPathPatterm", "/jsp%s.jsp");
        *//*sce.getServletContext().setAttribute("contextPath", "/servlet-jsp-example");*//*
        sce.getServletContext().setAttribute("contextPath", "/");
        sce.getServletContext().setAttribute("home", "home");
        sce.getServletContext().setAttribute("login", "login");
        sce.getServletContext().setAttribute("logout", "logout");
        sce.getServletContext().setAttribute("registration", "registration");
        Logger.getGlobal().warning("jspPathPatterm = " + sce.getServletContext().getAttribute("jspPathPatterm"));
        Logger.getGlobal().warning("contextPath = " + sce.getServletContext().getAttribute("contextPath"));
        Logger.getGlobal().warning("home = " + sce.getServletContext().getAttribute("home"));
        Logger.getGlobal().warning("login = " + sce.getServletContext().getAttribute("login"));
        Logger.getGlobal().warning("logout = " + sce.getServletContext().getAttribute("logout"));
        Logger.getGlobal().warning("registration = " + sce.getServletContext().getAttribute("registration"));*/
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /*sce.getServletContext().removeAttribute("jspPathPatterm");
        sce.getServletContext().removeAttribute("contextPath");
        sce.getServletContext().removeAttribute("home");
        sce.getServletContext().removeAttribute("login");
        sce.getServletContext().removeAttribute("logout");
        sce.getServletContext().removeAttribute("registration");*/
    }
}
