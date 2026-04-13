<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-expand-lg navbar-dark shadow-sm" style="background-color: #198754; border-bottom: 4px solid #ff69b4; width: 100%;">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold text-white" href="${pageContext.request.contextPath}/index">
            🎵 SHOP VIDEO
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <c:if test="${not empty sessionScope.currentUser}">
                    <!-- Link dành cho Admin -->
                    <c:if test="${sessionScope.currentUser.admin}">
                        <li class="nav-item">
                            <a class="nav-link fw-bold text-warning" href="${pageContext.request.contextPath}/admin-home">ADMIN TOOL</a>
                        </li>
                    </c:if>
                    <!-- Link Video yêu thích -->
                    <li class="nav-item">
                        <a class="nav-link fw-bold text-white" href="${pageContext.request.contextPath}/videoFavorite">MY FAVORITES</a>
                    </li>
                </c:if>
            </ul>

            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle fw-bold text-white" href="#" id="accountDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="bi bi-person-circle"></i> MY ACCOUNT
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end shadow border-0" aria-labelledby="accountDropdown" style="border-radius: 10px;">
                        <c:choose>
                            <c:when test="${not empty sessionScope.currentUser}">
                                <!-- Menu khi đã Đăng nhập -->
                                <li class="px-3 py-2 fw-bold text-success border-bottom">
                                    Chào, ${sessionScope.currentUser.fullname}
                                </li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/editProfile">👤 Edit Profile</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/changePassword">🔒 Change Password</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item text-danger fw-bold" href="${pageContext.request.contextPath}/signout">🚪 Logoff</a></li>
                            </c:when>
                            <c:otherwise>
                                <!-- Menu khi chưa Đăng nhập -->
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/signin">🔑 Login</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/register">📝 Registration</a></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/forgotPassword">❓ Forgot Password</a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>