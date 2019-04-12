# Free Agents Group Project
## Waterfall REST API

## Concept:
As long as water flows downhill, waterfalls will run over cliff faces large and small, compelling wonder and awe.
This API accepts location parameters via a REST endpoint and supplies a JSON response with information about nearby falls.  
[Waterfall info](start_doc.md)

### Job assignments 
Git Master - Andrew  
Unit Testing - Blake
*These roles need to be filled*

End points -  
Usage Documentation -  
Example Client App - Andrew 
Data acquisition, wget, JSoup -  
Database admin -  Andrew

### REST Endpoints

waterfalls/ GET - Andrew created a class "waterfallByLocation", you can refactor name if needed.   
waterfalls/ POST  
waterfalls/ PUT  
waterfalls/ DELETE


### Example Application

Home page - index.jsp created with waterfallResults.jsp
- Ask user for location

Results List
- Display nearest falls in order by distance

### Example Client App
Andrew
TO-DO
add hibernate property files
add jsoup dependency
connect search with results by pulling data from DB

DONE
Simple search page with a zipcode search and a search all
Result page

### Create a hardcoded database
Andrew
TO-DO
Create DB properties files

DONE
Created sql queries to create DB hard coded data "createDatabase.sql" with three waterfalls
ONE BIG QUESTION I HAVE IS THE WAY WE WANT TO STORE THE COORDINATES?????? I created three different options and figured we all could talk about it.

*Two or three falls would be enough to show the closest*
