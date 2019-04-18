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
        Waterfall mockWaterfall = new Waterfall();
        mockWaterfall.setWaterfallId(1);
        mockWaterfall.setName("Kalandula Falls");
        mockWaterfall.setLatitude(-9.07583f);
        mockWaterfall.setLongitude(16.0033f);

        List<Waterfall> foundWaterfalls = dao.getByPropertyEqual("waterfall_id", "1");

        assertEquals(1, foundWaterfalls.size());
        assertEquals(mockWaterfall.toString(), foundWaterfalls.get(0).toString());
    }

//   These tests will be used if the DAO
//   has methods for them
//    public void testGetByPropertyLike() {
//    }
//
//    public void testGetByProperties() {
//    }

    public void testUpdate() {
    }

    @Test
    public void testGetAll() {
        List<Waterfall> waterfalls = dao.getAll();

        assertEquals(4, waterfalls.size());
    }

    public void testInsert() {
    }

    public void testDelete() {
    }

}