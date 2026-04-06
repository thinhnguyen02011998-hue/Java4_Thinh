package servlet.videos;

import java.io.IOException;

import java.util.Date;


import dao.FavoriteDAO;

import dao.VideoDAO;
import entity.Favorite;

import entity.Users;
import entity.Video;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/likeVideo")
public class LikeVideoServlet extends HttpServlet {

    private FavoriteDAO favoriteDAO = new FavoriteDAO();
    private VideoDAO videoDAO = new VideoDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Users user = (Users) req.getSession().getAttribute("currentUser");
        String videoId = req.getParameter("id");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/signin");
            return;
        }

        Video video = videoDAO.findById(videoId);

        Favorite f = favoriteDAO.findByUserIdAndVideoId(user.getId(), videoId);

        if (f != null) {
            favoriteDAO.deleteById(f.getId());
        } else {
            favoriteDAO.create(new Favorite(user, video, new Date()));
        }

        resp.sendRedirect(req.getHeader("Referer"));
    }
}
