<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Administration Tool - User Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .nav-tabs .nav-link { color: #495057; font-weight: bold; text-transform: uppercase; }
        .nav-tabs .nav-link.active { color: #d9534f; border-top: 3px solid #d9534f; }
        .bg-custom-gray { background-color: #f1f1f1; }
        .btn-custom { background-color: #b5b5b5; color: white; border: none; }
        .btn-custom:hover { background-color: #d9534f; color: white; }
    </style>
</head>
<body class="bg-light">

    <%@ include file="layout/admin-header.jsp" %>

    <main class="container mt-4 mb-5">
        <h2 class="text-danger text-uppercase mb-3">Quản lý người sử dụng</h2>
        
        <!-- Thông báo -->
        <c:if test="${not empty message}"><div class="alert alert-success">${message}</div></c:if>
        <c:if test="${not empty error}"><div class="alert alert-danger">${error}</div></c:if>

        <ul class="nav nav-tabs" id="userTab" role="tablist">
            <li class="nav-item">
                <button class="nav-link active" id="edition-tab" data-bs-toggle="tab" data-bs-target="#edition" type="button">User Edition</button>
            </li>
            <li class="nav-item">
                <button class="nav-link" id="list-tab" data-bs-toggle="tab" data-bs-target="#list" type="button">User List</button>
            </li>
        </ul>

        <div class="tab-content border border-top-0 p-4 bg-white shadow-sm" id="userTabContent">
            
            <!-- FORM EDITION -->
            <div class="tab-pane fade show active" id="edition">
                <form action="${pageContext.request.contextPath}/admin-users" method="post">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="form-label text-uppercase fw-bold small">Username?</label>
                            <input name="id" value="${form.id}" class="form-control" placeholder="Tên đăng nhập..." ${isEdit ? 'readonly' : ''}>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label class="form-label text-uppercase fw-bold small">Password?</label>
                            <input name="password" type="password" value="${form.password}" class="form-control" placeholder="Mật khẩu...">
                        </div>
                        <div class="col-md-6 mb-3">
                            <label class="form-label text-uppercase fw-bold small">Fullname?</label>
                            <input name="fullname" value="${form.fullname}" class="form-control" placeholder="Họ và tên...">
                        </div>
                        <div class="col-md-6 mb-3">
                            <label class="form-label text-uppercase fw-bold small">Email Address?</label>
                            <input name="email" type="email" value="${form.email}" class="form-control" placeholder="Địa chỉ email...">
                        </div>
                        <div class="col-12 mb-3">
                            <label class="form-label text-uppercase fw-bold small d-block">Role?</label>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="admin" id="rAdmin" value="true" ${form.admin ? 'checked' : ''}>
                                <label class="form-check-label" for="rAdmin">Admin</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="admin" id="rUser" value="false" ${!form.admin ? 'checked' : ''}>
                                <label class="form-check-label" for="rUser">User</label>
                            </div>
                        </div>
                    </div>

                    <div class="text-end border-top pt-3 mt-3">
                        <!-- Theo nghiệp vụ: Update/Delete chỉ bật khi đã chọn [Edit] -->
                        <button formaction="${pageContext.request.contextPath}/admin-users/update" class="btn btn-secondary px-4" ${!isEdit ? 'disabled' : ''}>Update</button>
                        <button formaction="${pageContext.request.contextPath}/admin-users/delete" class="btn btn-secondary px-4" ${!isEdit ? 'disabled' : ''}>Delete</button>
                        <a href="${pageContext.request.contextPath}/admin-users/reset" class="btn btn-secondary px-4">Reset</a>
                    </div>
                </form>
            </div>

            <!-- DANH SÁCH (USER LIST) -->
            <div class="tab-pane fade" id="list">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover mt-2">
                        <thead class="bg-light small text-uppercase">
                            <tr>
                                <th>Username</th><th>Password</th><th>Fullname</th><th>Email</th><th>Role</th><th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="u" items="${users}">
                                <tr class="align-middle">
                                    <td>${u.id}</td>
                                    <td>******</td>
                                    <td>${u.fullname}</td>
                                    <td>${u.email}</td>
                                    <td>${u.admin ? 'Admin' : 'User'}</td>
                                    <td><a href="${pageContext.request.contextPath}/admin-users/edit/${u.id}" class="text-primary text-decoration-none fw-bold">Edit</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <!-- Pagination -->
                <div class="d-flex justify-content-between align-items-center mt-3 p-2 bg-light border shadow-sm">
                    <span class="fw-bold">${users.size()} users</span>
                    <div class="btn-group btn-group-sm shadow-sm">
                        <button class="btn btn-light border text-uppercase">|&lt;</button>
                        <button class="btn btn-light border text-uppercase">&lt;&lt;</button>
                        <button class="btn btn-light border text-uppercase">&gt;&gt;</button>
                        <button class="btn btn-light border text-uppercase">&gt;|</button>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <%@ include file="layout/admin-footer.jsp" %>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>