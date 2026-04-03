<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- CSS -->
<style>
body {
    background-color: #181818;
    color: #fff;
    font-family: Arial;
}

/* Video iframe */
iframe {
    border-radius: 10px;
}

/* Title */
h5 {
    font-weight: bold;
}

/* Card video bên phải */
.card {
    background-color: transparent;
    transition: 0.3s;
}

.card:hover {
    background-color: #2a2a2a;
    border-radius: 10px;
}

/* Ảnh thumbnail */
.card-content-img {
    height: 90px;
    object-fit: cover;
    border-radius: 8px;
}

/* Text */
.td-text {
    color: #fff;
    font-size: 14px;
}

.td-text:hover {
    color: red;
}

/* Scroll đẹp */
.overflow-auto::-webkit-scrollbar {
    width: 6px;
}
.overflow-auto::-webkit-scrollbar-thumb {
    background: #555;
    border-radius: 10px;
}

/* Button */
.btn {
    border-radius: 20px;
    font-size: 13px;
    padding: 6px 12px;
}

.btn-primary {
    background-color: #3ea6ff;
    border: none;
}

.btn-danger {
    background-color: #ff4d4d;
    border: none;
}

.btn-success {
    background-color: #00c853;
    border: none;
}

.btn:hover {
    opacity: 0.8;
}
</style>
</head>
<body>
<div class="container">
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
                            ng-class="{'text-danger': id==item.idTapphim}">
                            <h6 ng-bind="item.tenTapphim" class="td-text">${video.title }</h6>
                            <span>Lượt xem: ${video.views }</span>
                         </div>
                      </div>
                   </div>
                </div>
             </a>
          </c:forEach>
       </div>
    </div>

</div>
</body>
</html>