package servlet.videos;

//import dao.FavoriteDAO;
import dao.VideoDAO;
//import entity.Favorite;
import entity.Users;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/")
public class HomeServlet extends HttpServlet {

    private VideoDAO videoDAO = new VideoDAO();
//    private FavoriteDAO favoriteDAO = new FavoriteDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Users user = (Users) request.getSession().getAttribute("currentUser");

//        if (user != null) {
//            List<Favorite> list = favoriteDAO.findFavoritesByUserId(user.getId());
//            Set<String> likedSet = new HashSet<>();
//            for (Favorite f : list) {
//                likedSet.add(f.getVideo().getId());
//            }
//            request.setAttribute("listLiked", likedSet);
//        }

        request.setAttribute("listVideo", videoDAO.findAllActive());
        //TEST DAO/ ///////////////////
//        System.out.println("Video: " +videoDAO);
//        if (videoDAO != null) {
//        	System.out.println("link: " +videoDAO.findById("VID001").toString());
//        	System.out.println("link: " +videoDAO.findAll().toString());
//        	System.out.println("link: " +videoDAO.findById("VID001").toString());
//        }
        
        
        request.getRequestDispatcher("/views/client/home.jsp").forward(request, response);
    }
}
