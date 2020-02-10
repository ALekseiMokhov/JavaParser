package WEB;
import MAIN.Vacancy;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class HTML_Filter {

    private final static List<String> CITIES = Arrays.asList(new String[]{"Москва",
            "Зеленоград","Дубна","Санкт-Петербург",
            "Химки","Воронеж","Тула","Екатеринбург",
            "Орел", "Брянск","Белгород","Минск",
            "Курск"});

    static String filterLocation(String parsingString) {
     for(String  string: CITIES){
         if (parsingString.contains(string))return  string;
     }
     return "Required far relocation";
    }
    static String filterExpirience(String parsingString) {
        if(parsingString.matches(".*\\d.*")){
            return parsingString.replaceAll("[^0-9]", "").substring(0,1);
        }
        return "0";
    }

    static String filterRequirements(String parsingString){
        StringBuilder stringBuilder = new StringBuilder();
        for (String string: Vacancy.getSkillsRequired().keySet()){
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