package edu.matc;

import edu.matc.entity.Coordinates;
import edu.matc.entity.Photo;
import edu.matc.entity.Waterfall;
import edu.matc.persistence.GenericDao;
import edu.matc.persistence.WaterfallDao;

import javax.persistence.GeneratedValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/get")
public class GetWaterfalls {

    private final WaterfallDao waterfallDao = new WaterfallDao();
    private final GenericDao<Waterfall> waterfallGenericDao = new GenericDao(Waterfall.class);
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Waterfall> getAllWaterfallInformation() {

        List<Waterfall> waterfalls = waterfallGenericDao.getAll();

        return waterfalls;

    }


    @GET
    @Path("/byZipcode/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Waterfall> getWaterfallsByZipcode(@PathParam("param") String location) {

        Coordinates waterfallCoordinates = waterfallDao.coordsFromZipcode(location);
        List<Waterfall> waterfallListByZipcode = waterfallDao.findWaterfallsNear(waterfallCoordinates);

        return waterfallListByZipcode;
    }

    @GET
    @Path("byName/{param}/{param2}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Waterfall> getWaterfallsByName(@PathParam("param") String searchTerm, @PathParam("param2") String name) {

        List<Waterfall> waterfallListByName = waterfallGenericDao.getByPropertyEqual(searchTerm, name);

        return waterfallListByName;
    }

}

