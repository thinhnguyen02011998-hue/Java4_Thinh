<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
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
		<div class="row my-3 row-cols-2 row-cols-sm-4">
			<c:forEach var="video" varStatus="loop" items="${listVideFavorite}">
				<div class="col">
					<div class="card p-2 position-relative">
						<img src="${video.poster}"
							class="card-img card-content-img" />
						<div class="card-body" style="transform: rotate(0);">
							<a href="${pageContext.request.contextPath}/videoDetail?id=${video.id }"
								class="card-text stretched-link text-decoration-none td-text">${video.title }</a>
						</div>
						<div class="mx-2 my-0">
							<c:if test="${not empty sessionScope.currentUser}">
								<a href="${pageContext.request.contextPath}/likeVideo?id=${video.id}"
									class="btn btn-danger">Unlike</a>
								<button class="btn btn-success" data-idVideo="${video.id }"
									data-bs-toggle="modal" data-bs-target="#modalShareVideo">Share</button>
							</c:if>
						</div>
					</div>
				</div>
			</c:forEach>
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