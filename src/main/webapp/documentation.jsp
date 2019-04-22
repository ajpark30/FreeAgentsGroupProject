<%@include file="taglib.jsp"%>
<%@include file="header.jsp"%>
<body>
<div class="jumbotron text-center">
    <h1>Waterfall RESTful Service</h1>
    <p>Documentation</p>
</div>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <li><a href="index.jsp">Home</a></li>
            <li class="active"><a href="documentation.jsp">Documentation</a></li>
        </ul>
    </div>
</nav>
<aside>
    <p>All endpoints will return JSON</p>
</aside>
<div class="container-fluid col-md-8 col-md-offset-2">
    <h2>Searching by Zipcode - Find Nearest Waterfall</h2>
    <br />
    <div class="well">
        <p>After entering a valid zipcode the program will return results nearest you. Containing the exact location
            in latitude and longitude</p>
        <code>http://localhost:8080/waterfalls/zipcode/{zipcode here}</code>
    </div>
</div>
<div class="container-fluid col-md-8 col-md-offset-2">
    <h2>Searching by Name - Get Location & Photo</h2>
    <br />
    <div class="well">
        <p>If you know the name of the waterfall you wish to search, you will be provided location information
            and a photo.</p>
        <code>http://localhost:8080/waterfalls/{name here}</code>
    </div>
</div>
<div class="container-fluid col-md-8 col-md-offset-2">
    <h2>Searching by Latitude Longitude</h2>
    <br />
    <div class="well">
        <p>If you know approximate coordinates, find information on the nearest waterfall.</p>
        <code>http://localhost:8080/waterfalls/{coordinates here}</code>
    </div>
</div>

</body>
</html>