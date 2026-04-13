<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* CSS để cố định footer ở dưới cùng (Sticky Footer) */
        html, body {
            height: 100%;
            margin: 0;
        }
        body {
            display: flex;
            flex-direction: column;
        }
        .main-content {
            flex: 1 0 auto; /* Đẩy footer xuống đáy */
        }
        footer {
            flex-shrink: 0;
        }
    </style>
</head>
<body>
    <c:if test="${not empty sessionScope.currentUser}">
        <c:redirect url="/" />
    </c:if>

    <!-- HEADER: Đưa ra ngoài container để rộng 100% -->
    <jsp:include page="layout/header.jsp" />

    <!-- NỘI DUNG CHÍNH -->
    <main class="main-content">
        <div class="container py-5">
            <div class="card mx-auto shadow border-0" style="max-width: 500px; border-radius: 15px;">
                <div class="card-body p-4">
                    <h3 class="text-center mb-4 fw-bold text-success">🔑 LOGIN</h3>
                    <form action="${pageContext.request.contextPath}/signin" method="post">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" name="id" id="Username" value="${user.id}" placeholder="Username" required>
                            <label for="Username">Username</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="password" class="form-control" name="password" id="password" value="${user.password}" placeholder="password" required>
                            <label for="password">Password</label>
                        </div>
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger py-2 small">${errorMessage}</div>
                        </c:if>
                        <div class="d-grid mt-4">
                            <button class="btn btn-primary btn-lg fw-bold text-uppercase" type="submit">Login</button>
                            <div class="text-center mt-3">
                                <a class="text-decoration-none small" href="${pageContext.request.contextPath}/forgotPassword">Forgot password?</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </main>

    <!-- FOOTER: Đưa ra ngoài container để rộng 100% -->
    <jsp:include page="layout/footer.jsp" />

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>