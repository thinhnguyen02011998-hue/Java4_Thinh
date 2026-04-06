package servlet.videos;

import java.io.IOException;

import dao.UsersDAO;

import entity.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Mailer;
@WebServlet("/forgotPassword")
public class ForgotPasswordServlet extends HttpServlet {

    private UsersDAO usersDAO = new UsersDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/client/forgotPassword.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");
        String email = req.getParameter("email");

        Users user = usersDAO.findByIdOrEmail(id);

        if (user != null && user.getEmail().equals(email)) {
            String body = "<h3>Password: " + user.getPassword() + "</h3>";
            utils.Mailer.send(email, "Your password", body);
            req.setAttribute("successMessage", "Đã gửi mail!");
        } else {
            req.setAttribute("errorMessage", "Sai thông tin!");
        }

        req.getRequestDispatcher("/views/client/forgotPassword.jsp").forward(req, resp);
    }
}