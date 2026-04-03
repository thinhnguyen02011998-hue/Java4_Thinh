<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- CSS custom -->
<style>
body {
    background-color: pink;
    font-family: Arial, sans-serif;
}

.container {
    max-width: 1200px;
}

.card {
    background-color: #1f1f1f;
    border: none;
    border-radius: 10px;
    overflow: hidden;
    transition: all 0.3s ease;
}

.card:hover {
    transform: scale(1.05);
    box-shadow: 0 10px 25px rgba(0,0,0,0.8);
}

.card-content-img {
    border-radius: 10px;
}

.td-text {
    color: #fff;
    font-size: 14px;
    font-weight: 500;
}

.td-text:hover {
    color: red;
}

.btn {
    font-size: 13px;
    padding: 5px 10px;
}

.row {
    gap: 15px;
}
</style>


</head>
<body>

<div class="container">
       <%-- <jsp:include page="layout/header.jsp" /> --%>

       <div class="row my-3 row-cols-2 row-cols-sm-4">
          <jsp:useBean id="listVideo" scope="request" type="java.util.List"/>
          <c:forEach var="video" varStatus="loop" items="${listVideo}">
             <div class="col">
                <div class="card p-2 position-relative">
                   <a href="${pageContext.request.contextPath}/videoDetail?id=${video.id }">
                   <img src="${video.poster}"
                       class="card-img card-content-img" style="width: 100%; height: 400px; object-fit: cover;" />
                   </a>
                   <div class="card-body" style="transform: rotate(0);">
                      <a href="${pageContext.request.contextPath}/videoDetail?id=${video.id }"
                         class="card-text stretched-link text-decoration-none td-text">${video.title }</a>
                   </div>
                   <div class="mx-2 my-0">
                      <c:if test="${not empty sessionScope.currentUser}">
                         <a href="${pageContext.request.contextPath}/likeVideo?id=${video.id}" class="btn btn-primary ${listLiked.contains(video.id)?'d-none':''}">Like</a> 
                         <a href="${pageContext.request.contextPath}/likeVideo?id=${video.id}" class="btn btn-danger ${listLiked.contains(video.id)?'':'d-none'}">Unlike</a> 
                         <button class="btn btn-success" data-idVideo="${video.id }" data-bs-toggle="modal" data-bs-target="#modalShareVideo">Share</button>
                      </c:if>
                   </div>
                </div>
             </div>
          </c:forEach>
       </div>

    
       <%-- <jsp:include page="layout/footer.jsp" /> --%>
    </div>
    <%-- <div class="modal" tabindex="-1" id="modalShareVideo">
          <div class="modal-dialog">
             <div class="modal-content">
                <div class="modal-header">
                   <!-- <h5 class="modal-title">Send video to yout friend</h5> -->
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
       </div> --%>

</body>
</html>