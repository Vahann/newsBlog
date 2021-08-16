package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        User user = userManager.getUserByNameAndPassword(name, password);

        if (user == null) {
            req.getSession().setAttribute("massage", "Error,user is null");
            resp.sendRedirect("/"); //"/home"
        } else {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/home");
        }
    }
}
