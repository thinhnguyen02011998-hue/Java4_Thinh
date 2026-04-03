package servlet.videos;



import java.io.IOException;

//import dao.FavoriteDAO;

import dao.VideoDAO;

import entity.Users;
import entity.Video;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/videoDetail")
public class VideoDetailServlet extends HttpServlet {

    private VideoDAO videoDAO = new VideoDAO();
//    private FavoriteDAO favoriteDAO = new FavoriteDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");
        Video video = videoDAO.findById(id);
     // Tách ID video để nhúng iframe
        if (video.getLink() != null && video.getLink().contains("&")) {
            String videoId = video.getLink().split("&")[0]; // lấy phần trước dấu &
            video.setLink(videoId);
        }
        
        

        if (video == null) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        video.setViews(video.getViews() + 1);
        videoDAO.update(video);

        req.setAttribute("video", video);

        Users user = (Users) req.getSession().getAttribute("currentUser");
//        if (user != null) {
//            boolean liked = favoriteDAO.findByUserIdAndVideoId(user.getId(), id) != null;
//            req.setAttribute("likedVideo", liked);
//        }

        req.setAttribute("listVideoRandom", videoDAO.random10Video(id));
        req.getRequestDispatcher("/views/client/videoDetail.jsp").forward(req, resp);
    }
}
