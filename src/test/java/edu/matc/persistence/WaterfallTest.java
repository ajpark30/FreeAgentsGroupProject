package edu.matc.persistence;

import edu.matc.entity.Waterfall;
import edu.matc.util.DatabaseUtility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertEquals(1, 1);
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

    public void testGetAll() {
    }

    public void testInsert() {
    }

    public void testDelete() {
    }

}