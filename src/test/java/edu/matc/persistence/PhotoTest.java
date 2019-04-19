package edu.matc.persistence;

import edu.matc.entity.Photo;
import edu.matc.entity.Waterfall;
import edu.matc.util.DatabaseUtility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PhotoTest {

    private GenericDao<Photo> dao;

    @BeforeAll
    @AfterAll
    public static void resetDatabase() {
        DatabaseUtility dbUtil = new DatabaseUtility();
        dbUtil.runSQL("target/test-classes/create_waterfalls_db.sql");
    }

    @BeforeEach
    public void setUp() {
        dao = new GenericDao<>(Photo.class);
        DatabaseUtility dbUtil = new DatabaseUtility();
        dbUtil.runSQL("target/test-classes/photoSetup.sql");
    }

    @Test
    public void testGetByPropertyEqual() {
        Photo mockPhoto = getMockPhoto();

        List<Photo> foundPhotos = dao.getByPropertyEqual("photo_id", "1");

        assertEquals(1, foundPhotos.size());
        assertEquals(mockPhoto.toString(), foundPhotos.get(0).toString());
    }

    @Test
    public void testGetById() {
        Photo mockPhoto = getMockPhoto();
        Photo photo = dao.getById(1);

        assertNotEquals(null, photo);
        assertEquals(mockPhoto.toString(), photo.toString());
    }

    @Test
    public void testGetByPropertyLike() {
        List<Photo> photos = dao.getByPropertyLike("sourceURL", "wikipedia");
        assertEquals(2, photos.size());
    }

    @Test
    public void testGetAll() {
        List<Photo> photos = dao.getAll();
        assertEquals(2, photos.size());
    }

    @Test
    public void testUpdate() {
        Photo photo = dao.getById(2);
        photo.setHeight(8000);

        dao.saveOrUpdate(photo);

        Photo updatedPhoto = dao.getById(2);

        assertNotEquals(null, updatedPhoto);
        assertEquals(8000, photo.getHeight());
    }

    public void testInsert() {
    }

    public void testDelete() {
    }

    private Photo getMockPhoto() {
        GenericDao<Waterfall> waterfallDao = new GenericDao<>(Waterfall.class);

        Photo mockPhoto = new Photo();
        mockPhoto.setPhotoId(1);
        mockPhoto.setWaterfall(waterfallDao.getById(3));
        mockPhoto.setSourceURL("https://upload.wikimedia.org/wikipedia/commons/3/3a/GrandfatherExposedPreCambrian.jpg");

        return mockPhoto;
    }

}