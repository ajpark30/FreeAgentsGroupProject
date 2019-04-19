package edu.matc.data;

import java.io.BufferedReader;
import java.io.*;
import java.io.IOException;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;


public class DataParser {

    private void openFile() {
        try (BufferedReader links = new BufferedReader(new FileReader("links.txt"))) {
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


        while (input.ready()) {
            inputLine = input.readLine();
            urlArray = inputLine.split("\\W");
            for (String urls : urlArray) {
                if (!urls.isEmpty() && urls.matches(urlReg)) {
                    processLinks(urls);
                }
            }
        }
    }

    private void processLinks(String urls) throws IOException {
        Document doc = Jsoup.connect(urls).get();
        String latLng = doc.body().getElementsByClass("geo-dec").toString();


    }

    public static void main(String args[]) throws Exception {
        /*
        Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_waterfalls").get();
        String DataToParse = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<title>Waterfall Sample</title>"
                + "</head>"
                + "<body>"
                + "<table><tr><td><h1>Bridal Falls</h1></tr>"
                + "</table>"
                + "<p>Lat Lng</p>"
                + "</body>"
                + "</html>";

        Document html = Jsoup.parse(DataToParse);
        String title = html.title;
        String name = html.body().getElementsByTag("h1").text();
        String location = html.body().getElementsByTag("p").text();
        Elements waterfallLinks = doc.select("a[href^=`/wiki`]");
        */
    }
}