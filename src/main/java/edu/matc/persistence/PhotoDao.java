package edu.matc.persistence;

import edu.matc.entity.Coordinates;
import edu.matc.entity.Waterfall;
import edu.matc.entity.Photo;

import edu.matc.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PhotoDao extends GenericDao<Photo> {

//  if implementing photo dimensions search example
//    private final int DEFAULT_MIN_HEIGHT = 100;
//    private final int DEFAULT_MIN_WIDTH = 120;

    private Database database = Database.getInstance();
    private final Logger logger = LogManager.getLogger(this.getClass());

    // access all methods on GenericDao through this class
    public PhotoDao() {
        super(Photo.class);
    }

    public List<Photo> findByDimensions(int minWidth, int maxWidth, int minHeight, int maxHeight){

        List<Photo> photos = new ArrayList<>();
        Statement stmt = null;
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try {

            Class.forName("com.mysql.jdbc.Driver");
            database.connect();
            stmt = database.getConnection().createStatement();

            String sql = "\n" +
                    "       SELECT *" + "\n" +
                    "       FROM photo" + "\n" +
                    "       WHERE" + "\n" +
                    "          width >= '" + minWidth + "' AND width <= '" + maxWidth + "'" + "\n" +
                    "          AND height >= '" + minHeight + "' AND height <= '" + maxHeight + "'";

            ResultSet results = stmt.executeQuery(sql);

            GenericDao<Photo> photoDao = new GenericDao(Photo.class);
            while (results.next()) {
                int row = results.getInt("photo_id");
                photos.add(photoDao.getById(row));

                logger.debug("Row: " + row
                        + "\nWidth: "
                        + results.getDouble("width")
                        + "\nHeight: "
                        + results.getDouble("height")
                );
            }
        } catch (SQLException se) {
            logger.error(se);
        } catch (Exception e) {
            logger.error(e);
        } finally {
            database.disconnect();
        }
        return photos;
    }
}