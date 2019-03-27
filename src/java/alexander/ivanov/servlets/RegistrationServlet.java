package alexander.ivanov.servlets;

import alexander.ivanov.models.UserMapping;
import alexander.ivanov.models.object.User;
import alexander.ivanov.servlets.checks.RegisterCheck;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext()
                .getRequestDispatcher(String.format((String)getServletContext().getAttribute("jspPathPatterm"),
                        req.getServletPath()))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("name");
        String password = req.getParameter("password");
        String confirm = req.getParameter("confirm");

        /*log("user = " + user + "\n password = " + password + "\n confirm = " + confirm);*/

        RegisterCheck check = new RegisterCheck();
        check.checkAttributes(user, password, confirm);

        if (check.hasErrors()) {
            req.getRequestDispatcher(
                    String.format(
                            (String)getServletContext().getAttribute("jspPathPatterm")
                            , req.getServletPath())).include(req, resp);

            resp.getWriter().format("<font color=\"red\">%s</font>", check.arrayToString("<br>"));
            req.getRequestDispatcher(req.getContextPath() + req.getServletPath()).include(req, resp);
            req.getRequestDispatcher(req.getContextPath() + req.getServletPath()).forward(req, resp);
        } else {
            new UserMapping().add(new User(user, password));
            HttpSession session = req.getSession(false);
            session.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + req.getServletContext().getAttribute("home"));
        }
    }
}
