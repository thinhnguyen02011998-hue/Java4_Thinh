package servlet.videos;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import dao.VideoDAO;
import entity.Video;

@WebServlet({ 
    "/admin-video/index",    // Trang chính
    "/admin-video/create",   // Thêm mới
    "/admin-video/update",   // Cập nhật
    "/admin-video/delete",   // Xóa
    "/admin-video/edit/*",   // Chọn để sửa
    "/admin-video/reset"     // Làm mới form
})
public class VideoManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    VideoDAO dao = new VideoDAO();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String uri = req.getRequestURI();
        Video video = new Video();
        boolean isEdit = false; // Biến điều khiển trạng thái nút bấm

        try {
            if (uri.contains("edit")) {
                // [Edit].Click: Hiển thị tiểu phẩm lên form, vô hiệu hóa Create
                String id = uri.substring(uri.lastIndexOf("/") + 1);
                video = dao.findById(id);
                isEdit = true;
            } 
            else if (uri.contains("create")) {
                // [Create].Click: Thêm mới, sau đó về trạng thái khởi đầu
                BeanUtils.populate(video, req.getParameterMap());
                dao.create(video);
                video = new Video(); // Reset form sau khi tạo
                isEdit = false;
                req.setAttribute("message", "Thêm mới thành công!");
            } 
            else if (uri.contains("update")) {
                // [Update].Click: Cập nhật và giữ nguyên trạng thái Edit
                BeanUtils.populate(video, req.getParameterMap());
                dao.update(video);
                isEdit = true;
                req.setAttribute("message", "Cập nhật thành công!");
            } 
            else if (uri.contains("delete")) {
                // [Delete].Click: Xóa và quay về trạng thái khởi đầu
                String id = req.getParameter("id");
                dao.deleteById(id); // Sử dụng đúng tên hàm trong VideoDAO của bạn
                video = new Video();
                isEdit = false;
                req.setAttribute("message", "Xóa thành công!");
            } 
            else if (uri.contains("reset")) {
                // [Reset].Click: Form trống, vô hiệu hóa Update/Delete
                video = new Video();
                isEdit = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
        }

        // Lấy danh sách video hiển thị lên bảng (Video List)
        List<Video> list = dao.findAll();
        
        // Gửi dữ liệu sang JSP
        req.setAttribute("form", video);      // Dữ liệu trong các ô nhập
        req.setAttribute("videos", list);     // Dữ liệu trong bảng
        req.setAttribute("isEdit", isEdit);   // Trạng thái để disable/enable nút

        req.getRequestDispatcher("/views/client/admin-videos.jsp").forward(req, resp);
    } 
}