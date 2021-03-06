package edu.matc.persistence;

import edu.matc.entity.Coordinates;
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
    private GenericDao<Coordinates> coordsDao;

    /**
     * Reset database.
     */
    @BeforeAll
    public static void setUpAll() {
        DatabaseUtility dbUtil = new DatabaseUtility();
        dbUtil.runSQL("target/test-classes/sql/create_waterfalls_db.sql");
        dbUtil.runSQL("target/test-classes/sql/coordinatesSetup.sql");
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
        assertEquals(6, waterfalls.size());
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
     * Test findNearest.
     */
    @Test
    public void testFindNearest() {
        Waterfall source = getSourceWaterfall();
        Waterfall dest = getDestWaterfall();

        List<Waterfall> waterfalls = dao.findNearest(
            source.getLatitude()
            , source.getLongitude()
            , 10
            , 6600
        );

        assertEquals(2, waterfalls.size());
        assertEquals(dest.getLongitude(), waterfalls.get(1).getLongitude());
        assertEquals(dest.getLatitude(), waterfalls.get(1).getLatitude());

    }

    /**
     * Test find nearest with no within parameter.
     */
    @Test
    public void testFindNearestNoWithin() {
        Waterfall source = getSourceWaterfall();

        List<Waterfall> waterfalls = dao.findNearest(
                source.getLatitude(), source.getLongitude(), 4
        );

        assertEquals(4, waterfalls.size());
    }

    /**
     * Test find nearest with only lat and long.
     */
    @Test
    public void testFindNearestNoLatLong() {
        Waterfall source = getSourceWaterfall();

        List<Waterfall> waterfalls = dao.findNearest(
            source.getLatitude(), source.getLongitude()
        );

        assertEquals(6, waterfalls.size());
    }

    /**
     * Test find nearest with Coordinates object as parameter.
     */
    @Test
    public void testFindNearestWithCoords() {
        Coordinates cords = getSourceWaterfallCoords();
        List<Waterfall> waterfalls = dao.findWaterfallsNear(cords);

        assertEquals(6, waterfalls.size());
    }

    /**
     * Test find nearest with Coordinates object and result count.
     */
    @Test
    public void testFindNearestWithCoordsResultCount() {
        Coordinates cords = getSourceWaterfallCoords();
        List<Waterfall> waterfalls = dao.findWaterfallsNear(cords, 3);

        assertEquals(3, waterfalls.size());
    }

    /**
     * Test find nearest with Coordinates object, result count,
     * and within.
     */
    @Test
    public void testFindNearestWithCoordsAllParams() {
        Coordinates coords = getSourceWaterfallCoords();

        List<Waterfall> waterfalls = dao.findWaterfallsNear(
                coords, 4, 7000
        );

        assertEquals(4, waterfalls.size());
    }

    /**
     * Test find Coordinates from zip code string.
     */
    @Test
    public void testFindCoordsFromZipcode() {

        String zip = "612";

        Coordinates coords = dao.coordsFromZipcode(zip);

        assertEquals(18.402253, coords.getLatitude());
        assertEquals(-66.711397, coords.getLongitude());
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

    /**
     * Gets waterfall with lat -9.07583 and long 16.0033.
     *
     * @return waterfall id #1
     */
    private Waterfall getSourceWaterfall() { return dao.getById(1); }

    /**
     * Gets waterfall nearest to waterfall id #1.
     *
     * @return waterfall id #2
     */
     private Waterfall getDestWaterfall() { return dao.getById(2); }

    /**
     * Get Coordinates object with waterfall id #1's coordinates.
     *
     * @return the coordinates
     */
    private Coordinates getSourceWaterfallCoords() {
        Waterfall source = getSourceWaterfall();

        return new Coordinates(source.getLatitude(), source.getLongitude());
     }
}
