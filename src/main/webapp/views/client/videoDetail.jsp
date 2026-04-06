<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style>
.td-text {
	overflow: hidden;
	display: -webkit-box;
	-webkit-line-clamp: 2;
	-webkit-box-orient: vertical;
}
</style>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<jsp:include page="layout/header.jsp" />

		<div class="row">
			<div class="col-12 col-lg-8">
				<div class="mt-3">
					<iframe width="100%" height="450"
							src="https://www.youtube.com/embed/${video.link}"
							frameborder="0" allowfullscreen>
					</iframe>
					<p>Link: ${video.link}</p>

					<h5 class="mt-2">${video.title }</h5>
					<p>Mô tả: ${video.description }</p>
					<span>Lượt xem: ${video.views }</span>
					<div class="mt-3">
						<c:if test="${not empty sessionScope.currentUser}">
						  	<a href="${pageContext.request.contextPath}/likeVideo?id=${video.id}" class="btn btn-primary ${likedVideo?'d-none':''}">Like</a> 
							<a href="${pageContext.request.contextPath}/likeVideo?id=${video.id}" class="btn btn-danger ${likedVideo?'':'d-none'}">Unlike</a> 
							<button class="btn btn-success" data-idVideo="${video.id }" data-bs-toggle="modal" data-bs-target="#modalShareVideo">Share</button>
						</c:if>
					</div>
				</div>

			</div>

			<div class="col-12 col-lg-4">
				<div class="border border-1 mt-3 d-flex flex-column gap-2 overflow-auto"
					style="height: 760px;">
					<c:forEach var="video" varStatus="loop" items="${listVideoRandom}">
						<a href="${pageContext.request.contextPath}/videoDetail?id=${video.id }" class="text-decoration-none">
							<div class="card border-0 mx-3">
								<div class="row g-0 card-episode-new p-0">
									<div class="col-4 overflow-hidden">
										<img src="${video.poster }"
											class="w-100 rounded-start card-content-img"
											style="object-fit: cover">
									</div>

									<div class="col-8">
										<div class="card-body p-0 ms-3"
											ng-class="{'text-success': id==item.idTapphim}">
											<h6 class="text-success">${video.title }</h6>
											<span class="text-success">Lượt xem: ${video.views }</span>
										</div>
									</div>



								</div>
							</div>
						</a>
					</c:forEach>
				</div>
			</div>

			<jsp:include page="layout/footer.jsp" />
		</div>
		<div class="modal" tabindex="-1" id="modalShareVideo">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Send video to yout friend</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form action="${pageContext.request.contextPath}/shareVideo" method="post">
							<div class="mb-3">
								<label for="exampleFormControlInput1" class="form-label">Your
									friend's email?</label>
								<input type="email" class="form-control" name="email" id="exampleFormControlInput1"
									placeholder="name@example.com" required>
								<span class="text-success d-none" id="successMessage">Chia sẻ thành công</span>
								<input type="hidden" id="videoIdInput" name="videoId"/>
								<input id="btn-send" class="d-none" type="submit" >
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<label for="btn-send"  class="btn btn-primary">Send</label>
					</div>
				</div>
			</div>
		</div>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
			crossorigin="anonymous"></script>
		<script>
	document.addEventListener("DOMContentLoaded", () => {
		  const shareButtons = document.querySelectorAll("button[data-idVideo]");
		  const videoIdInput = document.getElementById("videoIdInput");
		  shareButtons.forEach((button) => {
		    button.addEventListener("click", () => {
		      const videoId = button.getAttribute("data-idVideo");
		      videoIdInput.value = videoId;
		    });
		  });
		  const urlParams = new URLSearchParams(window.location.search);
		  if (urlParams.get("modal") === "true") {
		      const modal = new bootstrap.Modal(document.getElementById("modalShareVideo"));
		      modal.show();
		      const successMessage = document.getElementById("successMessage");
	          if (successMessage) {
	              successMessage.classList.remove("d-none");
	          }
		  }
		});
	</script>
</body>
</html>