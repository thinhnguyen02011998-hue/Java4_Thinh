<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Admin Home - Administration Tool</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .dashboard-card {
            transition: all 0.3s ease;
            border: none;
            border-radius: 15px;
            text-decoration: none !important;
        }
        .dashboard-card:hover {
            transform: translateY(-10px);
            box-shadow: 0 10px 20px rgba(0,0,0,0.15) !important;
        }
        .stat-number { font-size: 2.5rem; font-weight: bold; }
        .text-admin-gold { color: #ffff00; } /* Màu vàng giống logo admin */
    </style>
</head>
<body class="bg-light">

    <!-- Include Thanh Menu đen bạn đã làm -->
    <%@ include file="layout/admin-header.jsp" %>

    <div class="container mt-5">
        <div class="row mb-4">
            <div class="col-12">
                <h1 class="display-5 fw-bold text-dark">DASHBOARD</h1>
                <p class="lead">Hệ thống quản trị nội dung tiểu phẩm giải trí.</p>
                <hr>
            </div>
        </div>

        <div class="row g-4 text-center">
            <!-- Card Quản lý Videos -->
            <div class="col-md-4">
                <a href="${pageContext.request.contextPath}/admin-video/index" class="card dashboard-card shadow-sm h-100 bg-white">
                    <div class="card-body py-5">
                        <div class="stat-number text-primary mb-2">${videoCount}</div>
                        <h4 class="fw-bold text-dark text-uppercase">Videos</h4>
                        <p class="text-muted">Quản lý nội dung, tiêu đề và lượt xem tiểu phẩm.</p>
                        <div class="btn btn-outline-primary mt-3">Quản lý ngay</div>
                    </div>
                </a>
            </div>

            <!-- Card Quản lý Users -->
            <div class="col-md-4">
                <a href="${pageContext.request.contextPath}/admin-users/index" class="card dashboard-card shadow-sm h-100 bg-white">
                    <div class="card-body py-5">
                        <div class="stat-number text-success mb-2">${userCount}</div>
                        <h4 class="fw-bold text-dark text-uppercase">Users</h4>
                        <p class="text-muted">Quản lý tài khoản, mật khẩu và phân quyền admin.</p>
                        <div class="btn btn-outline-success mt-3">Quản lý ngay</div>
                    </div>
                </a>
            </div>

            <!-- Card Báo cáo Thống kê -->
            <div class="col-md-4">
                <a href="${pageContext.request.contextPath}/admin-reports" class="card dashboard-card shadow-sm h-100 bg-white">
                    <div class="card-body py-5">
                        <div class="stat-number text-danger mb-2">📊</div>
                        <h4 class="fw-bold text-dark text-uppercase">Reports</h4>
                        <p class="text-muted">Xem thống kê lượt yêu thích và danh sách chia sẻ.</p>
                        <div class="btn btn-outline-danger mt-3">Xem báo cáo</div>
                    </div>
                </a>
            </div>
        </div>

        <!-- Thông tin nhanh hệ thống -->
        <div class="row mt-5">
            <div class="col-md-12">
                <div class="alert bg-dark text-white p-4 rounded-4 shadow-sm">
                    <h5 class="fw-bold"><i class="bi bi-info-circle"></i> Trạng thái hệ thống</h5>
                    <p class="mb-0">Phiên đăng nhập Admin đang hoạt động. Bạn có toàn quyền thêm, sửa, xóa dữ liệu tiểu phẩm và người dùng.</p>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="layout/admin-footer.jsp" %>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>