<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Favorites</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<style>
    /* Card Style Neobrutalism */
    .custom-card {
        border: 7px solid #ffc107 !important; /* Viền vàng bự hơn */
        border-radius: 20px;
        background-color: #ffc107; /* Thân dưới màu vàng */
        overflow: hidden;
        transition: 0.3s;
    }
    .custom-card:hover { transform: translateY(-5px); box-shadow: 0 10px 20px rgba(0,0,0,0.2); }
    
    .card-img-custom {
        height: 200px; object-fit: cover;
        border-bottom: 5px solid #ffc107;
    }

    .td-text {
        font-weight: 800; color: #000 !important;
        text-transform: uppercase; font-size: 0.95rem;
        height: 3rem; display: -webkit-box;
        -webkit-line-clamp: 2; -webkit-box-orient: vertical;
        overflow: hidden; margin-bottom: 10px;
    }

    .stats-badge {
        background: rgba(255, 255, 255, 0.5);
        border-radius: 10px; padding: 5px 10px;
        font-weight: bold; font-size: 0.85rem;
        display: flex; justify-content: space-between;
    }
    
    .btn-custom {
        border-radius: 10px; font-weight: bold;
        border: 2px solid rgba(0,0,0,0.1);
    }
</style>
</head>
<body class="bg-light">
    <div class="container py-4">
        <jsp:include page="layout/header.jsp" />
        <h3 class="my-4 fw-bold text-uppercase border-start border-5 border-warning ps-3">Video đã thích</h3>

        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4">
            <c:forEach var="video" items="${listVideFavorite}">
                <div class="col d-flex align-items-stretch">
                    <div class="card custom-card w-100 border-0 shadow-sm">
                        <!-- Poster -->
                        <a href="${pageContext.request.contextPath}/videoDetail?id=${video.id}">
                            <img src="${video.poster}" class="card-img-top card-img-custom" />
                        </a>
                        
                        <!-- Nội dung nằm trên phần Vàng -->
                        <div class="card-body d-flex flex-column">
                            <a href="${pageContext.request.contextPath}/videoDetail?id=${video.id}" 
                               class="text-decoration-none td-text">${video.title}</a>
                            
                            <!-- Hiển thị Lượt Like/Share thực tế -->
                            <div class="stats-badge mb-3">
                                <span class="text-danger"><i class="bi bi-heart-fill"></i> ${video.favoriteCount}</span>
                                <span class="text-primary"><i class="bi bi-share-fill"></i> ${video.shareCount}</span>
                            </div>

                            <div class="mt-auto d-flex gap-2">
                                <c:if test="${not empty sessionScope.currentUser}">
                                    <a href="${pageContext.request.contextPath}/likeVideo?id=${video.id}" 
                                       class="btn btn-danger btn-sm flex-fill btn-custom">Unlike</a>
                                    <button class="btn btn-success btn-sm flex-fill btn-custom" 
                                            data-idVideo="${video.id}" data-bs-toggle="modal" data-bs-target="#modalShareVideo">Share</button>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <jsp:include page="layout/footer.jsp" />
    </div>

    <!-- Modal Share giữ nguyên logic của bạn -->
    <div class="modal fade" id="modalShareVideo" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content" style="border: 5px solid #ffc107; border-radius: 20px;">
                <div class="modal-header">
                    <h5 class="modal-title fw-bold">Chia sẻ Video</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <form action="${pageContext.request.contextPath}/shareVideo" method="post">
                    <div class="modal-body">
                        <input type="email" class="form-control" name="email" placeholder="Email người nhận" required>
                        <input type="hidden" id="videoIdInput" name="videoId"/>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-warning w-100 fw-bold">GỬI</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.addEventListener("DOMContentLoaded", () => {
            const shareButtons = document.querySelectorAll("button[data-idVideo]");
            shareButtons.forEach(btn => {
                btn.addEventListener("click", () => {
                    document.getElementById("videoIdInput").value = btn.getAttribute("data-idVideo");
                });
            });
        });
    </script>
</body>
</html>