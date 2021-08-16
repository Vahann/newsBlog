package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("re-password");
        if (!password.equals(rePassword)) {
            req.getSession().setAttribute("massage", "Error Password");
            resp.sendRedirect("/");
        } else {
            User user = User.builder()
                    .name(name)
                    .surname(surname)
                    .password(password)
                    .build();
            userManager.addUser(user);

            req.getSession().setAttribute("massage", "register successfully");
            resp.sendRedirect("/");
        }
    }
}
