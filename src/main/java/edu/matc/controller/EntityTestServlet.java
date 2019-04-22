package edu.matc.controller;

import edu.matc.data.DataParser;
import edu.matc.entity.Coordinates;
import edu.matc.entity.Waterfall;
import edu.matc.entity.Photo;
import edu.matc.persistence.GenericDao;
import edu.matc.persistence.WaterfallDao;
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
 * This servlet tests parts of our application with browser output.
 *
 * @author cwmoore
 */

@WebServlet(
        name = "entityTest",
        urlPatterns = {"/entityTest"}
)

public class EntityTestServlet extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final WaterfallDao waterfallDao = new WaterfallDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Waterfall> waterfalls;// = waterfallDao.getAll();
        PrintWriter out = resp.getWriter();
        /*
        for (Waterfall waterfall : waterfalls) {
            //logger.info(waterfall.toString());
            out.println(waterfall.toString());
            if (waterfall.getPhotos().size() > 0) {
                //logger.info(waterfall.getPhotos().get(0).toString());
                out.println(waterfall.getPhotos().get(0).toString());
            }
        }
*/
        DataParser dataParser = new DataParser();
        dataParser.parseAndRead();

        //logger.info("\nWaterfalls closest to 0,0");
        out.println("\nWaterfalls closest to 0,0");
        waterfalls = waterfallDao.findNearest(0.0, 0.0);
        for (Waterfall waterfall : waterfalls) {
            //logger.info(waterfall.toString());
            out.println(waterfall.toString());
        }
        //logger.info("\nWaterfalls closest to 0,0");
        out.println("\nWaterfalls closest to 0,0");
        waterfalls = waterfallDao.findNearest(0.0, 0.0, 100, 1000);
        for (Waterfall waterfall : waterfalls) {
            //logger.info(waterfall.toString());
            out.println(waterfall.toString());
        }

        //logger.info("\nWaterfalls closest to 90,90");
        out.println("\nWaterfalls closest to 90,90");
        waterfalls = waterfallDao.findNearest(90.0, 90.0);
        for (Waterfall waterfall : waterfalls) {
            //logger.info(waterfall.toString());
            out.println(waterfall.toString());
        }

        //logger.info("\nWaterfalls closest to -90,-90");
        out.println("\nWaterfalls closest to -90,-90");
        waterfalls = waterfallDao.findNearest(-90.0, -90.0);
        for (Waterfall waterfall : waterfalls) {
            //logger.info(waterfall.toString());
            out.println(waterfall.toString());
        }

        //logger.info("\nWaterfalls closest to 45,-120");
        out.println("\nWaterfalls closest to 45,-120");
        waterfalls = waterfallDao.findNearest(45.0, -120.0);
        for (Waterfall waterfall : waterfalls) {
            //logger.info(waterfall.toString());
            out.println(waterfall.toString());
        }

        //logger.info("\nWaterfalls closest to 8.7,55.5");
        out.println("\nWaterfalls closest to 8.7,55.5");
        waterfalls = waterfallDao.findNearest(8.7, 55.5);
        for (Waterfall waterfall : waterfalls) {
            //logger.info(waterfall.toString());
            out.println(waterfall.toString());
        }

        //logger.info("\nWaterfalls closest to 23.3,85.6");
        out.println("\nWaterfalls closest to 23.3,85.6");
        waterfalls = waterfallDao.findNearest(23.3, 85.6);
        for (Waterfall waterfall : waterfalls) {
            //logger.info(waterfall.toString());
            out.println(waterfall.toString());
        }

        //logger.info("\nWaterfalls closest to 53735");
        out.println("\nWaterfalls closest to 53735");
        Coordinates coords = waterfallDao.coordsFromZipcode("53735");
        //logger.info(coords.toString());
        out.println(coords.toString());

        waterfalls = waterfallDao.findWaterfallsNear(coords);
        for (Waterfall waterfall : waterfalls) {
            //logger.info(waterfall.toString());
            out.println(waterfall.toString());
        }

        //logger.info("\nWaterfalls closest to 90210");
        out.println("\nWaterfalls closest to 90210");
        coords = waterfallDao.coordsFromZipcode("90210");
        //logger.info(coords.toString());
        out.println(coords.toString());

        waterfalls = waterfallDao.findWaterfallsNear(coords);
        for (Waterfall waterfall : waterfalls) {
            //logger.info(waterfall.toString());
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
