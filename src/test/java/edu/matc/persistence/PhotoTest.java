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

/**
 * The Photo entity test.
 */
public class PhotoTest {

    private PhotoDao dao;
    private WaterfallDao waterfallDao;

//    private GenericDao<Photo> dao;
//    private GenericDao<Waterfall> waterfallDao;

    /**
     * Reset database.
     */
    @BeforeAll
    @AfterAll
    public static void resetDatabase() {
        DatabaseUtility dbUtil = new DatabaseUtility();
        dbUtil.runSQL("target/test-classes/create_waterfalls_db.sql");
    }

    /**
     * Set up each test.
     */
    @BeforeEach
    public void setUp() {
        dao = new PhotoDao();//GenericDao<>(Photo.class);
        DatabaseUtility dbUtil = new DatabaseUtility();
        dbUtil.runSQL("target/test-classes/photoSetup.sql");
    }

    /**
     * Test get by property equal.
     */
    @Test
    public void testGetByPropertyEqual() {
        Photo mockPhoto = getMockPhoto();

        List<Photo> foundPhotos = dao.getByPropertyEqual("photo_id", "1");

        assertEquals(1, foundPhotos.size());
        assertEquals(mockPhoto.toString(), foundPhotos.get(0).toString());
    }

    /**
     * Test get by id.
     */
    @Test
    public void testGetById() {
        Photo mockPhoto = getMockPhoto();
        Photo photo = dao.getById(1);

        assertNotEquals(null, photo);
        assertEquals(mockPhoto.toString(), photo.toString());
    }

    /**
     * Test get by property like.
     */
    @Test
    public void testGetByPropertyLike() {
        List<Photo> photos = dao.getByPropertyLike("sourceURL", "wikipedia");
        assertEquals(2, photos.size());
    }

    /**
     * Test get all.
     */
    @Test
    public void testGetAll() {
        List<Photo> photos = dao.getAll();
        assertEquals(2, photos.size());
    }

    /**
     * Test update.
     */
    @Test
    public void testUpdate() {
        Photo photo = dao.getById(2);
        photo.setHeight(8000);

        dao.saveOrUpdate(photo);

        Photo updatedPhoto = dao.getById(2);

        assertNotEquals(null, updatedPhoto);
        assertEquals(8000, photo.getHeight());
    }

    /**
     * Test insert.
     */
    @Test
    public void testInsert() {
        setUpWaterfallDao();

        Photo photoToAdd = new Photo();
        photoToAdd.setWaterfall(waterfallDao.getById(2));
        photoToAdd.setSourceURL("https://en.wikipedia.org/foo.png");

        int id = dao.insert(photoToAdd);

        Photo foundPhoto = dao.getById(id);

        assertNotEquals(null, foundPhoto);
        assertEquals(photoToAdd.toString(), foundPhoto.toString());
    }

    /**
     * Test delete.
     */
    @Test
    public void testDelete() {
        Photo photoToDelete = dao.getById(2);
        dao.delete(photoToDelete);

        Photo photoSearch = dao.getById(2);

        assertEquals(null, photoSearch);
    }

    /**
     * Get mock photo.
     *
     * @return the photo
     */
    private Photo getMockPhoto() {
        setUpWaterfallDao();

        Photo mockPhoto = new Photo();
        mockPhoto.setPhotoId(1);
        mockPhoto.setWaterfall(waterfallDao.getById(3));
        mockPhoto.setSourceURL("https://upload.wikimedia.org/wikipedia/commons/3/3a/GrandfatherExposedPreCambrian.jpg");

        return mockPhoto;
    }

    /**
     * Set up the waterfall dao.
     */
    private void setUpWaterfallDao() {
        waterfallDao = new WaterfallDao();//new GenericDao<>(Waterfall.class);
    }

}