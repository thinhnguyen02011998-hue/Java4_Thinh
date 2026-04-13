package servlet.videos;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import dao.UsersDAO;
import entity.Users;

@WebServlet({
    "/admin-users/index",    // Trang chủ admin-users
    "/admin-users/edit/*",   // Nhấn Edit từ bảng
    "/admin-users/update",   // Nhấn Update form
    "/admin-users/delete",   // Nhấn Delete form
    "/admin-users/reset"     // Nhấn Reset form
})
public class UserManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    UsersDAO dao = new UsersDAO();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String uri = req.getRequestURI();
        Users userForm = new Users();
        boolean isEdit = false; // Mặc định là trạng thái khởi đầu (chưa chọn user để sửa)

        try {
            if (uri.contains("edit")) {
                // Sự kiện [Edit].Click: Hiển thị người dùng lên form
                String id = uri.substring(uri.lastIndexOf("/") + 1);
                userForm = dao.findById(id);
                isEdit = true;
            } 
            else if (uri.contains("update")) {
                // Sự kiện [Update].Click: Cập nhật thông tin
                BeanUtils.populate(userForm, req.getParameterMap());
                dao.update(userForm);
                isEdit = true; // Sau khi update vẫn giữ trạng thái đang edit
                req.setAttribute("message", "Cập nhật tài khoản " + userForm.getId() + " thành công!");
            } 
            else if (uri.contains("delete")) {
                // Sự kiện [Delete].Click: Xóa người dùng và về trạng thái khởi đầu
                String id = req.getParameter("id");
                dao.deleteById(id);
                userForm = new Users();
                isEdit = false;
                req.setAttribute("message", "Xóa tài khoản thành công!");
            } 
            else if (uri.contains("reset")) {
                // Sự kiện Reset: Form trống, vô hiệu hóa Update/Delete
                userForm = new Users();
                isEdit = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Lỗi xử lý: " + e.getMessage());
        }

        // Lấy danh sách toàn bộ người dùng để hiển thị lên bảng (Video List tab)
        // Bạn có thể chỉnh lại dao.findAll() để lấy Top 10 hoặc phân trang sau
        List<Users> list = dao.findAll();

        // Gửi dữ liệu qua JSP
        req.setAttribute("isEdit", isEdit); // Để disable/enable nút
        req.setAttribute("form", userForm); // Dữ liệu đổ vào các input
        req.setAttribute("users", list);    // Dữ liệu đổ vào table

        req.getRequestDispatcher("/views/client/admin-users.jsp").forward(req, resp);
    }
}