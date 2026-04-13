<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Forgot Password</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Ép body chiếm hết chiều cao màn hình */
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }
        /* Cấu trúc Flexbox để đẩy footer xuống đáy */
        body {
            display: flex;
            flex-direction: column;
        }
        /* Nội dung chính sẽ tự giãn ra */
        .main-content {
            flex: 1 0 auto;
        }
        /* Footer luôn nằm dưới cùng */
        footer {
            flex-shrink: 0;
        }
    </style>
</head>
<body>
    <c:if test="${not empty sessionScope.currentUser}">
        <c:redirect url="/" />
    </c:if>

    <!-- HEADER: Nằm ngoài container để rộng 100% -->
    <jsp:include page="/views/client/layout/header.jsp" />

    <!-- PHẦN NỘI DUNG CHÍNH -->
    <main class="main-content">
        <div class="container py-5">
            <div class="card mx-auto shadow border-0" style="max-width: 500px; border-radius: 15px;">
                <div class="card-body p-4">
                    <h3 class="text-center mb-4 fw-bold text-primary">❓ FORGOT PASSWORD</h3>
                    <form action="${pageContext.request.contextPath}/forgotPassword" method="post">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" value="${user.id}" name="id" id="Username" placeholder="Username" required>
                            <label for="Username">Username</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="email" class="form-control" value="${user.email}" name="email" id="Email" placeholder="Email" required>
                            <label for="Email">Email</label>
                        </div>
                        
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger py-2 small">${errorMessage}</div>
                        </c:if>
                        <c:if test="${not empty successMessage}">
                            <div class="alert alert-success py-2 small">${successMessage}</div>
                        </c:if>
                        
                        <div class="d-grid mt-4">
                            <button class="btn btn-primary btn-lg text-uppercase fw-bold" type="submit">Retrieve</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </main>

    <!-- FOOTER: Nằm ngoài container để rộng 100% -->
    <jsp:include page="/views/client/layout/footer.jsp" />

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>