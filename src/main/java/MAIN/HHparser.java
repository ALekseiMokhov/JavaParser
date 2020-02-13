package MAIN;
import DAO.Vacancy_CRUD;

public class HHparser {
@Desc(text = " parse() [& save data] from hh.ru, retrieve data by calling read();")

    public static void main(String[] args) {

       parse();
       read();
    }
    public static void parse(){
        Parser parser = new Parser();
        parser.work();
    }
    public static void read(){
        Vacancy_CRUD vacancy_crud= new Vacancy_CRUD();
        vacancy_crud.getData();
    }


}