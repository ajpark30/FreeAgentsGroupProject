package edu.matc.util;

import edu.matc.entity.Coordinates;
import edu.matc.entity.Waterfall;
import edu.matc.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Provides access the database
 * Created on 8/31/16.
 *
 * @author pwaite
 */

public class Database {

    private final int DEFAULT_RESULTS_COUNT = 10;

    private final Logger logger = LogManager.getLogger(this.getClass());
    // create an object of the class Database
    private static Database instance = new Database();

    private Properties properties;

    private Connection connection;

    // private constructor prevents instantiating this class anywhere else
    private Database() {
        loadProperties();

    }

    private void loadProperties() {
        properties = new Properties();
        try {
            properties.load (this.getClass().getResourceAsStream("/database.properties"));
        } catch (IOException ioe) {
            System.out.println("Database.loadProperties()...Cannot load the properties file");
            ioe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Database.loadProperties()..." + e);
            e.printStackTrace();
        }

    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
// get the only Database object available
    public static Database getInstance() {
        return instance;
    }

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Connect.
     *
     * @throws Exception the exception
     */
    public void connect() throws Exception {
        if (connection != null)
            return;

        try {
            Class.forName(properties.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            throw new Exception("Database.connect()... Error: MySQL Driver not found");
        }

        String url = properties.getProperty("url");
        connection = DriverManager.getConnection(url, properties.getProperty("username"),  properties.getProperty("password"));
    }

    /**
     * Disconnect.
     */
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Cannot close connection" + e);
            }
        }

        connection = null;
    }

    /**
     * Find nearest waterfalls to lat/lon.
     *
     * @param latitude  the decimal latitude
     * @param longitude the decimal longitude
     * @return the list of waterfalls
     */
    public List<Waterfall> findNearest(double latitude, double longitude, int resultCount) {
        List<Waterfall> waterfalls = new ArrayList<>();
        Statement stmt = null;
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connect();
            stmt = connection.createStatement();
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
                                + " 15000.0 /* no narrowing with large value, more results; use a smaller value for performance */ AS radius,"
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
                        + "\nDistance: " + results.getDouble("distance")
                );
            }
        } catch (SQLException se) {
            logger.error(se);
        } catch (Exception e) {
            logger.error(e);
        } finally {
            disconnect();
        }
        return waterfalls;
    }

    /**
     * Find waterfalls near coordinates.
     *
     * @param latitude the latitude
     * @param longitude the longitude
     * @return the list of waterfalls
     */
    public List<Waterfall> findNearest(double latitude, double longitude) {
        return this.findNearest(latitude, longitude, DEFAULT_RESULTS_COUNT);
    }

    /**
     * Find waterfalls near coordinates.
     *
     * @param coords the coords
     * @return the list
     */
    public List<Waterfall> findWaterfallsNear(Coordinates coords) {
        return this.findNearest(coords.getLatitude(), coords.getLongitude(), DEFAULT_RESULTS_COUNT);
    }

    /**
     * Find waterfalls near coordinates.
     *
     * @param coords the coords
     * @return the list
     */
    public List<Waterfall> findWaterfallsNear(Coordinates coords, int resultCount) {
        return this.findNearest(coords.getLatitude(), coords.getLongitude(), resultCount);
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
            connect();
            stmt = connection.createStatement();
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
            disconnect();
        }

        return coords;
    }

    /**
     * Run the sql.
     *
     * @param sqlFile the sql file to be read and executed line by line
     */
//    public void runSQL(String sqlFile) {
//
//        Statement stmt = null;
//        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//        InputStream inputStream = classloader.getResourceAsStream(sqlFile);
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
//
//            Class.forName("com.mysql.jdbc.Driver");
//            connect();
//            stmt = connection.createStatement();
//
//            while (true) {
//                String sql = br.readLine();
//                if (sql == null) {
//                    break;
//                }
//                stmt.executeUpdate(sql);
//
//            }
//
//        } catch (SQLException se) {
//            logger.error(se);
//        } catch (Exception e) {
//            logger.error(e);
//        } finally {
//            disconnect();
//        }
//
//    }
}