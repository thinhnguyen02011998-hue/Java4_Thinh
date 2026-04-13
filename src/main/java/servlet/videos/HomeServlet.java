package servlet.videos;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FavoriteDAO;
import dao.VideoDAO;
import entity.Favorite;
import entity.Users;
import entity.Video;

@WebServlet("/") 
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private VideoDAO videoDAO = new VideoDAO();
    private FavoriteDAO favoriteDAO = new FavoriteDAO(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       
        Users user = (Users) request.getSession().getAttribute("currentUser");

     
        Set<String> likedSet = new HashSet<>();
        if (user != null) {
            // Lấy danh sách Favorite từ DB của User này
            List<Favorite> listFav = favoriteDAO.findFavoritesByUserId(user.getId());
            for (Favorite f : listFav) {
             
                likedSet.add(f.getVideo().getId());
            }
        }

        request.setAttribute("listLiked", likedSet);

        List<Video> listVideo = videoDAO.findAllWithStats();
        request.setAttribute("listVideo", listVideo);

        request.getRequestDispatcher("/views/client/home.jsp").forward(request, response);
    }
}