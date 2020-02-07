import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


class WEBParser {
    private static String URL;
    private static String USER_AGENT;
    final Properties properties = new Properties();
    InputStream inputStream = null;

    {
        try {
            inputStream = new FileInputStream("src/main/resources/properties.config");
            properties.load(inputStream);
            URL = properties.getProperty("PAGE1");
        } catch (FileNotFoundException e) {
            System.out.println("Can't find config file!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Can't find correct URL in properties!");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void work() {
        try {

            Document doc = Jsoup.connect(URL)
                    .userAgent("Chrome/4.0.249.0")
                    .get();
            doc.outputSettings().outline(true);

            Elements link = doc.select("a[href*=/vacancy/][href$=%20junior]");

            for (Element e : link) {
                String linkText = e.attr("abs:href");
                System.out.println(linkText);
                deepWork(linkText);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deepWork(String url) {
        try {
            Document document = Jsoup.connect(url).
                    userAgent("Chrome/4.0.249.0 ")
                    .referrer("http://www.google.com")
                    .get();
            document.outputSettings().outline(true);

            Elements eSalary = document.select("p[class='vacancy-salary']");
            for (Element e : eSalary) {
                String string = e.text();
                System.out.println(string);
            }
            Elements eCity = document.select("p[data-qa='vacancy-view-location']");
            for (Element e : eCity) {
                String string = HTML_Filter.filterLocation(e.text());
                System.out.println(string);
            }
            Elements eExp = document.select("[data-qa='vacancy-experience']");
            for (Element e : eExp) {
                String string = e.text();
                System.out.println(string);
            }
            Elements eDescription = document.select("div[data-qa='vacancy-description']");
            for (Element e : eDescription) {
                String string = HTML_Filter.filterRequirements(e.text());
                System.out.println(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}