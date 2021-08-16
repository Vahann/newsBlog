package servlet;

import manager.NewsManager;
import model.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/singleNews")
public class SingleNewsServlet extends HttpServlet {
    private NewsManager newsManager = new NewsManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        News news = newsManager.getNewsById(id);

        req.setAttribute("news", news);
        req.getRequestDispatcher("/WEB-INF/singleNews.jsp").forward(req, resp);
    }

}
