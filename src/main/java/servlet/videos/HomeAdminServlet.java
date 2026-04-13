package servlet.videos;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VideoDAO;
import dao.UsersDAO;
import entity.Video;
import entity.Users;

@WebServlet("/admin-home")
public class HomeAdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    VideoDAO vdao = new VideoDAO();
    UsersDAO udao = new UsersDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy số lượng thực tế từ Database để hiển thị lên Dashboard
        List<Video> videos = vdao.findAll();
        List<Users> users = udao.findAll();

        req.setAttribute("videoCount", videos.size());
        req.setAttribute("userCount", users.size());
        
        // Chuyển hướng sang trang giao diện Home Admin
        req.getRequestDispatcher("/views/client/admin-index.jsp").forward(req, resp);
    }
}