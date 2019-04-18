package edu.matc.api;

import edu.matc.persistence.GenericDao;
import edu.matc.entity.Waterfall;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/waterfall")
public class WaterfallEndpoints {

    private static final GenericDao<Waterfall> DAO = new GenericDao<>(Waterfall.class);

    @GET
    public Response getWaterfall(@QueryParam("id") String id) {
        List<Waterfall> waterfalls = DAO.getByPropertyEqual("waterfall_id", id);

        if (waterfalls.size() == 0) {
            return Response.status(404).entity("Waterfall not found").build();
        }

        return Response.status(200).entity(waterfalls.get(0).getName()).build();
    }

    @DELETE
    public Response deleteWaterfall(@QueryParam("id") String id) {
        List<Waterfall> waterfalls = DAO.getByPropertyEqual("waterfall_id", id);

//        if (waterfalls.size()) {
//        }
        return Response.status(200).entity("...").build();
    }

}
