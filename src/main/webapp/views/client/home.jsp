<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Home - PolyOES</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

<style>
html, body {
	height: 100%;
	margin: 0;
}

body {
	display: flex;
	flex-direction: column;
	background-color: #f0f2f5;
}

.main-content {
	flex: 1 0 auto;
}

footer {
	flex-shrink: 0;
}

/* KHÔI PHỤC PHONG CÁCH KHUNG VÀNG */
.custom-card {
	border: 5px solid #ffc107 !important;
	border-radius: 20px;
	overflow: hidden;
	background-color: #ffc107;
	box-shadow: 8px 8px 0px rgba(0, 0, 0, 0.15);
	transition: all 0.2s ease;
}

.custom-card:hover {
	transform: translate(-3px, -3px);
	box-shadow: 12px 12px 0px rgba(0, 0, 0, 0.2);
}

.card-content-img {
	height: 190px;
	width: 100%;
	object-fit: cover;
	border-bottom: 5px solid #ffc107;
	background-color: #000;
}

.td-text {
	font-weight: 800;
	color: #000 !important;
	text-transform: uppercase;
	height: 3rem;
	overflow: hidden;
	display: -webkit-box;
	-webkit-line-clamp: 2;
	-webkit-box-orient: vertical;
}

.video-stats {
	display: flex;
	justify-content: space-around;
	background: rgba(255, 255, 255, 0.6);
	border-radius: 10px;
	padding: 5px;
	margin-bottom: 10px;
	font-weight: bold;
	border: 2px solid rgba(0, 0, 0, 0.1);
}
</style>
</head>
<body>

	<!-- Header rộng 100% -->
	<jsp:include page="/views/client/layout/header.jsp" />

	<main class="main-content">
		<div class="container py-4">
			<div
				class="row g-4 row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4">
				<c:forEach var="video" items="${listVideo}">
					<div class="col">
						<div class="card custom-card">
							<a
								href="${pageContext.request.contextPath}/videoDetail?id=${video.id}">
								<img src="${video.poster}" class="card-content-img" />
							</a>
							<div class="card-body d-flex flex-column">
								<a
									href="${pageContext.request.contextPath}/videoDetail?id=${video.id}"
									class="text-decoration-none td-text mb-2"> ${video.title} </a>

								<div class="video-stats">
									<span class="text-danger"><i class="bi bi-heart-fill"></i>
										${video.favoriteCount != null ? video.favoriteCount : 0}</span> <span
										class="text-primary"><i class="bi bi-share-fill"></i>
										${video.shareCount != null ? video.shareCount : 0}</span>
								</div>

								<div class="mt-auto d-flex gap-2">
									<c:choose>
										
										<c:when test="${not empty sessionScope.currentUser}">
											<c:set var="isLiked" value="${listLiked.contains(video.id)}" />
											<a
												href="${pageContext.request.contextPath}/likeVideo?id=${video.id}"
												class="btn ${isLiked ? 'btn-danger' : 'btn-light'} border-dark fw-bold btn-sm flex-fill">
												${isLiked ? 'UNLIKE' : 'LIKE'} </a>

											<button class="btn btn-dark fw-bold btn-sm flex-fill"
												onclick="document.getElementById('videoIdInput').value='${video.id}'"
												data-bs-toggle="modal" data-bs-target="#modalShareVideo">
												SHARE</button>
										</c:when>

										
										<c:otherwise>
											
											<a href="${pageContext.request.contextPath}/signin"
												class="btn btn-light border-dark fw-bold btn-sm flex-fill">
												LIKE </a>
											
											<a href="${pageContext.request.contextPath}/signin"
												class="btn btn-dark fw-bold btn-sm flex-fill"> SHARE </a>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</main>

	<!-- Footer rộng 100% -->
	<jsp:include page="/views/client/layout/footer.jsp" />

	<!-- Modal Share Video (Giữ nguyên) -->
	<div class="modal fade" id="modalShareVideo" tabindex="-1">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content"
				style="border: 5px solid #ffc107; border-radius: 20px;">
				<form action="${pageContext.request.contextPath}/shareVideo"
					method="post">
					<div class="modal-body p-4 text-center">
						<h4 class="fw-bold mb-3">CHIA SẺ VIDEO</h4>
						<input type="email" class="form-control border-dark mb-3"
							name="email" placeholder="Email bạn bè" required> <input
							type="hidden" id="videoIdInput" name="videoId">
						<button type="submit"
							class="btn btn-warning w-100 fw-bold border-dark shadow-sm">GỬI
							NGAY</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>