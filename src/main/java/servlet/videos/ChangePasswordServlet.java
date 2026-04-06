package servlet.videos;


import java.io.IOException;

import dao.UsersDAO;

import entity.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {

    private UsersDAO usersDAO = new UsersDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/views/client/changePassword.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");
        Users user = usersDAO.findByIdOrEmail(id);

        String oldPass = req.getParameter("password");
        String newPass = req.getParameter("newPassword");
        String confirm = req.getParameter("confirmNewPassword");

        if (!user.getPassword().equals(oldPass)) {
            req.setAttribute("errorPassword", "Sai mật khẩu");
        } else if (!newPass.equals(confirm)) {
            req.setAttribute("errorConfirmNewPassword", "Không trùng");
        } else {
            user.setPassword(newPass);
            usersDAO.update(user);
            req.setAttribute("success", "Đổi thành công");
        }

        req.getRequestDispatcher("/views/client/changePassword.jsp").forward(req, resp);
    }
}
