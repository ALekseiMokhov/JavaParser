package com.gmail.alekseimokhov.javaparser.buiseness_logic;
import com.gmail.alekseimokhov.javaparser.entity.Skill;
import com.gmail.alekseimokhov.javaparser.entity.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Parser {
    private static String URL;
    private static final Set<Vacancy> VACANCY_SET = new HashSet<>();
    final Properties properties = new Properties();
     InputStream inputStream;

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

    public static Set<Vacancy> getVacancySet() {
        return VACANCY_SET;
    }

    public void parseURL() {
        try {

            Document doc = Jsoup.connect(URL)
                    .userAgent("Chrome/4.0.249.0")
                    .get();
            doc.outputSettings().outline(true);

            Elements link = doc.select("a[href*=/vacancy/][href$=%20junior]");

            for (Element e : link) {
                String linkText = e.attr("abs:href");
                parseVacancy(linkText);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseVacancy(String url) {
        Vacancy vacancy=new Vacancy();
        vacancy.setURL(url);
        try {
            Document document = Jsoup.connect(url).
                    userAgent("Chrome/4.0.249.0 ")
                    .referrer("http://www.google.com")
                    .get();
            document.outputSettings().outline(true);

            Elements eCompany = document.select("a[data-qa='vacancy-company-name']");
            for (Element e : eCompany) {
                String string = e.text();
                vacancy.setCompany(string);

            }

            Elements eCity = document.select("p[data-qa='vacancy-view-location']");
            for (Element e : eCity) {
                String string = HTML_Filter.filterLocation(e.text());
                vacancy.setCity(string);
            }
            Elements eExp = document.select("[data-qa='vacancy-experience']");
            for (Element e : eExp) {
                String string = HTML_Filter.filterExperience(e.text());
                vacancy.setExperience(Integer.valueOf(string));
            }

            Elements eSalary = document.select("p[class='vacancy-salary']");
            for (Element e : eSalary) {
                String string = HTML_Filter.filterSalary(e.text());
                vacancy.setSalary(Integer.parseInt(string));

            }
            Elements eDescription = document.select("div[data-qa='vacancy-description']");
            for (Element e : eDescription) {
                String string = HTML_Filter.filterRequirements(e.text());
                List<Skill> listOfSkills =
                        Stream.of(string.split(",")).
                                map(s-> new Skill(s)).
                                collect(Collectors.toList());
                vacancy.setSKILLS_REQUIRED(listOfSkills);

            }
            VACANCY_SET.add(vacancy);
            System.out.println(vacancy);
        } catch (IOException e){

        }

    }
}