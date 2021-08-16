package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(urlPatterns = "/getImage")
public class GetImageServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "C:\\Lesson Java\\Project\\newsBlog\\src\\main\\java\\images\\colorful_umbrellas-wallpaper-1920x1200.jpg";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String picUrl = req.getParameter("picUrl");

        String filePath = UPLOAD_DIR + picUrl;
        File downloadFile = new File(filePath);
        FileInputStream fIStream = new FileInputStream(downloadFile);

        resp.setContentType("image/jpeg");
        resp.setContentLength((int) downloadFile.length());

        OutputStream outputStream = resp.getOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = fIStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);

        }
        fIStream.close();
        outputStream.close();
    }

}
