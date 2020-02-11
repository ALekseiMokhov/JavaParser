package MAIN;
import DAO.Vacancy_CRUD;
import WEB.WebParser;
import java.util.List;
public class HHparser {
@Desc(text = " parse() [& save data] from hh.ru, retrieve data by calling read();")

    public static void main(String[] args) {
       read();
    }
    public static void parse(){
        WebParser webparser = new WebParser();
        webparser.work();
    }
    public static void read(){
        Vacancy_CRUD vacancy_crud= new Vacancy_CRUD();
        vacancy_crud.getData();
    }
    public static void loadVacancies(List<Vacancy> list){
        Vacancy_CRUD vacancie_persistance = new Vacancy_CRUD();
        for (Vacancy vacancy : list) {
            vacancie_persistance.mapVacancy(vacancy);
            System.out.println(vacancy.getSalary());
        }
    }

}