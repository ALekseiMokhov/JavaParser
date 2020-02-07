
import java.time.LocalDate;

class Vacancy extends Skills {

    private int ID;
    private int salary;
    private int expirience;/*counted in years*/

    private String company;
    private String city;
    private String URL;

    private LocalDate date;

    public Vacancy(int ID, int salary, int expirience, String company, String city, String URL) {
        this.ID = ID;
        this.company = company;
        this.salary = salary;
        this.city = city;
        this.date = LocalDate.now();
        this.URL = URL;
        this.expirience = expirience;
    }

    public int getID() {
        return ID;
    }

    public String getCompany() {
        return company;
    }


    public int getSalary() {
        return salary;
    }
}