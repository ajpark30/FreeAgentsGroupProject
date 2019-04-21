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

        //openFile();
        createLinks();
        processLinks();
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
        }
    }

    private void handleProcessedLink(Map<String, String> processedLink) {

        logger.debug("In DataParser.handleProcessedLink()");
        logger.debug(new Date().toString(), processedLink.get("url")
                , processedLink.get("title")
                , processedLink.get("header")
                , processedLink.get("coords"));

        try {
            // create waterfall object and store it here
            Waterfall waterfall = new Waterfall(
                    processedLink.get("title")
                    , processedLink.get("url")
                    , new Coordinates(processedLink.get("coords"))
            );

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
        Elements links = docLinks.select("a[href^=/wiki/]"); //not specific enough
        //Elements links = docLinks.select("a[href=/wiki/]"); // trying something else
        System.out.println(links);
        logger.debug(links);

        int maxResults = 10;
        for (Element l : links) {
            logger.debug("maxResults: " + maxResults);
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

//        logger.debug("latlng: " + latLng);
//        logger.debug("name: " + name);

        processedLink.put("url", url);
        processedLink.put("title", title);
        processedLink.put("heading", name);
        processedLink.put("coords", latLng);

        return processedLink;
    }


    private void createLinks() throws IOException {
        Document docLinks = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_waterfalls").get();
        Elements links = docLinks.select("a[href^=/wiki/]"); //not specific enough
        System.out.println(links);
    }

    /*
        private void openFile() {
            try (BufferedReader links = new BufferedReader(new FileReader("./links.txt"))) {
                readLinks(links);
            } catch (FileNotFoundException fileNotFound) {
                fileNotFound.printStackTrace();
            } catch (IOException inputOutputException) {
                inputOutputException.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        private void readLinks(BufferedReader input) throws IOException {
            String inputLine = null;
            String[] urlArray = null;
            String urlReg = "\\\\b(https?|ftp|file)://[-A-Z0-9+&@#/%?=~_|!:,.;]*[-A-Z0-9+&@#/%=~_|]";
            urlReg = ".*";

            while (input.ready()) {
                inputLine = input.readLine();
                urlArray = inputLine.split("\\W");
                for (String urls : urlArray) {
                    if (!urls.isEmpty() && urls.matches(urlReg)) {
                        processLinks();
                    }
                }
            }
        }
    */
    private void processLinks() throws IOException {
        Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Blue_Nile_Falls").get();
        String latLng = doc.body().getElementsByClass("geo-dec").text();
        String name = doc.body().getElementsByClass("firstHeading").text();

        System.out.println(latLng);
        System.out.println(name);

    }

}