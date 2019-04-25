package edu.matc;

import edu.matc.entity.Coordinates;
import edu.matc.entity.Waterfall;
import edu.matc.persistence.WaterfallDao;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;


/**
 * Get api endpoints.
 */
@Path("/")
public class GetWaterfalls {

    private final WaterfallDao waterfallDao = new WaterfallDao();

    /**
     * Gets all waterfalls.
     *
     * @return all waterfalls
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Waterfall> getAllWaterfallInformation() {
        List<Waterfall> waterfalls = waterfallDao.getAll();
        return waterfalls;
    }


    /**
     * Gets waterfalls by zipcode.
     *
     * @param location the zipcode
     * @return the waterfalls by zipcode
     */
    @GET
    @Path("/zipcode/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Waterfall> getWaterfallsByZipcode(@PathParam("param") String location) {
        Coordinates waterfallCoordinates = waterfallDao.coordsFromZipcode(location);
        List<Waterfall> waterfallListByZipcode = waterfallDao.findWaterfallsNear(waterfallCoordinates);
        return waterfallListByZipcode;
    }

    /**
     * Gets waterfalls by name.
     *
     * @param searchTerm the name
     * @return the waterfalls by name
     */
    @GET
    @Path("name/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Waterfall> getWaterfallsByName(@PathParam("param") String searchTerm) {
        List<Waterfall> waterfallListByName = waterfallDao.getByPropertyEqual("name", searchTerm);
        return waterfallListByName;
    }

    /**
     * Gets waterfalls by latitude and longitude.
     *
     * @param latitude  the latitude
     * @param longitude the longitude
     * @return the waterfalls by latitude and longitude
     */
    @GET
    @Path("latitude/{param}/longitude/{param2}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Waterfall> getWaterfallsByLatLong(@PathParam("param") Double latitude, @PathParam("param2") Double longitude){
        List<Waterfall> waterfallListByLatLong = waterfallDao.findNearest(latitude, longitude);
        return waterfallListByLatLong;
    }

    /**
     * Gets waterfalls by location.
     *
     * @param searchTerm the search term
     * @param location   the location
     * @return the waterfalls by location
     */
    @GET
    @Path("location/{param}/{param2}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Waterfall> getWaterfallsByLocation(@PathParam("param") String searchTerm, @PathParam("param2") String location) {
        List<Waterfall> waterfallListByLocation = waterfallDao.getByPropertyEqual(searchTerm, location);
        return waterfallListByLocation;
    }

    /**
     * Gets waterfalls by like name.
     *
     * @param searchTerm the like name
     * @return the waterfalls by like name
     */
    @GET
    @Path("nameLike/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Waterfall> getWaterfallsByLikeName(@PathParam("param") String searchTerm) {
        List<Waterfall> waterfallListByLikeName = waterfallDao.getByPropertyLike("name", searchTerm);
        return waterfallListByLikeName;
    }

}

