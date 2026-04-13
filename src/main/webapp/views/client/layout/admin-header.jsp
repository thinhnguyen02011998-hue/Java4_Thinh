<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
    .admin-navbar { background-color: #198754 !important; border-bottom: 4px solid #ff69b4; }
    .admin-link { color: white !important; font-weight: bold; }
    .admin-link:hover { color: #ff69b4 !important; }
    .text-admin-brand { color: #ff69b4 !important; font-weight: bold; font-size: 1.5rem; }
</style>

<nav class="navbar navbar-expand-lg navbar-dark admin-navbar shadow">
    <div class="container-fluid">
        <a class="navbar-brand text-admin-brand" href="admin-home">
            ADMIN TOOL
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#adminNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="adminNavbar">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link admin-link px-3" href="admin-home">HOME</a></li>
                <li class="nav-item"><a class="nav-link admin-link px-3" href="${pageContext.request.contextPath}/admin-video/index">VIDEOS</a></li>
                <li class="nav-item"><a class="nav-link admin-link px-3" href="${pageContext.request.contextPath}/admin-users/index">USERS</a></li>
                <li class="nav-item"><a class="nav-link admin-link px-3" href="admin-reports">REPORTS</a></li>
                <li class="nav-item"><a class="nav-link admin-link px-3 text-warning" href="${pageContext.request.contextPath}/">OUT SITE</a></li>
            </ul>
        </div>
    </div>
</nav>