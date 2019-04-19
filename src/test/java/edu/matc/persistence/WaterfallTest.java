package edu.matc.persistence;

import edu.matc.entity.Photo;
import edu.matc.entity.Waterfall;
import edu.matc.util.DatabaseUtility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The Waterfall entity test.
 *
 * @author bsinner
 */
public class WaterfallTest {

    private GenericDao<Waterfall> dao;
    private GenericDao<Photo> photoDao;

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
     * Sets up each test.
     */
    @BeforeEach
    public void setUpEach() {
        dao = new GenericDao<>(Waterfall.class);
        DatabaseUtility dbUtil = new DatabaseUtility();
        dbUtil.runSQL("target/test-classes/waterfallSetup.sql");
    }

    /**
     * Test get by property equal.
     */
    @Test
    public void testGetByPropertyEqual() {
        Waterfall mockWaterfall = new Waterfall("Kalandula Falls", -9.07583f, 16.0033f);
        mockWaterfall.setWaterfallId(1);

        List<Waterfall> foundWaterfalls = dao.getByPropertyEqual("waterfall_id", "1");

        assertEquals(1, foundWaterfalls.size());
        assertEquals(mockWaterfall.toString(), foundWaterfalls.get(0).toString());
    }

    /**
     * Test get by property like.
     */
    @Test
    public void testGetByPropertyLike() {
        List<Waterfall> foundWaterfalls = dao.getByPropertyLike("name", "an");
        assertEquals(2, foundWaterfalls.size());
    }


    /**
     * Test update.
     */
    @Test
    public void testUpdate() {
        Waterfall waterfall = dao.getByPropertyEqual("waterfall_id", "1").get(0);
        waterfall.setLongitude(333.33f);

        dao.saveOrUpdate(waterfall);

        List<Waterfall> foundWaterfalls = dao.getByPropertyEqual("waterfall_id", "1");

        assertEquals(1, foundWaterfalls.size());
        assertEquals(333.33f, foundWaterfalls.get(0).getLongitude());
    }

    /**
     * Test get all.
     */
    @Test
    public void testGetAll() {
        List<Waterfall> waterfalls = dao.getAll();
        assertEquals(4, waterfalls.size());
    }

    /**
     * Test insert with a photo.
     */
    @Test
    public void testInsert() {
        Waterfall waterfallToAdd = new Waterfall("Foo Falls", -10.002f, 34.394f);
        Photo p = new Photo();
        p.setWaterfall(waterfallToAdd);
        waterfallToAdd.setPhotos(new ArrayList<>(Arrays.asList(p)));

        int id = dao.insert(waterfallToAdd);
        List<Waterfall> foundWaterfalls = dao.getByPropertyEqual("waterfall_id", String.valueOf(id));

        assertEquals(1, foundWaterfalls.size());
        assertEquals(waterfallToAdd.toString(), foundWaterfalls.get(0).toString());
        assertEquals(1, foundWaterfalls.get(0).getPhotos().size());
    }

    /**
     * Test delete, test photo cascade delete.
     */
    @Test
    public void testDelete() {
        setUpDeleteTest();

        Waterfall waterfallToDelete = dao.getByPropertyEqual("waterfall_id", "1").get(0);
        dao.delete(waterfallToDelete);

        List<Waterfall> waterfallSearch = dao.getByPropertyEqual("waterfall_id", "1");
        List<Photo> photoSearch = photoDao.getByPropertyEqual("photo_id", "2");

        assertEquals(0, waterfallSearch.size());
        assertEquals(0, photoSearch.size());
    }

    /**
     * Set up photoDao and photo table.
     */
    private void setUpDeleteTest() {
        DatabaseUtility dbUtil = new DatabaseUtility();
        dbUtil.runSQL("target/test-classes/photoSetup.sql");
        photoDao = new GenericDao<>(Photo.class);
    }

}