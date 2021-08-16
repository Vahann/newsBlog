package servlet;

import manager.NewsManager;
import model.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(urlPatterns = "/updateNews")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)
public class UpdateNewsServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "C:\\Lesson Java\\Project\\newsBlog\\src\\main\\java\\images\\colorful_umbrellas-wallpaper-1920x1200.jpg";

    private NewsManager newsManager = new NewsManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int newsId = Integer.parseInt(req.getParameter("id"));
        News news = newsManager.getNewsById(newsId);
        req.setAttribute("news", news);
        req.getRequestDispatcher("/WEB-INF/updateNews.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int newsId = Integer.parseInt(req.getParameter("id"));

        News newsById = newsManager.getNewsById(newsId);
        if (newsById != null) {
            Part filePart = req.getPart("picture");
            String fileName = filePart.getSubmittedFileName();
            String picUrl = System.currentTimeMillis() + "_" + fileName;
            filePart.write(UPLOAD_DIR + picUrl);

            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String text = req.getParameter("text");
            String userId = req.getParameter("userId");

            News news = News.builder()
                    .id(newsId)
                    .title(title)
                    .description(description)
                    .text(text)
                    .picUrl(picUrl)
                    .build();

            newsManager.updateNews(news);
            req.getSession().setAttribute("massage", "News UPDATE");

            resp.sendRedirect("/home");
        }
    }
}
