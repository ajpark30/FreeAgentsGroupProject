package edu.matc.persistence;

import edu.matc.entity.Photo;
import edu.matc.entity.Waterfall;
import edu.matc.util.DatabaseUtility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * The Waterfall entity test.
 *
 * @author bsinner
 */
public class WaterfallTest {

    private WaterfallDao dao;
    private PhotoDao photoDao;

    /**
     * Reset database.
     */
    @BeforeAll
    public static void setUpAll() {
        DatabaseUtility dbUtil = new DatabaseUtility();
        dbUtil.runSQL("target/test-classes/sql/create_waterfalls_db.sql");
    }

    /**
     * Sets up each test.
     */
    @BeforeEach
    public void setUpEach() {
        dao = new WaterfallDao();
        DatabaseUtility dbUtil = new DatabaseUtility();
        dbUtil.runSQL("target/test-classes/sql/waterfallSetup.sql");
    }

    /**
     * Test get by property equal.
     */
    @Test
    public void testGetByPropertyEqual() {
        Waterfall mockWaterfall = getMockWaterfall();

        List<Waterfall> foundWaterfalls = dao.getByPropertyEqual("waterfall_id", "1");

        assertEquals(1, foundWaterfalls.size());
        assertEquals(mockWaterfall.toString(), foundWaterfalls.get(0).toString());
    }

    /**
     * Test get by id.
     */
    @Test
    public void testGetById() {
        Waterfall mockWaterfall = getMockWaterfall();

        Waterfall foundWaterfall = dao.getById(1);

        assertNotEquals(null, foundWaterfall);
        assertEquals(foundWaterfall.toString(), mockWaterfall.toString());
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
        Waterfall waterfall = dao.getById(1);
        waterfall.setLongitude(333.33);

        dao.saveOrUpdate(waterfall);

        Waterfall foundWaterfall = dao.getById(1);

        assertNotEquals(null, foundWaterfall);
        assertEquals(333.33, foundWaterfall.getLongitude());
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
        Waterfall waterfallToAdd = new Waterfall("Foo Falls", -10.002, 34.394);
        Photo p = new Photo();
        p.setWaterfall(waterfallToAdd);
        waterfallToAdd.setPhotos(new ArrayList<>(Arrays.asList(p)));

        int id = dao.insert(waterfallToAdd);
        Waterfall foundWaterfall = dao.getById(id);

        assertNotEquals(null, foundWaterfall);
        assertEquals(waterfallToAdd.toString(), foundWaterfall.toString());
        assertEquals(1, foundWaterfall.getPhotos().size());
    }

    /**
     * Test delete, test photo cascade delete.
     */
    @Test
    public void testDelete() {
        setUpDeleteTest();

        Waterfall waterfallToDelete = dao.getById(1);
        dao.delete(waterfallToDelete);

        Waterfall waterfallSearch = dao.getById(1);
        Photo photoSearch = photoDao.getById(2);

        assertEquals(null, waterfallSearch);
        assertEquals(null, photoSearch);
    }

    /**
     * Set up photoDao and photo table.
     */
    private void setUpDeleteTest() {
        DatabaseUtility dbUtil = new DatabaseUtility();
        dbUtil.runSQL("target/test-classes/sql/photoSetup.sql");
        photoDao = new PhotoDao();
    }

    /**
     * Get mock waterfall.
     *
     * @return the waterfall
     */
    private Waterfall getMockWaterfall() {
        Waterfall mockWaterfall = new Waterfall("Kalandula Falls", -9.07583, 16.0033);
        mockWaterfall.setWaterfallId(1);
        return mockWaterfall;
    }

}
