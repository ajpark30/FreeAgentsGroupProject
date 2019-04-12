package edu.matc.controller;

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

/**
 * This servlet routes the HTTP requests of a RESTful client app.
 *
 * @author cwmoore
 */

@WebServlet(
        name = "waterfallEndpoints",
        urlPatterns = {"/waterfalls"}
)

public class WaterfallEndpoints extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // check credentials
        // access request JSON
        // extract search fields
        // verify complete, or send error message
        // do database query
        // create JSON response
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // check credentials
        // access request JSON
        // extract new waterfall fields
        // verify complete, or send error message
        // do database insert/update
        // create JSON success response
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // check credentials
        // access request JSON
        // extract new waterfall fields
        // verify id exists, or send error message
        // do database insert/update
        // create JSON success response
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // check credentials
        // access request JSON
        // verify id exists, or send error message
        // do database delete
        // create JSON success response
    }
}
