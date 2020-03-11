package com.gmail.alekseimokhov.javaparser.buiseness_logic;


import com.gmail.alekseimokhov.javaparser.entity.Skill;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTML_Filter {

     enum CITIES {MOSCOW("Москва"),
            ZELENOGRAD("Зеленоград"),DUBNA("Дубна"),SPB("Санкт-Петербург"),
            KHIMKI("Химки"),VORONEZH("Воронеж"),TULA("Тула"),EKB("Екатеринбург"),
            ORYOL("Орел"),BRYANSK("Брянск"),BELGOROD("Белгород"),MINSK("Минск"),
            KURSK("Курск"),FAR_LOCATION("requires far location!");

            String cityName;

        CITIES(String cityName) {
            this.cityName=cityName;
        }

         @Override
         public String toString() {
             return cityName ;

         }
     }


    static String filterLocation(String parsingString) {
        String result="";
        CITIES [] arr = CITIES.values();
        for (CITIES city : arr) {
            if(parsingString.contains(city.toString()))
               result=city.toString();
                return result ;
        }
     return CITIES.FAR_LOCATION.toString();
    }
    static String filterExperience(String parsingString) {
        if(parsingString.matches(".*\\d.*")){
            return parsingString.replaceAll("[^0-9]", "").substring(0,1);
        }
        return "0";
    }
    static String filterSalary(String parsingString){
        if(parsingString.matches(".*\\d.*")){
            Matcher matcher = Pattern.compile("\\d+").matcher(parsingString);
            matcher.find();
            return matcher.group();
        }

        return"0";
    }

    static String filterRequirements(String parsingString){
        StringBuilder stringBuilder = new StringBuilder();

        for (String string : Skill.getValues()){
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