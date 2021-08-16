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

@WebServlet(urlPatterns = "/singleNewsByCategory")
public class SingleNewsByCategoryServlet extends HttpServlet {
    private NewsManager newsManager = new NewsManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String category = req.getParameter("category");
//        System.out.println("SINGLENEWS GET PARAMETER"+category);

        List<News> news = newsManager.getNewsByCategory(category);
        System.out.println(news);


        req.setAttribute("news", news);
        req.getRequestDispatcher("/WEB-INF/singleNewsByCategory.jsp").forward(req, resp);
    }
}
