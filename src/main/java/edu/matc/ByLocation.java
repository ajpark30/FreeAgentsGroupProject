package edu.matc;

import edu.matc.entity.Waterfall;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/findByLocation")
public class ByLocation {
    // The Java method will process HTTP GET requests
    @GET
    @Path("/{param}")
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessage(@PathParam("param") String location) {

        Waterfall waterfall = new Waterfall();

        // Return a simple message
        String output = "What waterfall location did you pass? " + location;
        return Response.status(200).entity(output).build();
    }

}

