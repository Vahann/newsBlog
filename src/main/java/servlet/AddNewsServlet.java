package servlet;

import manager.NewsManager;
import model.News;
import model.NewsCategory;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(urlPatterns = "/addNews")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100
)

public class AddNewsServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "C:\\Lesson Java\\Project\\newsBlog\\src\\main\\java\\images\\colorful_umbrellas-wallpaper-1920x1200.jpg";

    private NewsManager newsManager = new NewsManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/addNews.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        Part filePart = req.getPart("picture");
        String fileName = filePart.getSubmittedFileName();
        String picUrl = System.currentTimeMillis() + "_" + fileName;
        filePart.write(UPLOAD_DIR + picUrl);

        String category = req.getParameter("category");
        category = category.toUpperCase();
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String text = req.getParameter("text");
        //


        System.out.println("AddNews Category-n toUpperCase " + category);
        News news = News.builder()
                .newsCategory(NewsCategory.valueOf(category))
                .title(title)
                .description(description)
                .text(text)
                .picUrl(picUrl)
                .user(user)
                .build();
//        System.out.println(NewsCategory.valueOf(category));
        newsManager.addNews(news);
        req.getSession().setAttribute("massage", "news was added");
        resp.sendRedirect("/addNews");
    }
}
