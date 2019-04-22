<%@include file="taglib.jsp"%>
<c:set var="title" value = "Waterfall Finder"/>
<%@include file="header.jsp"%>
<html>
<body>
<div class="jumbotron text-center">
    <h1>Waterfall RESTful Service</h1>
    <p>You can receive waterfall information in a JSON format by hitting our REST endpoints.</p>
</div>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <li class="active"><a href="">Home</a></li>
            <li><a href="documentation.jsp">Documentation</a></li>
        </ul>
    </div>
</nav>
<div class="container-fluid col-md-8 col-md-offset-2">
    <h2>Requesting our waterfall information</h2>
    <br />
    <div class="well">
        <a href="http://18.221.180.234:8080/waterfallRest/">http://18.221.180.234:8080/waterfallRest/</a>
        <p>Use this base URL :</p><input id="waterfallInfo" value="http://18.221.180.234:8080/waterfallRest/" /><button onclick="copyToClip()">Copy</button>
    </div>
</div>
<div class="container-fluid col-md-8 col-md-offset-2">
    <h2>Waterfall By Zip Code</h2>
    <br />
    <div class="row">
        <div class="well" id="userWell">
            <a href="http://18.221.180.234:8080/waterfallRest/zipcode/53705">http://18.221.180.234:8080/waterfallRest/zipcode/53705</a>
            <p>Use this base URL :</p><input id="byZip" value="http://18.221.180.234:8080/waterfallRest/zipcode/53705" /><button onclick="copyToClipByZip()">Copy</button>
        </div>
    </div>
</div>
<div class="container-fluid col-md-8 col-md-offset-2">
    <h2>WaterFall by Name</h2>
    <br />
    <div class="row">
        <div class="well" id="userWell">
            <a href="http://18.221.180.234:8080/waterfallRest/likeName/Little%20River%20Falls" >http://18.221.180.234:8080/waterfallRest/likeName/Little%20River%20Falls</a>
            <p>Use this base URL :</p><input id="byName" value="http://18.221.180.234:8080/waterfallRest/likeName/Little%20River%20Falls" /><button onclick="copyToClipByName()">Copy</button>
        </div>
    </div>
</div>
<div class="container-fluid col-md-8 col-md-offset-2">
    <h2>WaterFall by Exact Name</h2>
    <br />
    <div class="row">
        <div class="well" id="userWell">
            <a href="http://18.221.180.234:8080/waterfallRest/exactName/Little%20River%20Falls" >http://18.221.180.234:8080/waterfallRest/exactName/Little%20River%20Falls</a>
            <p>Use this base URL :</p><input id="byName" value="http://18.221.180.234:8080/waterfallRest/exactName/Little%20River%20Falls" /><button onclick="copyToClipByName()">Copy</button>
        </div>
    </div>
</div>
<div class="container-fluid col-md-8 col-md-offset-2">
    <h2>Waterfall by Latitude and Longitude</h2>
    <br />
    <div class="row">
        <div class="well" id="userWell">
            <a href="http://18.221.180.234:8080/waterfallRest/latitude/-40.0/longitude/100.00011" >http://18.221.180.234:8080/waterfallRest/latitude/-40.0/longitude/100.00011</a>
            <p>Use this base URL :</p><input id="byLatLng" value="http://18.221.180.234:8080/waterfallRest/latitude/-40.0/longitude/100.00011" /><button onclick="copyToClipByLatLng()">Copy</button>
        </div>
    </div>
</div>
<div class="container-fluid col-md-8 col-md-offset-2">
    <h2>All Waterfalls</h2>
    <div class="row">
        <div class="well" id="userWell">
            <a href="http://18.221.180.234:8080/waterfallRest/all" >http://18.221.180.234:8080/waterfallRest/all</a>
            <p>Use this base URL :</p><input id="all" value="http://18.221.180.234:8080/waterfallRest/all" /><button onclick="copyToClipAll()">Copy</button>
        </div>
    </div>
</div>
</body>
</html>


