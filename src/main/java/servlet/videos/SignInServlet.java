package servlet.videos;

import dao.UsersDAO;

import entity.Users;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
    private UsersDAO usersDAO = new UsersDAO();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/client/login.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        Users user = usersDAO.findByIdOrEmail(id);
        if (user != null && user.getPassword().equals(password)) {
            req.getSession().setAttribute("currentUser", user);
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("errorMessage", "Sai tài khoản hoặc mật khẩu");
            req.getRequestDispatcher("/views/client/login.jsp").forward(req, resp);
        }
    }
}
