package servlet.videos;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FavoriteDAO;
import dao.ShareDAO;
import dao.VideoDAO;
import dto.ReportFavoriteUser;
import dto.ReportFavorites;
import dto.ReportShareFriend;
import entity.Video;

@WebServlet("/admin-reports")
public class ReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    FavoriteDAO fdao = new FavoriteDAO();
    ShareDAO sdao = new ShareDAO();
    VideoDAO vdao = new VideoDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Luôn lấy danh sách tất cả Video để hiển thị trong các Select Box
        List<Video> videoList = vdao.findAll();
        req.setAttribute("videos", videoList);

        // 2. Tab 1: Mặc định luôn load báo cáo tổng hợp Favorite
        List<ReportFavorites> favList = fdao.generateFavoriteReport();
        req.setAttribute("favList", favList);

        // 3. Xử lý Lọc theo VideoId (Dùng chung cho Tab 2 và Tab 3)
        String videoId = req.getParameter("videoId");
        if (videoId != null && !videoId.isEmpty()) {
            // Lọc Tab 2: Danh sách người dùng thích Video này
            List<ReportFavoriteUser> favUsers = fdao.findUsersByVideoId(videoId);
            req.setAttribute("favUsers", favUsers);

            // Lọc Tab 3: Danh sách người gửi/nhận chia sẻ Video này
            List<ReportShareFriend> shareFriends = sdao.findReportShareFriendsByVideoId(videoId);
            req.setAttribute("shareFriends", shareFriends);
            
            // Giữ lại videoId đã chọn để hiển thị trên giao diện
            req.setAttribute("selectedVideoId", videoId);
        }

        req.getRequestDispatcher("/views/client/admin-reports.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}