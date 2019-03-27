package alexander.ivanov.servlets;

import alexander.ivanov.models.UserMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute("jspPathPatterm", "/jsp%s.jsp");
        getServletContext().setAttribute("contextPath", "/servlet-jsp-example");
        getServletContext().setAttribute("home", "/home");
        getServletContext().setAttribute("login", "/login");
        getServletContext().setAttribute("logout", "/logout");
        getServletContext().setAttribute("registration", "/registration");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log(String.format((String)getServletContext().getAttribute("jspPathPatterm"), req.getServletPath()));
        /*log("!!!jspPathPatterm " + req.getServletContext().getAttribute("jspPathPatterm"));
        log("!!!jspPathPatterm " + req.getParameter("jspPathPatterm"));*/

        req.setAttribute("allUsers", new UserMapping().findAll());

        RequestDispatcher dispatcher;
        (dispatcher = req.getServletContext()
                .getRequestDispatcher(String.format((String)getServletContext().getAttribute("jspPathPatterm"),
                        req.getServletPath()))).include(req, resp);

        dispatcher.forward(req, resp);
        /*req.getServletContext().getRequestDispatcher("/jsp" + req.getServletPath() + ".jsp").forward(req,resp);*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    public void destroy() {
        getServletContext().removeAttribute("jspPathPatterm");
        getServletContext().removeAttribute("contextPath");
        getServletContext().removeAttribute("home");
        getServletContext().removeAttribute("login");
        getServletContext().removeAttribute("logout");
        getServletContext().removeAttribute("registration");
    }
}
