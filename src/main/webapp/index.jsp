<%@include file="taglib.jsp"%>
<c:set var="title" value = "Waterfall Finder"/>
<%@include file="header.jsp"%>
<html>
<body>
    <div class="container col-md-8 col-md-offset-2">
        <h1>Waterfall RESTful Service</h1>
        <p>You can receive waterfall information in a JSON format by hitting our REST endpoints.</p>
        <p></p>
        <br />
        <div class="well">
            <h2>Requesting our waterfall information</h2>
            <p>Use this base URL : http://localhost:8080/waterfalls/</p>
        </div>
    </div>
    <div class="container col-md-8 col-md-offset-2">
        <h2>Waterfall By Zip Code</h2>
        <br />
        <div class="row">
            <div class="well" id="userWell">
                <p>URL : http://localhost:8080/waterfalls/zipcode/{Enter your zipcode here}</p>
            </div>
        </div>
    </div>
    <div class="container col-md-8 col-md-offset-2">
        <h2>WaterFall by Name</h2>
        <br />
        <div class="row">
            <div class="well" id="userWell">
                <p>URL : http://localhost:8080/waterfalls/name/{Enter name of waterfall here}</p>
            </div>
        </div>
    </div>
    <div class="container col-md-8 col-md-offset-2">
        <h2>Waterfall by Latitude and Longitude</h2>
        <br />
        <div class="row">
            <div class="well" id="userWell">
               <p>URL : http://localhost:8080/waterfalls/latitude/{Enter latitude}/longitude/{Enter longitude}</p>
            </div>
        </div>
    </div>
    <div class="container col-md-8 col-md-offset-2">
        <h2>All Waterfalls</h2>
        <div class="row">
            <div class="well" id="userWell">
                <p>URL : http://localhost:8080/waterfalls/all</p>
            </div>
        </div>
    </div>
</body>
</html>

