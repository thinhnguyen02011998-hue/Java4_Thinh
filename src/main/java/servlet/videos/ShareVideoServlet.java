package servlet.videos;

import java.io.IOException;

import java.util.Date;

import dao.ShareDAO;

import dao.VideoDAO;

import entity.Share;
import entity.Users;
import entity.Video;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/shareVideo")
public class ShareVideoServlet extends HttpServlet {

    private ShareDAO shareDAO = new ShareDAO();
    private VideoDAO videoDAO = new VideoDAO();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Users user = (Users) req.getSession().getAttribute("currentUser");

        String email = req.getParameter("email");
        String videoId = req.getParameter("videoId");

        Video video = videoDAO.findById(videoId);

        String link = req.getContextPath() + "/videoDetail?id=" + videoId;

        String body = "<a href='http://localhost:8080" + link + "'>Xem video</a>";

        utils.Mailer.send(email, "Video hay", body);

        shareDAO.create(new Share(user, video, email, new Date()));

        resp.sendRedirect(req.getHeader("Referer"));
    }
}
