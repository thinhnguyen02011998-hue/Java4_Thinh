package servlet.videos;

import java.io.IOException;

import dao.UsersDAO;

import entity.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UsersDAO usersDAO = new UsersDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/client/register.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");
        String email = req.getParameter("email");

        if (usersDAO.findByIdOrEmail(id) != null) {
            req.setAttribute("errorUsername", "Username tồn tại");
        } else if (usersDAO.findByIdOrEmail(email) != null) {
            req.setAttribute("errorEmail", "Email tồn tại");
        } else {
            usersDAO.create(new Users(id,
                    req.getParameter("password"),
                    email,
                    req.getParameter("fullname"),
                    false));
            req.setAttribute("success", "Đăng ký thành công");
        }

        req.getRequestDispatcher("/views/client/register.jsp").forward(req, resp);
    }
}
