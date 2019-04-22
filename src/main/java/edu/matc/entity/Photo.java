package edu.matc.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.time.LocalDateTime;
import java.util.Objects;

import java.time.LocalDateTime;

/**
 * The type Photo.
 *
 * @author cwmoore
 * @author pwaite
 */
@Entity(name = "Photo")
@Table(name = "photo")
@XmlAccessorType( XmlAccessType.FIELD)
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name="photo_id")
    private int photo_id;

    @XmlTransient
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "waterfall_id",
            foreignKey = @ForeignKey(name = "waterfall_photo_cx")
    )
    private Waterfall waterfall;

    private LocalDateTime dateAcquired;
    private String sourceURL;
    private String linkedFromURL;
    private String attribution;
    private LocalDateTime dateTaken;
    private String title;
    private String caption;
    private String description;
    private String localPath;
    private int height;
    private int width;

    /**
     * Instantiates a new Photo.
     */
    public Photo() {
    }

    /**
     * Instantiates a new Photo.
     *
     * @param waterfall the waterfall
     * @param sourceURL    the source url
     * @param title        the title
     * @param localPath    the local path
     */
    public Photo(Waterfall waterfall, String sourceURL, String title, String localPath) {
        this.waterfall = waterfall;
        this.sourceURL = sourceURL;
        this.title = title;
        this.localPath = localPath;
    }

    /**
     * Instantiates a new Photo.
     *
     * @param photo_id           the photo_id
     * @param waterfall the waterfall
     * @param sourceURL    the source url
     * @param title        the title
     * @param localPath    the local path
     */
    public Photo(int photo_id, Waterfall waterfall, String sourceURL, String title, String localPath) {
        this.photo_id = photo_id;
        this.waterfall = waterfall;
        this.sourceURL = sourceURL;
        this.title = title;
        this.localPath = localPath;
    }

    /**
     * Gets photo_id.
     *
     * @return the photo_id
     */
    public int getPhotoId() {
        return photo_id;
    }

    /**
     * Sets photo_id.
     *
     * @param photo_id the photo_id
     */
    public void setPhotoId(int photo_id) {
        this.photo_id = photo_id;
    }

    /**
     * Gets waterfall.
     *
     * @return the waterfall
     */
    public Waterfall getWaterfall() {
        return waterfall;
    }

    /**
     * Sets waterfall
     *
     * @param waterfall the waterfall
     */
    public void setWaterfall(Waterfall waterfall) {
        this.waterfall = waterfall;
    }

    /**
     * Gets date acquired.
     *
     * @return the date acquired
     */
    public LocalDateTime getDateAcquired() {
        return dateAcquired;
    }

    /**
     * Sets date acquired.
     *
     * @param dateAcquired the date acquired
     */
    public void setDateAcquired(LocalDateTime dateAcquired) {
        this.dateAcquired = dateAcquired;
    }

    /**
     * Gets source url.
     *
     * @return the source url
     */
    public String getSourceURL() {
        return sourceURL;
    }

    /**
     * Sets source url.
     *
     * @param sourceURL the source url
     */
    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

    /**
     * Gets linked from url.
     *
     * @return the linked from url
     */
    public String getLinkedFromURL() {
        return linkedFromURL;
    }

    /**
     * Sets linked from url.
     *
     * @param linkedFromURL the linked from url
     */
    public void setLinkedFromURL(String linkedFromURL) {
        this.linkedFromURL = linkedFromURL;
    }

    /**
     * Gets attribution.
     *
     * @return the attribution
     */
    public String getAttribution() {
        return attribution;
    }

    /**
     * Sets attribution.
     *
     * @param attribution the attribution
     */
    public void setAttribution(String attribution) {
        this.attribution = attribution;
    }

    /**
     * Gets date taken.
     *
     * @return the date taken
     */
    public LocalDateTime getDateTaken() {
        return dateTaken;
    }

    /**
     * Sets date taken.
     *
     * @param dateTaken the date taken
     */
    public void setDateTaken(LocalDateTime dateTaken) {
        this.dateTaken = dateTaken;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets caption.
     *
     * @return the caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Sets caption.
     *
     * @param caption the caption
     */
    public void setCaption(String caption) {
        this.caption = caption;
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
     * Gets local path.
     *
     * @return the local path
     */
    public String getLocalPath() {
        return localPath;
    }

    /**
     * Sets local path.
     *
     * @param localPath the local path
     */
    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets width.
     *
     * @param width the width
     */
    public void setWidth(int width) {
        this.width = width;
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
        Photo photo = (Photo) object;
        return photo_id == photo.photo_id &&
                waterfall == photo.waterfall &&
                height == photo.height &&
                width == photo.width &&
                dateAcquired.equals(photo.dateAcquired) &&
                sourceURL.equals(photo.sourceURL) &&
                java.util.Objects.equals(linkedFromURL, photo.linkedFromURL) &&
                java.util.Objects.equals(attribution, photo.attribution) &&
                java.util.Objects.equals(dateTaken, photo.dateTaken) &&
                title.equals(photo.title) &&
                java.util.Objects.equals(caption, photo.caption) &&
                java.util.Objects.equals(description, photo.description) &&
                localPath.equals(photo.localPath);
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    public int hashCode() {
        return Objects.hash(super.hashCode(), photo_id, waterfall, dateAcquired, sourceURL, linkedFromURL, attribution, dateTaken, title, caption, description, localPath, height, width);
    }

    /**
     * To string java . lang . string.
     *
     * @return the java . lang . string
     */
    @Override
    public String toString() {
        return "Photo{" +
                "photo_id=" + photo_id +
                ", waterfall=" + waterfall +
                ", dateAcquired=" + dateAcquired +
                ", sourceURL='" + sourceURL + '\'' +
                ", linkedFromURL='" + linkedFromURL + '\'' +
                ", attribution='" + attribution + '\'' +
                ", dateTaken=" + dateTaken +
                ", title='" + title + '\'' +
                ", caption='" + caption + '\'' +
                ", description='" + description + '\'' +
                ", localPath='" + localPath + '\'' +
                ", height=" + height +
                ", width=" + width +
                '}';
    }
}