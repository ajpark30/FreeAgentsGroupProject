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

    private static final String PROPS_PATH = "dbutil.properties";
    private static final char DELIMITER = ';';
    private static final String DRIVER= "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/waterfall";


    /**
     * Run the sql.
     *
     * @param sqlFile the sql file to be read and executed line by line
     */
    public void runSQL(String sqlFile) {
        Connection conn = null;
        Statement stmt = null;

//        Properties props = loadProperties();

        try (BufferedReader br = new BufferedReader(new FileReader(sqlFile))) {

            Class.forName(DRIVER);

            conn = DriverManager.getConnection(URL);
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

        } catch (SQLException se) {

        } catch (IOException ioe) {

        } catch (ClassNotFoundException cnf) {

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
            props.load(this.getClass().getResourceAsStream(PROPS_PATH));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return props;
    }

}