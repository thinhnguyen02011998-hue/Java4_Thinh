package servlet.videos;


import java.io.IOException;

import dao.VideoDAO;

import entity.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        req.setAttribute("listVideFavorite",
                videoDAO.findVideosLikedByUser(user.getId()));

        req.getRequestDispatcher("/views/client/videoFavorite.jsp").forward(req, resp);
    }
}
