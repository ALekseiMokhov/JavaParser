package com.gmail.alekseimokhov.javaparser.domain;
import com.gmail.alekseimokhov.javaparser.dao.Skill_CRUD;
import com.gmail.alekseimokhov.javaparser.dao.Vacancy_CRUD;
import com.gmail.alekseimokhov.javaparser.buiseness_logic.Parser;
import com.gmail.alekseimokhov.javaparser.entity.Skill;
import com.gmail.alekseimokhov.javaparser.entity.Vacancy;
import java.util.Set;

public class Domain {

    public static void main(String[] args) {
        Vacancy_CRUD vacancy_crud = new Vacancy_CRUD();
        vacancy_crud.createTable();
        Skill_CRUD skill_crud = new Skill_CRUD();
        skill_crud.createTable();
       parse();
       loadVacancies();
       read();
    }
    public static void parse(){
        Parser parser = new Parser();
        parser.parseURL();
    }
    public static void read(){
        Vacancy_CRUD vacancy_crud= new Vacancy_CRUD();
        vacancy_crud.printAllData();
    }
    public static void loadVacancies(){
        Vacancy_CRUD vacancy_crud = new Vacancy_CRUD();
        Skill_CRUD skill_crud = new Skill_CRUD();
        Set<Vacancy> set= Parser.getVacancySet();
        for (Vacancy vacancy : set) {
            vacancy_crud.saveData(vacancy);
            for(Skill skill: vacancy.getSKILLS_REQUIRED()){
                skill_crud.saveData(skill);
            }

        }
    }



}