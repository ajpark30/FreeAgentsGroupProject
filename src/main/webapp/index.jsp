<%@include file="taglib.jsp"%>
<c:set var="title" value = "Waterfall Finder"/>
<%@include file="header.jsp"%>
<html>
<body>
<div class="container col-md-8 col-md-offset-2"><h1>Waterfall Searches</h1></div>
    <div class="container col-md-8 col-md-offset-2">
        <h2>Search By Zipcode</h2>
        <br />
        <div class="row">
            <div class="well" id="userWell">
                <form class="form-horizontal" action="searchWaterFall">
                    <div class="form-group">
                        <label class="control-label col-sm-5" for="searchZip">Enter a Zip Code</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="searchZip" name="searchZip" aria-describedby="searchTermHelp" required/>
                        </div>
                        <div class="form-group">
                        <div class="col-sm-offset-6 col-sm-6">
                            <button type="submit" name="submit" value="searchByZip" class="btn btn-primary">Search</button>
                        </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="container col-md-8 col-md-offset-2">
        <h2>Search by Name</h2>
        <br />
        <div class="row">
            <div class="well" id="userWell">
                <form class="form-horizontal" action="searchWaterFall">
                    <div class="form-group">
                        <label class="control-label col-sm-5" for="searchName">Enter a Name</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="searchName" name="searchName" aria-describedby="searchTermHelp" required/>
                        </div>
                        <div class="form-group">
                        <div class="col-sm-offset-6 col-sm-6">
                            <button type="submit" name="submit" value="searchByName" class="btn btn-primary">Search</button>
                        </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="container col-md-8 col-md-offset-2">
        <h2>Search by Latitude and Longitude</h2>
        <br />
        <div class="row">
            <div class="well" id="userWell">
                <form class="form-horizontal" action="searchWaterFall">
                    <div class="form-group">
                        <label class="control-label col-sm-5" for="searchLatitude">Enter Latitude</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="searchLatitude" name="searchLatitude" aria-describedby="searchTermHelp" required/>
                        </div>
                        <label class="control-label col-sm-5" for="searchLongitude">Enter Longitude</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="searchLongitude" name="searchLongitude" aria-describedby="searchTermHelp" required/>
                        </div>
                        <div class="form-group">
                        <div class="col-sm-offset-6 col-sm-6">
                            <button type="submit" name="submit" value="searchByLatLong" class="btn btn-primary">Search</button>
                        </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="container col-md-8 col-md-offset-2">
        <h2>Search All Waterfalls</h2>
        <div class="row">
            <div class="well" id="userWell">
                <form class="form-horizontal" action="searchWaterFall">
                    <div class="form-group">
                        <div class="col-sm-offset-6 col-sm-6">
                            <button type="submit" name="submit" value="searchAll" class="btn btn-primary">Search</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>

