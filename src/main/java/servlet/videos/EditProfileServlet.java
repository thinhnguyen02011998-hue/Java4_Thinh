package servlet.videos;


import java.io.IOException;

import dao.UsersDAO;

import entity.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editProfile")
public class EditProfileServlet extends HttpServlet {

    private UsersDAO usersDAO = new UsersDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Users user = (Users) req.getSession().getAttribute("currentUser");
        req.setAttribute("user", user);
        req.getRequestDispatcher("/views/client/editProfile.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");
        Users user = usersDAO.findByIdOrEmail(id);

        String email = req.getParameter("email");
        Users check = usersDAO.findByIdOrEmail(email);

        if (check != null && !check.getId().equals(id)) {
            req.setAttribute("errorEmail", "Email đã tồn tại");
        } else {
            user.setFullname(req.getParameter("fullname"));
            user.setEmail(email);
            usersDAO.update(user);
            req.setAttribute("success", "Cập nhật thành công");
        }

        req.setAttribute("user", user);
        req.getRequestDispatcher("/views/client/editProfile.jsp").forward(req, resp);
    }
}
