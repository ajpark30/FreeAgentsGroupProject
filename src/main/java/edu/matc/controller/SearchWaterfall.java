package edu.matc.controller;

import edu.matc.entity.Coordinates;
import edu.matc.entity.Photo;
import edu.matc.entity.Waterfall;
import edu.matc.persistence.GenericDao;
import edu.matc.persistence.WaterfallDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import edu.matc.persistence.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * Add docs here
 * @author Andrew Park
 */

@WebServlet(
        name = "SearchWaterfallPage",
        urlPatterns = {"/searchWaterFall"}
)

public class SearchWaterfall extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WaterfallDao waterfallDao = new WaterfallDao();
        GenericDao<Photo> genericDaoPhoto = new GenericDao<>(Photo.class);
        //Photo photo = new Photo();

        if (req.getParameter("submit").equals("searchByZip")) {
            Coordinates coordinates = waterfallDao.coordsFromZipcode(req.getParameter("searchZip"));
            List<Waterfall> waterfall = waterfallDao.findWaterfallsNear(coordinates);
            req.setAttribute("waterFallInfo", waterfall);
            //req.setAttribute("photo", genericDaoPhoto.getById(waterfall.get(0).getWaterfallId()));
            logger.info("***Photo information for UX: " + genericDaoPhoto.getAll());
            logger.info("^^^Waterfall get all: " + waterfall.get(0).getWaterfallId());
            logger.info("$$$Photo by ID: " + genericDaoPhoto.getById(waterfall.get(0).getWaterfallId()));
        }
        if (req.getParameter("submit").equals("searchByName")) {
            List<Waterfall> waterfall = waterfallDao.getByPropertyEqual("name", req.getParameter("searchName"));
            req.setAttribute("waterFallInfo", waterfall);
            logger.info("***Photo information for UX: " + genericDaoPhoto.getById(1));
        }
        if (req.getParameter("submit").equals("searchByLatLong")) {
            String latitude = req.getParameter("searchLatitude");
            String longitude = req.getParameter("searchLongitude");
            List<Waterfall> waterfall = waterfallDao.findNearest(Double.parseDouble(latitude), Double.parseDouble(longitude));
            req.setAttribute("waterFallInfo", waterfall);
            logger.info("***Photo information for UX: " + genericDaoPhoto.getById(3));
            logger.info("^^^Waterfall get all: " + waterfall.get(0).getName());
        }
        if (req.getParameter("submit").equals("searchAll")) {
            List<Waterfall> waterfall = waterfallDao.getAll();
            req.setAttribute("waterFallInfo", waterfall);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/waterfallResults.jsp");
        dispatcher.forward(req, resp);

    }
}
