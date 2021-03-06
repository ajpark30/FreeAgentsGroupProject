package edu.matc.util;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Class to run sql statements as part of set up or tear down in the unit tests.
 */
public class DatabaseUtility {

    private static final char DELIMITER = ';';
    private static final String PROPS_LOCATION = "/dbutil.properties";

    /**
     * Run the sql.
     *
     * @param sqlFile the sql file to be read and executed line by line
     */
    public void runSQL(String sqlFile) {
        Connection conn = null;
        Statement stmt = null;
        Properties props = loadProperties();

        try (BufferedReader br = new BufferedReader(new FileReader(sqlFile))) {

        Class.forName(props.getProperty("jdbc.driver"));

            conn = DriverManager.getConnection(props.getProperty("db.url"), props);
            stmt = conn.createStatement();
            String sql = "";

            while (br.ready()) {
                char readCharacter = (char) br.read();

                sql += readCharacter;

                if (readCharacter == DELIMITER) {
                    stmt.addBatch(sql);
                    sql = "";
                }

            }

            stmt.executeBatch();

        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
        }

    }

    /**
     * Loads properties for the database utility from props file.
     *
     * @return properties object with loaded properties
     */
    private Properties loadProperties() {
        Properties props = new Properties();

        try {
            props.load(this.getClass().getResourceAsStream(PROPS_LOCATION));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return props;
    }

}