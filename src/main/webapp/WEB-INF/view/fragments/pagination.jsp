<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="pagination-wrapper">
    <c:set var="pagesNumber" value="${requestScope.get('pages')}"/>
    <c:set var="current" value="${requestScope.get('currentPage')}"/>
    <c:set var="itemsPerPage" value="${param.get('itemsPerPage')}"/>
    <div class="pagination">
        <c:if test="${current > 1}">
            <li><a href="controller?command=news&page=${current-1}&items=${itemsPerPage}"><</a></li>
        </c:if>
        <c:forEach begin="1" end="${pagesNumber}" var="page">
            <c:if test="${page == current}">
                <li class="active"><a class="active" href="controller?command=news&page=${page}&items=${itemsPerPage}">${page}</a></li>
            </c:if>
            <c:if test="${page != current}">
                <li><a href="controller?command=news&page=${page}&items=${itemsPerPage}">${page}</a></li>
            </c:if>
        </c:forEach>
        <c:if test="${current < pagesNumber}">
            <li><a href="controller?command=news&page=${current+1}&items=${itemsPerPage}">></a></li>
        </c:if>
    </div>
</div>