<%@include file="taglib.jsp"%>
<c:set var="title" value = "Waterfall Finder"/>
<%@include file="header.jsp"%>
<html>
<body>
    <div class="container col-md-8 col-md-offset-2">
        <h1>Search For the Nearest Waterfall</h1>
        <br />
        <div class="row">
            <div class="well" id="userWell">
                <form class="form-horizontal" action="searchWaterfall">
                    <div class="form-group">
                        <label class="control-label col-sm-5" for="searchTerm">Enter a Zip Code</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="searchTerm" name="searchTerm" aria-describedby="searchTermHelp" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-6 col-sm-6">
                            <button type="submit" name="submit" value="search" class="btn btn-primary">Search</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="container col-md-8 col-md-offset-2">
        <h1>Search All Waterfalls</h1>
        <div class="row">
            <div class="well" id="userWell">
                <form class="form-horizontal" action="searchWaterFall">
                    <div class="form-group">
                        <div class="col-sm-offset-6 col-sm-6">
                            <button type="submit" name="submit" value="viewAll" class="btn btn-primary">Search</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>

