package edu.matc.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.TypeMismatchException;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Objects;

/**
 * The type Coordinates.
 */
public class Coordinates {

    private double latitude;
    private double longitude;

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new Coordinates.
     */
    public Coordinates() {
    }

    /**
     * Instantiates a new Coordinates.
     *
     * @param latitude  the latitude
     * @param longitude the longitude
     */
    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

//
//    /**
//     * Instantiates a new Coordinates.
//     *
//     * @param coords the coordinates as a string
//     */
//    public Coordinates(String coords) throws Exception {
//        // "5.96750°N 62.53556°W"
//        if (coords.matches("\\d+\\.\\d+.\\w\\s+\\d+\\.\\d+.\\w.*")) {
//            double[] latLon = getLatLonFromString(coords);
//            this.longitude = latLon[0];
//            this.longitude = latLon[1];
//        } else {
//            throw new TypeMismatchException("Tried to create new Coordinates from unrecognized String format: " + coords);
//        }
//    }
//
//    /**
//     * Get latitude and longitude from string coords
//     * @param coords string with geospatial coordinates
//     * @return double[] of length two, coordinate pair: [lat, lon]
//     */
//    public double[] getLatLonFromString(String coords) {
//        //"5.96750°N 62.53556°W..."
//        double[] latLon = new double[2];
//
//        String[] parts = coords.trim().split("\\s+");
//
//        latLon[0] = getLatitudeFromString(parts[0]);
//        latLon[1] = getLongitudeFromString(parts[1]);
//
//        return latLon;
//    }
//
//    /**
//     * Get decimal latitude from string
//     * @param latitude the latitude
//     * @return latitude as a double
//     */
//    private double getLatitudeFromString(String latitude) {
//        //"5.96750°N"
//        logger.debug("latitude", latitude);
//        double lat = Double.parseDouble(latitude.trim().replaceAll("[^ \\d\\.]", ""));
//
//        if (latitude.substring(-1) == "S") {
//            lat = -lat;
//        } else if (latitude.substring(-1) == "N") {
//            // stays positive
//        }
//
//        return lat;
//    }
//
//    /**
//     * Get decimal longitude from string
//     * @param longitude the longitude
//     * @return longitude as a double
//     */
//    private double getLongitudeFromString(String longitude) {
//        //"62.53556°W"
//        logger.debug("longitude", longitude);
//        double lon = Double.parseDouble(longitude.trim().replaceAll("\\D|\\.", ""));
//
//        if (longitude.substring(-1) == "E") {
//            lon = -lon;
//        } else if (longitude.substring(-1) == "W") {
//            // stays positive
//        }
//
//        return lon;
//    }

    /**
     * Gets latitude.
     *
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets latitude.
     *
     * @param latitude the latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets longitude.
     *
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets longitude.
     *
     * @param longitude the longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Double.compare(that.latitude, latitude) == 0 &&
                Double.compare(that.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
