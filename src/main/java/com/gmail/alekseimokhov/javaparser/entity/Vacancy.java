package com.gmail.alekseimokhov.javaparser.entity;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vacancy {

    private int ID;
    private String city;
    private String company;
    private int experience;
    private Date date;
    private int salary;
    private String URL;

    private List<Skill> SKILLS_REQUIRED =new ArrayList<>();

    public Vacancy() {
        this.date=Date.valueOf(LocalDate.now());
    }

    public Vacancy(int salary, int experience, String company, String city, String URL) {
        this.ID = 0;
        this.company = company;
        this.salary = salary;
        this.city = city;
        this.date = Date.valueOf(LocalDate.now());
        this.URL = URL;
        this.experience = experience;
    }

    public int getID() {
        return ID;
    }

    public String getCompany() {
        return company;
    }

    public int getExperience() {
        return experience;
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

    public void setExperience(int experience) {
        this.experience = experience;
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

    public List<Skill> getSKILLS_REQUIRED() {
        return SKILLS_REQUIRED;
    }

    public void setSKILLS_REQUIRED(List<Skill> SKILLS_REQUIRED) {
        this.SKILLS_REQUIRED = SKILLS_REQUIRED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(getURL(), vacancy.getURL());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getURL());
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "ID=" + ID +
                ", city='" + city + '\'' +
                ", company='" + company + '\'' +
                ", experience=" + experience +
                ", date=" + date +
                ", salary=" + salary +
                ", URL='" + URL + '\'' +
                '}';
    }
}