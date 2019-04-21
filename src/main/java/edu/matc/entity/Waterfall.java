package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * The type Waterfall.
 *
 * @author cwmoore
 * @author pwaite
 */
@XmlRootElement(name = "waterfall")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "Waterfall")
@Table(name = "waterfall")
public class Waterfall {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int waterfall_id;

    private String name;
    private String description;
	private String country;
    private String state;
    private String region;
	private String city;
	private double latitude;
	private double longitude;
	private String preserve;
    private String river;
    private String url;

    @OneToMany(mappedBy = "waterfall",
            cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Photo> photos = new ArrayList<>();

	//private Set<Photo> photos;

    /**
     * Instantiates a new Waterfall.
     */
    public Waterfall() {
    }

    /**
     * Instantiates a new Waterfall.
     *
     * @param name      the name
     * @param latitude  the latitude
     * @param longitude the longitude
     */
    public Waterfall(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Instantiates a new Waterfall.
     *
     * @param name      the name
     * @param url      the url
     * @param latitude  the latitude
     * @param longitude the longitude
     */
    public Waterfall(String name, String url, double latitude, double longitude) {
        this.name = name;
        this.url = url;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Instantiates a new Waterfall.
     *
     * @param name      the name
     * @param url      the url
     * @param coordinates  the coordinates
     */
    public Waterfall(String name, String url, Coordinates coordinates) {
        this.name = name;
        this.url = url;
        this.latitude = coordinates.getLatitude();
        this.longitude = coordinates.getLongitude();
    }

    /**
     * Instantiates a new Waterfall.
     *
     * @param waterfall_id the waterfall_id
     * @param name         the name
     * @param country      the country
     * @param state        the state
     * @param region       the region
     * @param city         the city
     * @param latitude     the latitude
     * @param longitude    the longitude
     * @param preserve     the preserve
     * @param river        the river
     */
    public Waterfall(int waterfall_id, String name, String country, String state, String region, String city, double latitude, double longitude, String preserve, String river) {
        this.waterfall_id = waterfall_id;
        this.name = name;
        this.country = country;
        this.state = state;
        this.region = region;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.preserve = preserve;
        this.river = river;
    }

    /**
     * Gets waterfall_id.
     *
     * @return the waterfall_id
     */
    public int getWaterfallId() {
        return waterfall_id;
    }

    /**
     * Sets waterfall_id.
     *
     * @param waterfall_id the waterfall_id
     */
    public void setWaterfallId(int waterfall_id) {
        this.waterfall_id = waterfall_id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets region.
     *
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets region.
     *
     * @param region the region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

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

    /**
     * Gets preserve.
     *
     * @return the preserve
     */
    public String getPreserve() {
        return preserve;
    }

    /**
     * Sets preserve.
     *
     * @param preserve the preserve
     */
    public void setPreserve(String preserve) {
        this.preserve = preserve;
    }

    /**
     * Gets river.
     *
     * @return the river
     */
    public String getRiver() {
        return river;
    }

    /**
     * Sets river.
     *
     * @param river the river
     */
    public void setRiver(String river) {
        this.river = river;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url.
     *
     * @param url the url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets photos.
     *
     * @return the photos
     */
    public List<Photo> getPhotos() {
        return photos;
    }

    /**
     * Sets photos.
     *
     * @param photos the photos
     */
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    /**
     * Equals boolean.
     *
     * @param object the object
     * @return the boolean
     */
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Waterfall waterfall = (Waterfall) object;
        return waterfall_id == waterfall.waterfall_id &&
                Double.compare(waterfall.latitude, latitude) == 0 &&
                Double.compare(waterfall.longitude, longitude) == 0 &&
                name.equals(waterfall.name) &&
                url.equals(waterfall.url) &&
                country.equals(waterfall.country) &&
                java.util.Objects.equals(state, waterfall.state) &&
                java.util.Objects.equals(region, waterfall.region) &&
                java.util.Objects.equals(city, waterfall.city) &&
                java.util.Objects.equals(preserve, waterfall.preserve) &&
                java.util.Objects.equals(river, waterfall.river);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    public int hashCode() {
        return Objects.hash(super.hashCode(), waterfall_id, name, country, state, region, city, latitude, longitude, preserve, river);
    }

    /**
     * To string java . lang . string.
     *
     * @return the java . lang . string
     */
    @Override
    public String toString() {
        return "Waterfall{" +
                "waterfall_id=" + waterfall_id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", preserve='" + preserve + '\'' +
                ", river='" + river + '\'' +
                '}';
    }
}