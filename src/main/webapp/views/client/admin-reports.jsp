<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Báo cáo thống kê Admin</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
    .nav-tabs .nav-link { color: #495057; font-weight: bold; }
    .nav-tabs .nav-link.active { color: #d9534f !important; border-top: 3px solid #d9534f; }
    .text-danger-custom { color: #d9534f; font-weight: bold; }
</style>
</head>
<body class="bg-light">

<%@ include file="layout/admin-header.jsp" %>

<main class="container mt-4">
    <h3 class="mb-4">BÁO CÁO THỐNG KÊ</h3>

    <ul class="nav nav-tabs" id="reportTabs" role="tablist">
        <li class="nav-item">
            <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#tab-fav" type="button">FAVORITES</button>
        </li>
        <li class="nav-item">
            <button class="nav-link" data-bs-toggle="tab" data-bs-target="#tab-user" type="button">FAVORITE USERS</button>
        </li>
        <li class="nav-item">
            <button class="nav-link" data-bs-toggle="tab" data-bs-target="#tab-share" type="button">SHARED FRIENDS</button>
        </li>
    </ul>

    <div class="tab-content border border-top-0 p-4 bg-white shadow-sm">
        
        <!-- TAB 1: THỐNG KÊ LƯỢT THÍCH CHUNG -->
        <div class="tab-pane fade show active" id="tab-fav">
            <table class="table table-bordered">
                <thead class="text-danger-custom">
                    <tr>
                        <th>VIDEO TITLE</th><th>FAVORITE COUNT</th><th>LATEST DATE</th><th>OLDEST DATE</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="f" items="${favList}">
                        <tr>
                            <%-- Chỉnh sửa tại đây để khớp với class ReportFavorites --%>
                            <td>${f.title}</td>
                            <td>${f.likes}</td>
                            <td><fmt:formatDate value="${f.newestDate}" pattern="dd/MM/yyyy"/></td>
                            <td><fmt:formatDate value="${f.oldestDate}" pattern="dd/MM/yyyy"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- TAB 2: LỌC NGƯỜI YÊU THÍCH THEO VIDEO -->
        <div class="tab-pane fade" id="tab-user">
            <form action="${pageContext.request.contextPath}/admin-reports" method="get" class="mb-3">
                <div class="row align-items-center">
                    <div class="col-auto"><label class="fw-bold">VIDEO TITLE?</label></div>
                    <div class="col-md-6">
                        <%-- Thêm hidden input để giữ trạng thái tab nếu cần --%>
                        <select name="videoId" class="form-select border-danger" onchange="this.form.submit()">
                            <option value="">-- Chọn tiểu phẩm --</option>
                            <c:forEach var="v" items="${videos}">
                                <option value="${v.id}" ${v.id == selectedVideoId ? 'selected' : ''}>${v.title}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </form>
            <table class="table table-bordered">
                <thead class="text-danger-custom">
                    <tr>
                        <th>USERNAME</th><th>FULLNAME</th><th>EMAIL</th><th>FAVORITE DATE</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="u" items="${favUsers}">
                        <tr>
                            <td>${u.userId}</td><td>${u.fullname}</td><td>${u.email}</td>
                            <td><fmt:formatDate value="${u.favoriteDate}" pattern="dd/MM/yyyy"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- TAB 3: LỌC NGƯỜI GỬI & NHẬN THEO VIDEO -->
        <div class="tab-pane fade" id="tab-share">
            <form action="${pageContext.request.contextPath}/admin-reports" method="get" class="mb-3">
                <div class="row align-items-center">
                    <div class="col-auto"><label class="fw-bold">VIDEO TITLE?</label></div>
                    <div class="col-md-6">
                        <select name="videoId" class="form-select border-danger" onchange="this.form.submit()">
                            <option value="">-- Chọn tiểu phẩm --</option>
                            <c:forEach var="v" items="${videos}">
                                <option value="${v.id}" ${v.id == selectedVideoId ? 'selected' : ''}>${v.title}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </form>
            <table class="table table-bordered">
                <thead class="text-danger-custom">
                    <tr>
                        <th>SENDER NAME</th><th>SENDER EMAIL</th><th>RECEIVER EMAIL</th><th>SENT DATE</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="s" items="${shareFriends}">
                        <tr>
                            <td>${s.senderName}</td><td>${s.senderEmail}</td><td>${s.receiverEmail}</td>
                            <td><fmt:formatDate value="${s.sendDate}" pattern="dd/MM/yyyy"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        
    </div>
</main>

<%@ include file="layout/admin-footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<%-- Script bổ sung để tự động mở đúng Tab sau khi trang bị load lại (do form submit) --%>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        var selectedVideoId = "${selectedVideoId}";
        if (selectedVideoId) {
            // Nếu có videoId được chọn, mặc định mở Tab 2 (FAVORITE USERS) 
            // Hoặc bạn có thể tùy chỉnh logic để mở tab vừa thao tác
            var tabTrigger = new bootstrap.Tab(document.querySelector('#reportTabs button[data-bs-target="#tab-user"]'));
            tabTrigger.show();
        }
    });
</script>

</body>
</html>