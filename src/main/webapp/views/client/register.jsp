<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* QUAN TRỌNG: CSS này giúp Header/Footer rộng hết màn hình và đẩy Footer xuống đáy */
        html, body {
            height: 100%;
            margin: 0;
        }
        body {
            display: flex;
            flex-direction: column;
        }
        .main-content {
            flex: 1 0 auto; /* Đẩy footer xuống đáy màn hình */
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

    <!-- Header ra ngoài container để bự hết màn hình -->
    <jsp:include page="layout/header.jsp" />

    <main class="main-content">
        <div class="container py-5">
            <div class="card mx-auto shadow border-0" style="max-width: 500px; border-radius: 15px;">
                <div class="card-body p-4">
                    <h3 class="text-center mb-4 fw-bold text-primary">📝 SIGN UP</h3>
                    <form action="${pageContext.request.contextPath}/register" method="post">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" name="id" id="Username" value="${user.id}" placeholder="Username" required>
                            <label for="Username">Username</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="password" class="form-control" name="password" id="password" value="${user.password}" placeholder="Password" required>
                            <label for="password">Password</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" name="fullname" id="fullname" value="${user.fullname}" placeholder="Fullname" required>
                            <label for="fullname">Fullname</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="email" class="form-control" name="email" id="email" value="${user.email}" placeholder="Email" required>
                            <label for="email">Email Address</label>
                        </div>

                        <c:if test="${not empty errorEmail}"><p class="text-danger small">${errorEmail}</p></c:if>

                        <div class="d-grid mt-4">
                            <button class="btn btn-primary btn-lg fw-bold text-uppercase" type="submit">Sign Up</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </main>

    <!-- Footer ra ngoài container để bự hết màn hình -->
    <jsp:include page="layout/footer.jsp" />

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>