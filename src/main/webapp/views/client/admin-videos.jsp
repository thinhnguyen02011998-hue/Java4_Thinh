<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administration Tool - Video Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .poster-placeholder {
            width: 100%; height: 280px; background-color: #f8f9fa;
            border: 2px dashed #dee2e6; display: flex;
            align-items: center; justify-content: center; overflow: hidden;
        }
        .poster-placeholder img { max-width: 100%; max-height: 100%; object-fit: cover; }
        .nav-tabs .nav-link { color: #495057; font-weight: bold; }
        .nav-tabs .nav-link.active { color: #d9534f; border-top: 3px solid #d9534f; }
        .text-error { color: red; font-style: italic; }
        .text-message { color: green; font-weight: bold; }
    </style>
</head>
<body class="bg-light">

    <%@ include file="layout/admin-header.jsp" %>

    <main class="container mt-4 mb-5">
        <!-- Hiển thị thông báo lỗi hoặc thành công từ Servlet -->
        <c:if test="${not empty message}"><p class="text-message">${message}</p></c:if>
        <c:if test="${not empty error}"><p class="text-error">${error}</p></c:if>

        <div class="row">
            <div class="col-12">
                <h2 class="text-danger text-uppercase mb-3">Quản lý Video</h2>

                <ul class="nav nav-tabs" id="videoTab" role="tablist">
                    <li class="nav-item">
                        <button class="nav-link active text-uppercase" id="edition-tab" data-bs-toggle="tab" data-bs-target="#edition" type="button">Video Edition</button>
                    </li>
                    <li class="nav-item">
                        <button class="nav-link text-uppercase" id="list-tab" data-bs-toggle="tab" data-bs-target="#list" type="button">Video List</button>
                    </li>
                </ul>

                <div class="tab-content border border-top-0 p-4 bg-white shadow-sm" id="videoTabContent">
                    
                    <!-- PHẦN 1: VIDEO EDITION (FORM) -->
                    <div class="tab-pane fade show active" id="edition" role="tabpanel">
                        <form action="${pageContext.request.contextPath}/admin-video" method="post">
                            <div class="row">
                                <!-- Cột Poster -->
                                <div class="col-md-5 mb-3">
                                    <div class="poster-placeholder mb-2">
                                        <c:choose>
                                            <c:when test="${not empty form.poster}">
                                                <img src="${form.poster}" alt="Poster Video">
                                            </c:when>
                                            <c:otherwise><span class="text-muted fw-bold">POSTER IMAGE</span></c:otherwise>
                                        </c:choose>
                                    </div>
                                    <label class="form-label small fw-bold">Poster URL:</label>
                                    <input name="poster" value="${form.poster}" class="form-control form-control-sm" placeholder="URL hình ảnh...">
                                </div>

                                <!-- Cột Thông tin chính -->
                                <div class="col-md-7">
                                    <div class="mb-3">
                                        <label class="form-label fw-bold">YouTube ID (Mã Video):</label>
                                        <input name="id" value="${form.id}" class="form-control" placeholder="VD: VID01" ${isEdit ? 'readonly' : ''}>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label fw-bold">Video Title:</label>
                                        <input name="title" value="${form.title}" class="form-control" placeholder="Tên video...">
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6 mb-3">
                                            <label class="form-label fw-bold">View Count:</label>
                                            <input name="views" type="number" value="${form.views != null ? form.views : 0}" class="form-control">
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label class="form-label fw-bold">YouTube Link (Key):</label>
                                            <input name="link" value="${form.link}" class="form-control" placeholder="Mã nhúng YouTube...">
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label d-block fw-bold">Trạng thái:</label>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="active" id="activeTrue" value="true" ${form.active == null || form.active ? 'checked' : ''}>
                                            <label class="form-check-label" for="activeTrue">Active</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="active" id="activeFalse" value="false" ${form.active == false ? 'checked' : ''}>
                                            <label class="form-check-label" for="activeFalse">Inactive</label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label class="form-label fw-bold">Description:</label>
                                <textarea name="description" class="form-control" rows="4" placeholder="Mô tả nội dung...">${form.description}</textarea>
                            </div>

                            <div class="border-top pt-3 text-end">
                                <button formaction="${pageContext.request.contextPath}/admin-video/create" class="btn btn-primary px-4" ${isEdit ? 'disabled' : ''}>Create</button>
                                <button formaction="${pageContext.request.contextPath}/admin-video/update" class="btn btn-success px-4" ${!isEdit ? 'disabled' : ''}>Update</button>
                                <button formaction="${pageContext.request.contextPath}/admin-video/delete" class="btn btn-danger px-4" ${!isEdit ? 'disabled' : ''}>Delete</button>
                                <a href="${pageContext.request.contextPath}/admin-video/reset" class="btn btn-secondary px-4 text-decoration-none">Reset</a>
                            </div>
                        </form>
                    </div>

                    <!-- PHẦN 2: VIDEO LIST (TABLE) -->
                    <div class="tab-pane fade" id="list" role="tabpanel">
                        <div class="table-responsive">
                            <table class="table table-hover table-striped border">
                                <thead class="table-dark small">
                                    <tr>
                                        <th>ID</th><th>Title</th><th>Views</th><th>Status</th><th class="text-center">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="v" items="${videos}">
                                        <tr class="align-middle">
                                            <td class="fw-bold">${v.id}</td>
                                            <td>${v.title}</td>
                                            <td><span class="badge bg-info text-dark">${v.views}</span></td>
                                            <td>
                                                <span class="${v.active ? 'text-success fw-bold' : 'text-muted'}">${v.active ? 'Active' : 'Inactive'}</span>
                                            </td>
                                            <td class="text-center">
                                                <a href="${pageContext.request.contextPath}/admin-video/edit/${v.id}" class="btn btn-sm btn-outline-warning">Edit</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <%@ include file="layout/admin-footer.jsp" %>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>