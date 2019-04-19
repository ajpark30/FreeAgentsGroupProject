package edu.matc.persistence;

import edu.matc.entity.Waterfall;
import edu.matc.util.DatabaseUtility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WaterfallTest {

    private GenericDao<Waterfall> dao;

    @BeforeEach
    public void setUp() {
        dao = new GenericDao<>(Waterfall.class);
        DatabaseUtility dbUtil = new DatabaseUtility();

        dbUtil.runSQL("target/test-classes/waterfallSetup.sql");
    }

    @AfterAll
    public static void tearDown() {
        DatabaseUtility dbUtil = new DatabaseUtility();
        dbUtil.runSQL("target/test-classes/create_waterfalls_db.sql");
    }

    @Test
    public void testGetByPropertyEqual() {
        Waterfall mockWaterfall = new Waterfall("Kalandula Falls", -9.07583f, 16.0033f);
        mockWaterfall.setWaterfallId(1);

        List<Waterfall> foundWaterfalls = dao.getByPropertyEqual("waterfall_id", "1");

        assertEquals(1, foundWaterfalls.size());
        assertEquals(mockWaterfall.toString(), foundWaterfalls.get(0).toString());
    }


    public void testGetByPropertyLike() {
    }

    public void testGetByProperties() {
    }

    @Test
    public void testUpdate() {
        Waterfall waterfall = dao.getByPropertyEqual("waterfall_id", "1").get(0);
        waterfall.setLongitude(333.33f);

        dao.saveOrUpdate(waterfall);

        List<Waterfall> foundWaterfalls = dao.getByPropertyEqual("waterfall_id", "1");

        assertEquals(1, foundWaterfalls.size());
        assertEquals(333.33f, foundWaterfalls.get(0).getLongitude());
    }

    @Test
    public void testGetAll() {
        List<Waterfall> waterfalls = dao.getAll();
        assertEquals(4, waterfalls.size());
    }

    @Test
    public void testInsert() {
        Waterfall waterfallToAdd = new Waterfall("Foo Falls", -10.002f, 34.394f);

        int id = dao.insert(waterfallToAdd);

        List<Waterfall> foundWaterfalls = dao.getByPropertyEqual("waterfall_id", String.valueOf(id));

        assertEquals(1, foundWaterfalls.size());
        assertEquals(waterfallToAdd.toString(), foundWaterfalls.get(0).toString());
    }

    public void testDelete() {
    }

}