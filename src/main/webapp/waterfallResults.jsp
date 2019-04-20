<%@include file="taglib.jsp"%>
<c:set var="title" value="User Results"/>
<%@include file="header.jsp"%>

<html><body>

<div class="container-fluid">
    <h2>Results for Waterfalls</h2>
    <c:forEach items="${waterFallInfo}" var="waterfall">
        <table border = "1" width = "100%">
            <tr>
                <th>Waterfall Location: </th>
            </tr>
            <tr>
                <td>Waterfall Name: ${waterfall.name}<br />
                    Image: <br />
                </td>
                <br />
            </tr>
        </table>
    </c:forEach>
</div>


</body>
</html>