package com.gmail.alekseimokhov.javaparser.main;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTML_Filter {

     enum CITIES {MOSCOW("Москва"),
            ZELENOGRAD("Зеленоград"),DUBNA("Дубна"),SPB("Санкт-Петербург"),
            KHIMKI("Химки"),VORONEZH("Воронеж"),TULA("Тула"),EKB("Екатеринбург"),
            ORYOL("Орел"),BRYANSK("Брянск"),BELGOROD("Белгород"),MINSK("Минск"),
            KURSK("Курск"),FAR_LOCATION("requires far location!");

        CITIES(String cityName) {
        }

     };
    enum SKILLS {
           HIBERNATE("hibernate"),SPRING("spring"),JAVA8("java 8"),EJB("EJB"),GENERICS("генерики"),
            SQL("sql"), NOSQL("nosql"),AJAX("ajax"),CSS("css"),HTML("html"),
            JUNIT("junit"),LOG4J("log4j"),ORACLE("oracle"),POSTGRES("postgres"),
            MONGODB("mongoDB"),CASSANDRA("cassandra"),CORE("core"),UI("ui");
           SKILLS(String skillName){

           }
    }

    static String filterLocation(String parsingString) {
        CITIES [] arr = CITIES.values();
        for (CITIES cities : arr) {
            if(cities.valueOf(parsingString)!=null)
                System.out.println(parsingString + "TEST");
                return parsingString;
        }
     return CITIES.FAR_LOCATION.toString();
    }
    static String filterExpirience(String parsingString) {
        if(parsingString.matches(".*\\d.*")){
            return parsingString.replaceAll("[^0-9]", "").substring(0,1);
        }
        return "0";
    }
    static String filterSalary(String parsingString){
        if(parsingString.matches(".*\\d.*")){
            Matcher matcher = Pattern.compile("\\d+").matcher(parsingString);
            matcher.find();
            String res = matcher.group();
            return res;
        }

        return"0";
    }

    static String filterRequirements(String parsingString){
        StringBuilder stringBuilder = new StringBuilder();
        SKILLS[] arr = SKILLS.values();
        for (SKILLS skill:arr ){
            String string =skill.toString();
         Pattern pattern = Pattern.compile(string,Pattern.CASE_INSENSITIVE);
         Matcher matcher = pattern.matcher(parsingString);
         while (matcher.find()){
             stringBuilder.append(matcher.group()).append(",");
             break;
            }
         }
        if(stringBuilder.lastIndexOf(",") >0 ){
            stringBuilder.deleteCharAt( stringBuilder.lastIndexOf(","));
        }
        return stringBuilder.toString();
     }


}