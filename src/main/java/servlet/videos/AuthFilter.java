//package servlet.videos;
//
//
//
//import java.io.IOException;
//
//import entity.Users;
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.*;
//
//@WebFilter({"/editProfile", "/changePassword", "/videoFavorite", "/likeVideo", "/shareVideo"})
//public class AuthFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;
//
//        Users user = (Users) req.getSession().getAttribute("currentUser");
//
//        // Nếu chưa login
//        if (user == null) {
//
//            // lưu lại trang trước đó (để login xong quay lại)
//            String uri = req.getRequestURI();
//            String query = req.getQueryString();
//            String fullUrl = uri + (query != null ? "?" + query : "");
//
//            req.getSession().setAttribute("redirectAfterLogin", fullUrl);
//
//            // chuyển về trang login
//            resp.sendRedirect(req.getContextPath() + "/signin");
//            return;
//        }
//
//        // đã login → cho đi tiếp
//        chain.doFilter(request, response);
//    }
//}
