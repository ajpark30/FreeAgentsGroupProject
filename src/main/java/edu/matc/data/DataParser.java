package edu.matc.data;

import java.io.BufferedReader;
import java.io.*;
import java.io.IOException;
import java.util.*;

import com.sun.org.apache.xpath.internal.SourceTree;
import edu.matc.entity.Coordinates;
import edu.matc.entity.Waterfall;
import edu.matc.persistence.WaterfallDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;

import org.apache.logging.log4j.Logger;

public class DataParser {
    Logger logger = LogManager.getLogger(this.getClass());
    WaterfallDao waterfallDao = new WaterfallDao();

    public void parse() throws IOException {
        parseAndRead();
    }

    /**
     * Load start URL, read links, and retrieve linked page info
     * @throws IOException
     */
    public void parseAndRead() throws IOException {
        logger.debug("In DataParser.parse()");

        String startUrl = "https://en.wikipedia.org/wiki/List_of_waterfalls";
        List<Map<String, String>> linkMapList = createLinkMap(startUrl);

        for (Map<String, String> link : linkMapList) {
            Map<String, String> processedLink = processOneLink(link.get("url"), link.get("title"));
            handleProcessedLink(processedLink);
            System.out.println("processing..." + processedLink);
        }
    }

    /**
     * Using a Map of retrieved link data, create and store a Waterfall
     *
     * @param processedLink The Map returned from processOneLink(url, title)
     */
    private void handleProcessedLink(Map<String, String> processedLink) {

        logger.debug("In DataParser.handleProcessedLink()");
        logger.debug(new Date().toString()
                , processedLink.get("url")
                , processedLink.get("title")
                , processedLink.get("header")
                , processedLink.get("coords"));

        String stringCoords = processedLink.get("coords");
        String [] coordsArray;
        String spaceDelimiter = " ";
        coordsArray = stringCoords.split(spaceDelimiter);
        coordsArray[0].replaceAll( "[^0-9]", ""); //Not replacing degree and direction like expected.
        coordsArray[1].replaceAll( "[^0-9]", "");
        Double latitude = Double.parseDouble(coordsArray[0]);
        Double longitude = Double.parseDouble(coordsArray[1]);

        try {

            // create waterfall object and store it here
            Waterfall waterfall = new Waterfall(
                    processedLink.get("title")
                    , processedLink.get("url")
                    , new Coordinates(latitude, longitude)
            );
            System.out.println("Waterfall:");
            logger.debug(waterfall.toString());

            waterfallDao.saveOrUpdate(waterfall);
        } catch (Exception e) {
            logger.debug(e.toString());
        }
    }


    /**
     * Create list of link map data.
     * @return A list of Map <[url=,title=], value>
     * @throws IOException
     */
    private List<Map<String, String>> createLinkMap(String startUrl) throws IOException {
        logger.debug("In DataParser.createLinkMap()");
        List<Map<String, String>> linkMapList = new ArrayList<>();
        Map<String, String> linkMap;
        String urlPrefix = "https://en.wikipedia.org";

        Document docLinks = Jsoup.connect(startUrl).get();

        //Elements links = docLinks.select("a[href^=/wiki/]"); //not specific enough
        //Elements links = docLinks.select("a[href=/wiki/]"); // trying something else
        Elements links = docLinks.select("a[title*=falls]").not("a[href^=/wiki/List], a[href^=/wiki/Portal], a[href^=https], a[href^=/wiki/Category]");

        System.out.println(links);
        logger.debug(links);

        int maxResults = 100;
        for (Element l : links) {
            logger.debug("maxResults: " + maxResults);
            //if (!l.text().matches("all")) { continue; }

            Attributes attr = l.attributes();

            //if (attr.get("href").matches(".*png|jpg|jpeg|gif|bmp.*")) { logger.debug("bad link"); continue; }
            if (attr.get("title") != null) {
                logger.debug("Found a title");
                if (attr.get("title")
                        .equalsIgnoreCase(l.text()) // more specific:

                        && attr.get("href") // conformant wiki data will be identical
                                .replaceFirst("/wiki/", "") // in title, text and href (less prefix)
                                .replace('_', ' ') // (less underscores)
                            .equalsIgnoreCase(l.text())
                        ) {
                    linkMap = new HashMap<>();
                    linkMap.put("url", urlPrefix + attr.get("href"));
                    linkMap.put("title", attr.get("title"));
                    linkMapList.add(linkMap);
                }
            }

            if (maxResults-- <= 0) { break; }
        }
        return linkMapList;
    }

    /**
     * Process a URL: download, get values, return as map
     * @param url the URL to download
     * @param title the title of the page
     * @return a Map of retrieved data
     * @throws IOException
     */
    private Map<String, String> processOneLink(String url, String title) throws IOException {
        logger.debug("In DataParser.processOneLink()");
        Map<String, String> processedLink = new HashMap<>();


        Document doc = Jsoup.connect(url).get();

        String latLng = doc.body().getElementsByClass("geo-dec").text();
        String name = doc.body().getElementsByClass("firstHeading").text();
        /*
        String stringCoords = latLng;
        String [] coordsArray;
        String spaceDelimiter = " ";
        coordsArray = stringCoords.split(spaceDelimiter);
        System.out.println(coordsArray);
        Double latitude = Double.parseDouble(coordsArray[0]);
        Double longitude = Double.parseDouble(coordsArray[1]);
        */
//        logger.debug("latlng: " + latLng);
//        logger.debug("name: " + name);

        processedLink.put("url", url);
        processedLink.put("title", title);
        processedLink.put("heading", name);
//        processedLink.put("coords", latLng);
//        processedLink.put("latitude", Double.parseDouble(latLng.split("\\s")[0]);//latitude);
//        processedLink.put("longitude", Double.parseDouble(latLng.split("\\s")[1]); //longitude);

        return processedLink;
    }

/*
    private void createLinks() throws IOException {
        Document docLinks = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_waterfalls").get();
        Elements links = docLinks.select("a[title*=falls]").not("a[href^=/wiki/List], a[href^=/wiki/Portal], a[href^=https], a[href^=/wiki/Category]");
        System.out.println(links);
    }

    private void processLinks() throws IOException {
        Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Blue_Nile_Falls").get();
        String latLng = doc.body().getElementsByClass("geo-dec").text();
        String name = doc.body().getElementsByClass("firstHeading").text();

        System.out.println(latLng);
        System.out.println(name);

    }
*/
}