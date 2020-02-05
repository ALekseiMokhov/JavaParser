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

    Elements resumeEl = doc.select("div.resume-search-item__name");
    for (Element e: resumeEl){
     System.out.println(e.text());
     System.out.println("______________________");
    }
    Elements salaryEl = doc.select("div.vacancy-serp-item__compensation");
    for (Element e: salaryEl){
     System.out.println(e.text());
     System.out.println("______________________");

    }

/*    Elements reqEl = doc.select("a[href]");
    for (Element e: reqEl){
     System.out.println(e.text());
     System.out.println("______________________");
    }
    Elements addressEl = doc.select("div.vacancy-serp__vacancy-address");
    for (Element e: addressEl){
     System.out.println(e.text());
     System.out.println("______________________");
    }
    Elements urlEl = doc.select("div.vacancy-serp-item__link-overlay HH-LinkModifier");
    for (Element e: urlEl){
     System.out.println(e.text());
     System.out.println("______________________");
    }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
