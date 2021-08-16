package servlet;

import manager.NewsManager;
import model.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/home")

public class HomeServlet extends HttpServlet {
    private NewsManager newsManager = new NewsManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<News> news = newsManager.getNews(); //=newsManager.getNews

        req.setAttribute("news", news);
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
    }
}
