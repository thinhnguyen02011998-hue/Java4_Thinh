package servlet.videos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VideoDAO;
import entity.Video;

@WebServlet("/videoDetail")
public class VideoDetailServlet extends HttpServlet {

    private VideoDAO videoDAO = new VideoDAO(); // Phải dùng kiểu VideoDAO

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");
        
        // Gọi hàm đã thêm ở bước 2
        Video video = videoDAO.findByIdWithStats(id);

        if (video == null) {
            resp.sendRedirect(req.getContextPath() + "/index");
            return;
        }

        // Tăng view và cập nhật
        video.setViews(video.getViews() + 1);
        videoDAO.update(video);

        req.setAttribute("video", video);
        req.setAttribute("listVideoRandom", videoDAO.random10Video(id));
        req.getRequestDispatcher("/views/client/videoDetail.jsp").forward(req, resp);
    }
}