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


@Path("/")
public class GetWaterfalls {

    private final WaterfallDao waterfallDao = new WaterfallDao();
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Waterfall> getAllWaterfallInformation() {
        List<Waterfall> waterfalls = waterfallDao.getAll();
        return waterfalls;
    }


    @GET
    @Path("/zipcode/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Waterfall> getWaterfallsByZipcode(@PathParam("param") String location) {
        Coordinates waterfallCoordinates = waterfallDao.coordsFromZipcode(location);
        List<Waterfall> waterfallListByZipcode = waterfallDao.findWaterfallsNear(waterfallCoordinates);
        return waterfallListByZipcode;
    }

    @GET
    @Path("name/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Waterfall> getWaterfallsByName(@PathParam("param") String searchTerm) {
        List<Waterfall> waterfallListByName = waterfallDao.getByPropertyEqual("name", searchTerm);
        return waterfallListByName;
    }

    @GET
    @Path("latitude/{param}/longitude/{param2}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Waterfall> getWaterfallsByLatLong(@PathParam("param") Double latitude, @PathParam("param2") Double longitude){
        List<Waterfall> waterfallListByLatLong = waterfallDao.findNearest(latitude, longitude);
        return waterfallListByLatLong;
    }

    @GET
    @Path("location/{param}/{param2}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Waterfall> getWaterfallsByLocation(@PathParam("param") String searchTerm, @PathParam("param2") String location) {
        List<Waterfall> waterfallListByLocation = waterfallDao.getByPropertyEqual(searchTerm, location);
        return waterfallListByLocation;
    }

    @GET
    @Path("nameLike/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Waterfall> getWaterfallsByLikeName(@PathParam("param") String searchTerm) {
        List<Waterfall> waterfallListByLikeName = waterfallDao.getByPropertyLike("name", searchTerm);
        return waterfallListByLikeName;
    }

}

