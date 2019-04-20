package edu.matc.data;

import java.io.BufferedReader;
import java.io.*;
import java.io.IOException;
import java.util.*;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;


public class DataParser {
    public void parse() throws IOException {
        //openFile();
        createLinks();
        processLinks();
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