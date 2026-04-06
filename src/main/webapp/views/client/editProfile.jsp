<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Profile</title>
 <style>
        .td-text {
	overflow: hidden;
	display: -webkit-box;
	-webkit-line-clamp: 2;
	-webkit-box-orient: vertical;
}
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<c:if test="${empty sessionScope.currentUser}">
    <c:redirect url="/signin" />
</c:if>
 <div class="container">
        <jsp:include page="layout/header.jsp" />
 		
 		<div class="mx-auto mt-3" style="width: 500px;">
            <form action="${pageContext.request.contextPath}/editProfile" method="post">
                 <input type="hidden" value="${user.id }" class="form-control" name="id" id="Username" placeholder="Username">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" value="${user.fullname }" name="fullname" id="fullname" placeholder="fullname" required>
                    <label for="fullname">Fullname</label>
                </div>
                <span class="text-danger">${errorEmail }</span>
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="email" value="${user.email }" name="email" placeholder="email" required>
                    <label for="email">Email Address</label>
                </div>
                <span class="text-success">${success }</span>
                <div class="d-grid">
                  <button class="btn btn-lg btn-primary btn-login text-uppercase fw-bold mb-2" type="submit">Update</button>
                </div>
              </form>
        </div>

         <jsp:include page="layout/footer.jsp" />
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    
</body>
</html>