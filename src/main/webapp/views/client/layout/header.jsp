<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">


<nav class="navbar navbar-expand-lg bg-success rounded">
	<div class="container-fluid">

		<a class="navbar-brand fw-bold text-white"
		   href="${pageContext.request.contextPath}/">
			SHOP VIDEO
		</a>

		<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">

				<c:choose>
					<c:when test="${not empty sessionScope.currentUser}">

						<c:if test="${sessionScope.currentUser.admin}">
							<li class="nav-item">
								<a class="nav-link fw-bold text-white"
								   href="${pageContext.request.contextPath}/admin">
									Admin
								</a>
							</li>
						</c:if>

						<li class="nav-item">
							<a class="nav-link fw-bold text-white"
							   href="${pageContext.request.contextPath}/videoFavorite">
								My Favorites
							</a>
						</li>

					</c:when>
				</c:choose>

				<!-- Dropdown -->
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle fw-bold text-white"
					   href="#" role="button" data-bs-toggle="dropdown">
						My Account
					</a>

					<ul class="dropdown-menu custom-dropdown">

						<c:choose>

							<c:when test="${not empty sessionScope.currentUser}">

								<li class="dropdown-header">Tài khoản</li>

								<li>
									<a class="dropdown-item" href="${pageContext.request.contextPath}/changePassword">
										🔒 Change Password
									</a>
								</li>

								<li>
									<a class="dropdown-item" href="${pageContext.request.contextPath}/editProfile">
										👤 Edit Profile
									</a>
								</li>

								<li><hr class="dropdown-divider"></li>

								<li>
									<a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/signout">
										🚪 Logoff
									</a>
								</li>

							</c:when>

							<c:otherwise>

								<li class="dropdown-header">Welcome</li>

								<li>
									<a class="dropdown-item" href="${pageContext.request.contextPath}/signin">
										🔑 Login
									</a>
								</li>

								<li>
									<a class="dropdown-item" href="${pageContext.request.contextPath}/register">
										📝 Registration
									</a>
								</li>

								<li>
									<a class="dropdown-item" href="${pageContext.request.contextPath}/forgotPassword">
										❓ Forgot Password
									</a>
								</li>

							</c:otherwise>

						</c:choose>

					</ul>
				</li>

			</ul>
		</div>
	</div>
</nav>