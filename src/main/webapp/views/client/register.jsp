<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
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
<c:if test="${not empty sessionScope.currentUser}">
    <c:redirect url="/" />
</c:if>
 <div class="container">
        <jsp:include page="layout/header.jsp" />
 			
 		<div class="mx-auto mt-3" style="width: 500px;">
            <form action="${pageContext.request.contextPath}/register" method="post">
            	<span class="text-danger">${errorUsername }</span>
                <div class="form-floating mb-3">
                  <input type="text" class="form-control" name="id" id="Username" value="${user.id }" placeholder="Username" required>
                  <label for="Username">Username</label>
                </div>
                <div class="form-floating mb-3">
                  <input type="password" class="form-control" name="password" id="password" value="${user.password }" placeholder="password" required>
                  <label for="password">Password</label>
                </div>
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" name="fullname" id="fullname" value="${user.fullname }" placeholder="fullname" required>
                    <label for="fullname">Fullname</label>
                </div>
                <span class="text-danger">${errorEmail }</span>
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" name="email" id="email" placeholder="email" value="${user.email }" required>
                    <label for="email">Email</label>
                </div>
				<span class="text-success">${success }</span>
                <div class="d-grid">
                  <button class="btn btn-lg btn-primary btn-login text-uppercase fw-bold mb-2" type="submit">Sign Up</button>
                </div>

              </form>
          </div>

         <jsp:include page="layout/footer.jsp" />
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    
</body>
</html>