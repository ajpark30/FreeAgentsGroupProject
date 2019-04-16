package edu.matc.controller;

import edu.matc.entity.Coordinates;
import edu.matc.entity.Waterfall;
import edu.matc.entity.Photo;
import edu.matc.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import edu.matc.util.Database;

/**
 * This servlet routes the HTTP requests of a RESTful client app.
 *
 * @author cwmoore
 */

@WebServlet(
        name = "entityTest",
        urlPatterns = {"/entityTest"}
)

public class EntityTestServlet extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final GenericDao<Waterfall> waterfallDao = new GenericDao(Waterfall.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Waterfall> waterfalls = waterfallDao.getAll();
        PrintWriter out = resp.getWriter();
        /*
        for (Waterfall waterfall : waterfalls) {
            logger.debug(waterfall.toString());
            out.println(waterfall.toString());
            if (waterfall.getPhotos().size() > 0) {
                logger.debug(waterfall.getPhotos().get(0).toString());
                out.println(waterfall.getPhotos().get(0).toString());
            }
        }
*/
        Database database = Database.getInstance();
        waterfalls = database.findNearest(0.0, 0.0);
        for (Waterfall waterfall : waterfalls) {
            logger.debug(waterfall.toString());
            out.println(waterfall.toString());
        }

        waterfalls = database.findNearest(90.0, 90.0);
        for (Waterfall waterfall : waterfalls) {
            logger.debug(waterfall.toString());
            out.println(waterfall.toString());
        }

        waterfalls = database.findNearest(-90.0, -90.0);
        for (Waterfall waterfall : waterfalls) {
            logger.debug(waterfall.toString());
            out.println(waterfall.toString());
        }

        waterfalls = database.findNearest(45.0, -120.0);
        for (Waterfall waterfall : waterfalls) {
            logger.debug(waterfall.toString());
            out.println(waterfall.toString());
        }

        Coordinates coords = database.coordsFromZipcode("53735");
        logger.debug(coords.toString());
        out.println(coords.toString());

        waterfalls = database.findWaterfallsNear(coords);
        for (Waterfall waterfall : waterfalls) {
            logger.debug(waterfall.toString());
            out.println(waterfall.toString());
        }

        coords = database.coordsFromZipcode("90210");
        logger.debug(coords.toString());
        out.println(coords.toString());

        waterfalls = database.findWaterfallsNear(coords);
        for (Waterfall waterfall : waterfalls) {
            logger.debug(waterfall.toString());
            out.println(waterfall.toString());
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {}

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {}

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {}
}
