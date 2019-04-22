<%@include file="taglib.jsp"%>
<c:set var="title" value = "Waterfall Finder"/>
<%@include file="header.jsp"%>
<html>
<style>
    body {
        font-family: "Lucida Sans Unicode", "Lucida Grande", sans-serif;
    }

    footer {
        margin-bottom: 20px;
    }

    h1 {
        text-shadow: 3px 3px black;
    }

    .well {

    }

    .jumbotron {
        background-image: url(https://upload.wikimedia.org/wikipedia/commons/0/0d/Kravica_Waterfalls%2CBosnia_%26_Herzegovina.jpg);
        background-size: cover;
        color: white;
        padding: 100px 25px;
        margin-bottom: 0px;
    }

    .container-fluid {
        padding: 60px 50px;
    }
    p {
        font-style: italic;
    }

    input {
        width: 90%;
    }
</style>
<script type="text/javascript">
    function copyToClip() {
        let copyInfo = document.getElementById("waterfallInfo");
        copyInfo.select();
        document.execCommand("copy");
        alert("URL Copied");
    }

    function copyToClipByZip() {
        let copyByZip = document.getElementById("byZip");
        copyByZip.select();
        document.execCommand("copy");
    }

    function copyToClipByName() {
        let copyByName = document.getElementById("byName");
        copyByName.select();
        document.execCommand("copy");
    }

    function copyToClipByLatLng() {
        let copyByLatLng = document.getElementById("byLatLng");
        copyByLatLng.select();
        document.execCommand("copy");
    }

    function copyToClipAll() {
        let allWaterfalls = document.getElementById("all");
        allWaterFalls.select();
        document.execCommand("copy");
    }
</script>
</script>
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
    <p>Use this base URL :</p><input id="waterfallInfo" value="http://localhost:8080/waterfalls/" /><button onclick="copyToClip()">Copy</button>
</div>
</div>
<div class="container-fluid col-md-8 col-md-offset-2">
    <h2>Waterfall By Zip Code</h2>
<br />
<div class="row">
    <div class="well" id="userWell">
    <p>Use this base URL :</p><input id="byZip" value="http://localhost:8080/waterfalls/zipcode/53705" /><button onclick="copyToClipByZip()">Copy</button>
</div>
</div>
</div>
<div class="container-fluid col-md-8 col-md-offset-2">
    <h2>WaterFall by Name</h2>
<br />
<div class="row">
    <div class="well" id="userWell">
    <p>Use this base URL :</p><input id="byName" value="http://localhost:8080/waterfalls/name/Little%20River%20Falls" /><button onclick="copyToClipByName()">Copy</button>
</div>
</div>
</div>
<div class="container-fluid col-md-8 col-md-offset-2">
    <h2>Waterfall by Latitude and Longitude</h2>
<br />
<div class="row">
    <div class="well" id="userWell">
    <p>Use this base URL :</p><input id="byLatLng" value="http://localhost:8080/waterfalls/latitude/-40.0/longitude/100.00011" /><button onclick="copyToClipByLatLng()">Copy</button>
</div>
</div>
</div>
<div class="container-fluid col-md-8 col-md-offset-2">
    <h2>All Waterfalls</h2>
<div class="row">
    <div class="well" id="userWell">
    <p>Use this base URL :</p><input id="all" value="http://localhost:8080/waterfalls/all" /><button onclick="copyToClipAll()">Copy</button>
</div>
</div>
</div>
</body>
</html>

