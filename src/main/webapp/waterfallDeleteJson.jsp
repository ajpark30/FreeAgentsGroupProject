<%-- the following is just an idea about
 generating simple JSON in a JSP using JSTL
 --%><%@include file="taglib.jsp"%>{'deleted':'<c:choose><c:when test="${ requestScope.deleted == true }">true</c:when><c:otherwise>false</c:otherwise></c:choose>'}