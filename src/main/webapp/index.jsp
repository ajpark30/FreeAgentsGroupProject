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
            <p>Use this base URL : http://localhost:8080/waterfalls/ - this doesn't seem to work<br>
                <a href="http://localhost:8080/waterfalls/">http://localhost:8080/waterfalls/</a></p>
        </div>
    </div>
    <div class="container col-md-8 col-md-offset-2">
        <h2>Waterfall By Zip Code</h2>
        <br />
        <div class="row">
            <div class="well" id="userWell">
                <p>URL : http://localhost:8080/waterfalls/zipcode/{Enter your zipcode here}<br />
                    <a href="http://localhost:8080/waterfalls/zipcode/53705">http://localhost:8080/waterfalls/zipcode/53705</a></p>
            </div>
        </div>
    </div>
    <div class="container col-md-8 col-md-offset-2">
        <h2>WaterFall by Name</h2>
        <br />
        <div class="row">
            <div class="well" id="userWell">
                <p>URL : http://localhost:8080/waterfalls/name/{Enter name of waterfall here} -- this doesn't seem to work<br />
                    <a href="http://localhost:8080/waterfalls/name/Little%20River%20Falls">http://localhost:8080/waterfalls/name/Little%20River%20Falls</a></p>
            </div>
        </div>
    </div>
    <div class="container col-md-8 col-md-offset-2">
        <h2>Waterfall by Latitude and Longitude</h2>
        <br />
        <div class="row">
            <div class="well" id="userWell">
               <p>URL : http://localhost:8080/waterfalls/latitude/{Enter latitude}/longitude/{Enter longitude} <br/>
                   <a href="http://localhost:8080/waterfalls/latitude/-40.0/longitude/100.00011">http://localhost:8080/waterfalls/latitude/-40.0/longitude/100.00011</a></p>
            </div>
        </div>
    </div>
    <div class="container col-md-8 col-md-offset-2">
        <h2>All Waterfalls</h2>
        <div class="row">
            <div class="well" id="userWell">
                <p>URL : http://localhost:8080/waterfalls/all<br>
                    <a href="http://localhost:8080/waterfalls/all">http://localhost:8080/waterfalls/all</a></p>
            </div>
        </div>
    </div>
</body>
</html>

