<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="pagination_wrapper">
    here will be paginator
    <c:forEach var="pageNumber" items="${pageNumbers}">
        <a href="controller?command=news&page=${pageNumber}">${pageNumber}</a>
    </c:forEach>
</div>