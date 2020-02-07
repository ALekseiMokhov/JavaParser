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

    static String filterRequirements(String parsingString){
        StringBuilder stringBuilder = new StringBuilder();
        for (String string:Skills.SKILL_SET){
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