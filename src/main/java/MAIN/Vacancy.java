package MAIN;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
/*import org.apache.commons.lang.RandomStringUtils;*/

public class Vacancy {

    private int ID;
    private String city;
    private String company;
    private int expirience;
    private Date date;
    private int salary;
    private String URL;


    private static HashMap<String,Boolean> SKILLS_REQUIRED =new HashMap<>();
        static {
        SKILLS_REQUIRED.put("HIBERNATE",false);
        SKILLS_REQUIRED.put("SPRING",false);
        SKILLS_REQUIRED.put("JAVA8",false);
        SKILLS_REQUIRED.put("GENERICS",false);
        SKILLS_REQUIRED.put("SQL",false);
        SKILLS_REQUIRED.put("NOSQL",false);
        SKILLS_REQUIRED.put("UI",false);
        SKILLS_REQUIRED.put("AJAX",false);
        SKILLS_REQUIRED.put("CSS",false);
        SKILLS_REQUIRED.put("HTML",false);
        SKILLS_REQUIRED.put("JUNIT",false);
        SKILLS_REQUIRED.put("LOG4J",false);
        SKILLS_REQUIRED.put("EJB",false);
        SKILLS_REQUIRED.put("ORACLE",false);
        SKILLS_REQUIRED.put("POSTGRES",false);
        SKILLS_REQUIRED.put("MONGODB",false);
        SKILLS_REQUIRED.put("CASSANDRA",false);
        SKILLS_REQUIRED.put("ООП",false);
        SKILLS_REQUIRED.put("JAVA CORE",false);
        SKILLS_REQUIRED.put("ТЕСТ",false);

    }


    public Vacancy(int salary, int expirience, String company, String city, String URL) {
        this.ID = 0;
        this.company = company;
        this.salary = salary;
        this.city = city;
        this.date = Date.valueOf(LocalDate.now());
        this.URL = URL;
        this.expirience = expirience;
    }

    public int getID() {
        return ID;
    }

    public String getCompany() {
        return company;
    }

    public int getExpirience() {
        return expirience;
    }

    public String getCity() {
        return city;
    }

    public String getURL() {
        return URL;
    }

    public Date getDate() {
        return date;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setExpirience(int expirience) {
        this.expirience = expirience;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setSkillsRequired(List<String> list){
      for(String s: list){
          if (SKILLS_REQUIRED.containsKey(s.toUpperCase())){
              SKILLS_REQUIRED.replace(s.toUpperCase(),true);
          }
      }

    }

    public static HashMap<String, Boolean> getSkillsRequired() {
        return SKILLS_REQUIRED;
    }
}