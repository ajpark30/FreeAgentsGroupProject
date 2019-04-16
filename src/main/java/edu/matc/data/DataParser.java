import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;

public class DataParser {
    public static void main(String args[]) throws Exception {

        Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_waterfalls").get();
        /*
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
        */
        Elements waterfallLinks = doc.select("a[href^=`/wiki`]");
    }
}