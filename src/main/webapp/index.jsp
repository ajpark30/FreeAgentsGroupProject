<%@include file="taglib.jsp"%>
<c:set var="title" value = "Waterfall Finder"/>
<%@include file="header.jsp"%>
<html>
<body>
<div class="jumbotron text-center">
    <h1>Waterfall RESTful Service</h1>
    <p id="headingDetails">You can receive waterfall information in a JSON format by hitting our REST endpoints.</p>
</div>
<div class="container-fluid col-md-8 col-md-offset-2">
    <h2>Requesting our waterfall information</h2>
    <br />
    <div class="well">
        <%--<a href="http://18.221.180.234:8080/waterfallRest/waterfalls/">http://18.221.180.234:8080/waterfallRest/waterfalls/</a>--%>
        <%--<p>Use this base URL :</p><input id="waterfallInfo" value="http://18.221.180.234:8080/waterfallRest/waterfalls/" /><button onclick="copyToClip()">Copy</button>--%>
            <p>Request our waterfall information with the following base URL:</p>
            <code>http://18.221.180.234:8080/waterfallRest/waterfalls/</code><button class="btn btn-info btn-sm ml-3">Copy</button>
    </div>
</div>
<div class="container-fluid col-md-8 col-md-offset-2">
    <h2>Searching by Zipcode - Find Nearest Waterfall</h2>
    <br />
    <div class="well">
        <p>After entering a valid zipcode the program will return results nearest you. Containing the exact location
            in latitude and longitude</p>
        <i>Format:&nbsp;&nbsp;</i><code>http://18.221.180.234:8080/waterfallRest/waterfalls/zipcode/{zipcode here}</code><button class="btn btn-info btn-sm ml-3">Copy</button>
        <br><br>
        <p><i>Sample URL:&nbsp;&nbsp;</i><a href="http://18.221.180.234:8080/waterfallRest/waterfalls/zipcode/53705">http://18.221.180.234:8080/waterfallRest/waterfalls/zipcode/53705</a></p>
    </div>
</div>

<div class="container-fluid col-md-8 col-md-offset-2">
    <h2>Searching by Name - Get Location & Photo</h2>
    <br />
    <div class="well">
        <p>If you know the name of the waterfall you wish to search, you will be provided location information
            and a photo.</p>
        <i>Format:&nbsp;&nbsp;</i><code>http://18.221.180.234:8080/waterfallRest/waterfalls/name/name/{name here}</code><button class="btn btn-info btn-sm ml-3">Copy</button>
        <br><br>
        <p><i>Sample URL:&nbsp;&nbsp;</i><a href="http://18.221.180.234:8080/waterfallRest/waterfalls/name/name/Little%20River%20Falls">http://18.221.180.234:8080/waterfallRest/waterfalls/name/name/Little%20River%20Falls</a></p>
    </div>
</div>

<div class="container-fluid col-md-8 col-md-offset-2">
    <h2>Searching by Latitude Longitude</h2>
    <br>
    <div class="well">
        <p>If you know approximate coordinates, find information on the nearest waterfall.</p>
        <i>Format:&nbsp;&nbsp;</i><code>http://18.221.180.234:8080/waterfallRest/waterfalls/latitude/{latitude}/longitude/{longitude}</code><button class="btn btn-info btn-sm ml-3">Copy</button>
        <br><br>
        <p><i>Sample URL:&nbsp;&nbsp;</i><a href="http://18.221.180.234:8080/waterfallRest/waterfalls/latitude/-40.0/longitude/100.00011">http://18.221.180.234:8080/waterfallRest/waterfalls/latitude/-40.0/longitude/100.00011</a></p>
    </div>
</div>

<div class="container-fluid col-md-8 col-md-offset-2">
    <h2>All Waterfalls</h2>
    <br>
    <div class="well">
        <p>A list of all waterfalls can be found at the flowing URL:</p>
        <a href="http://18.221.180.234:8080/waterfallRest/waterfalls/all">http://18.221.180.234:8080/waterfallRest/waterfalls/all</a>
    </div>
</div>

</body>
</html>


