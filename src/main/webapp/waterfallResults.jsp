<%@include file="taglib.jsp"%>
<c:set var="title" value="User Results"/>
<%@include file="header.jsp"%>

<html><body>

<div class="container-fluid">
    <h2>Results for Waterfalls</h2>
    <c:forEach items="${waterFallInfo}" var="waterfall">
        <table border = "1" width = "100%">
            <tr>
                <th>Waterfall Information: </th>
            </tr>
            <tr>
                <td>Waterfall Name: ${waterfall.name}<br />
                    city: ${waterfall.city} <c:if test="${empty waterfall.city}">N/A</c:if><br />
                    state: ${waterfall.state} <c:if test="${empty waterfall.state}">N/A</c:if> <br />
                    Latitude: ${waterfall.latitude}<c:if test="${empty waterfall.latitude}">N/A</c:if><br />
                    Longitude: ${waterfall.longitude}<c:if test="${empty waterfall.longitude}">N/A</c:if><br />
                    URL: ${waterfall.url}<c:if test="${empty waterfall.url}">N/A</c:if><br />
                </td>
                <br />
            </tr>
        </table>
    </c:forEach>
</div>


</body>
</html>