package edu.matc.api;

import edu.matc.persistence.GenericDao;
import edu.matc.entity.Waterfall;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/waterfall")
public class WaterfallEndpoints {

    private static final GenericDao<Waterfall> DAO = new GenericDao<>(Waterfall.class);

    @GET
    public Response getWaterfall(@QueryParam("id") String id) {
        Waterfall waterfall = DAO.getByPropertyEqual("waterfall_id", "1").get(0);

        return Response.status(200).entity(waterfall.getName()).build();
    }

}
