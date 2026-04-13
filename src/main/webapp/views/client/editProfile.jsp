<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi" style="height: 100%;">
<head>
    <meta charset="UTF-8">
    <title>Edit Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Cố định footer xuống đáy màn hình */
        body {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            margin: 0;
        }
        .main-content {
            flex: 1 0 auto; /* Đẩy footer xuống */
        }
    </style>
</head>
<body>
    <c:if test="${empty sessionScope.currentUser}">
        <c:redirect url="/signin" />
    </c:if>

    <!-- Header Full Màn Hình -->
    <jsp:include page="layout/header.jsp" />

    <!-- Nội dung giữa tự giãn -->
    <main class="main-content container py-5">
        <div class="card mx-auto shadow-sm" style="max-width: 500px; border-radius: 15px;">
            <div class="card-body p-4">
                <h3 class="text-center mb-4 fw-bold text-success">👤 EDIT PROFILE</h3>
                <form action="${pageContext.request.contextPath}/editProfile" method="post">
                    <input type="hidden" value="${user.id}" name="id">
                    
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" value="${user.fullname}" name="fullname" id="fullname" placeholder="Fullname" required>
                        <label for="fullname">Fullname</label>
                    </div>

                    <div class="form-floating mb-3">
                        <input type="email" class="form-control" id="email" value="${user.email}" name="email" placeholder="Email" required>
                        <label for="email">Email Address</label>
                    </div>
                    <c:if test="${not empty errorEmail}">
                        <p class="text-danger small">${errorEmail}</p>
                    </c:if>
                    <c:if test="${not empty success}">
                        <p class="text-success small">${success}</p>
                    </c:if>

                    <div class="d-grid mt-4">
                        <button class="btn btn-primary btn-lg fw-bold text-uppercase" type="submit">Update</button>
                    </div>
                </form>
            </div>
        </div>
    </main>

    <!-- Footer Full Màn Hình -->
    <jsp:include page="layout/footer.jsp" />

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>