package servlet;

import manager.NewsManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteNews")

public class DeleteNewsServlet extends HttpServlet {
    private NewsManager newsManager = new NewsManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int newsId = Integer.parseInt(req.getParameter("id"));
        newsManager.deleteNews(newsId);
        req.getSession().setAttribute("massage", "news deleted");
        resp.sendRedirect("/home");
    }
}
