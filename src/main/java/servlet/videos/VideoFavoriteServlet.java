package servlet.videos;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VideoDAO;
import entity.Users;
import entity.Video;

@WebServlet("/videoFavorite")
public class VideoFavoriteServlet extends HttpServlet {

    private VideoDAO videoDAO = new VideoDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Users user = (Users) req.getSession().getAttribute("currentUser");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/signin");
            return;
        }

        // SỬA TẠI ĐÂY: Gọi hàm mới có Stats để hiển thị số Like/Share
        List<Video> list = videoDAO.findVideosLikedByUserWithStats(user.getId());
        req.setAttribute("listVideFavorite", list);

        req.getRequestDispatcher("/views/client/videoFavorite.jsp").forward(req, resp);
    }
}
