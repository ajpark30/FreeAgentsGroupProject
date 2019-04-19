package edu.matc.persistence;

import edu.matc.entity.Coordinates;
import edu.matc.entity.Waterfall;
import edu.matc.util.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WaterfallDao extends GenericDao {

    private final int DEFAULT_RESULTS_COUNT = 10;
    private final int DEFAULT_WITHIN_DISTANCE = 10000;

    private Database database = Database.getInstance();
    private final Logger logger = LogManager.getLogger(this.getClass());

    public WaterfallDao() {
        super(Waterfall.class);
    }

    /**
     * Find nearest waterfalls to lat/lon.
     *
     * @param latitude  the decimal latitude
     * @param longitude the decimal longitude
     * @param resultCount the maximum number of results to return
     * @param withinDistance search radius from provided point to find results
     * @return the list of waterfalls
     */
    public List<Waterfall> findNearest(double latitude, double longitude, int resultCount, double withinDistance) {

        List<Waterfall> waterfalls = new ArrayList<>();
        Statement stmt = null;
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try {

            Class.forName("com.mysql.jdbc.Driver");
            database.connect();
            stmt = database.getConnection().createStatement();

            String sql =
/*                  # find nearest object to coordinates using great circle distance
                    # after narrowing a range search with bounding boxes
                    # adapted from https://www.plumislandmedia.net/mysql/haversine-mysql-nearest-loc/
*/
                    "SELECT waterfall_id, "
                            + " latitude, longitude, distance"
                            + " FROM ("
                            + " SELECT w.waterfall_id, "
                            + " w.latitude, w.longitude,"
                            + " p.radius,"
                            + " p.distance_unit"
                            + " * DEGREES(ACOS(COS(RADIANS(p.latpoint))"
                            + " * COS(RADIANS(w.latitude))"
                            + " * COS(RADIANS(p.longpoint - w.longitude))"
                            + " + SIN(RADIANS(p.latpoint))"
                            + " * SIN(RADIANS(w.latitude)))) AS distance"
                            + " FROM waterfall AS w"
                            + " JOIN (   /* these are the query parameters */"
                            + " SELECT  "
                            + latitude + "/*input latitude*/  AS latpoint,"
                            + longitude + "/* input longitude */ AS longpoint,"
                            + " " + withinDistance + /* no narrowing with large value, more results; use a smaller value for performance */ " AS radius,"
                            + " /*111.045 for km*/ 69.0/* for miles*/ AS distance_unit"
                            + " ) AS p ON 1=1"
                            + " WHERE w.latitude"
                            + " BETWEEN p.latpoint  - (p.radius / p.distance_unit)"
                            + " AND p.latpoint  + (p.radius / p.distance_unit)"
                            + " AND w.longitude"
                            + " BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))"
                            + " AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))"
                            + " ) AS d"
                            + " WHERE distance <= radius"
                            + " ORDER BY distance ASC"
                            + " LIMIT " + resultCount;

            ResultSet results = stmt.executeQuery(sql);

            GenericDao<Waterfall> waterfallDao = new GenericDao(Waterfall.class);
            while (results.next()) {
                int row = results.getInt("waterfall_id");
                waterfalls.add(waterfallDao.getById(row));

                logger.debug("Row: " + row
                        + "\nDistance: "
                        + results.getDouble("distance")
                );
            }
        } catch (SQLException se) {
            logger.error(se);
        } catch (Exception e) {
            logger.error(e);
        } finally {
            database.disconnect();
        }
        return waterfalls;
    }

    /**
     * Find waterfalls near coordinates.
     *
     * @param latitude the latitude
     * @param longitude the longitude
     * @param resultCount the maximum number of results to return
     * @return the list of waterfalls
     */
    public List<Waterfall> findNearest(double latitude, double longitude, int resultCount) {
        return this.findNearest(latitude, longitude, resultCount, DEFAULT_WITHIN_DISTANCE);
    }

    /**
     * Find waterfalls near coordinates.
     *
     * @param latitude the latitude
     * @param longitude the longitude
     * @return the list of waterfalls
     */
    public List<Waterfall> findNearest(double latitude, double longitude) {
        return this.findNearest(latitude, longitude, DEFAULT_RESULTS_COUNT, DEFAULT_WITHIN_DISTANCE);
    }

    /**
     * Find waterfalls near coordinates.
     *
     * @param coords the coords
     * @return the list
     */
    public List<Waterfall> findWaterfallsNear(Coordinates coords) {
        return this.findNearest(coords.getLatitude(), coords.getLongitude(), DEFAULT_RESULTS_COUNT, DEFAULT_WITHIN_DISTANCE);
    }

    /**
     * Find waterfalls near coordinates.
     *
     * @param coords the coords
     * @return the list
     */
    public List<Waterfall> findWaterfallsNear(Coordinates coords, int resultCount) {
        return this.findNearest(coords.getLatitude(), coords.getLongitude(), resultCount, DEFAULT_WITHIN_DISTANCE);
    }

    /**
     * Coordinates from zipcode.
     *
     * @param zipcode the zipcode
     * @return the coordinates
     */
    public Coordinates coordsFromZipcode(String zipcode) {
        Coordinates coords = new Coordinates();

        Statement stmt = null;
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try {

            Class.forName("com.mysql.jdbc.Driver");
            database.connect();
            stmt = database.getConnection().createStatement();
            String sql = "SELECT latitude, longitude FROM zip_coords WHERE zipcode LIKE '" + zipcode.substring(0, 3) + "%'";
            ResultSet results = stmt.executeQuery(sql);

            while (results.next()) {
                coords.setLatitude(results.getDouble("latitude"));
                coords.setLongitude(results.getDouble("longitude"));
            }

        } catch (SQLException se) {
            logger.error(se);
        } catch (Exception e) {
            logger.error(e);
        } finally {
            database.disconnect();
        }

        return coords;
    }

}
