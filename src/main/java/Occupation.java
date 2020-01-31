
import jdk.jfr.DataAmount;

import java.util.Date;
import java.util.Objects;


public class Occupation extends Skills{

    private int ID;
    private String company;
    private int salary;
    private String city;
    private Date date;
    private String URL;
    private int expirience;/*counted in years*/

    public Occupation(int ID, String company, int salary, String city, Date date, String URL, int expirience) {
        this.ID = ID;
        this.company = company;
        this.salary = salary;
        this.city = city;
        this.date = date;
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

    public String getCity() {
        return city;
    }

    public Date getDate() {
        return date;
    }

    public String getURL() {
        return URL;
    }

    public int getExpirience() {
        return expirience;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setExpirience(int expirience) {
        this.expirience = expirience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Occupation)) return false;
        if (!super.equals( o )) return false;
        Occupation that = (Occupation) o;
        return getID() == that.getID() &&
                getSalary() == that.getSalary() &&
                getExpirience() == that.getExpirience() &&
                Objects.equals( getCompany(), that.getCompany() ) &&
                Objects.equals( getCity(), that.getCity() ) &&
                Objects.equals( getDate(), that.getDate() ) &&
                Objects.equals( getURL(), that.getURL() );
    }

    @Override
    public int hashCode() {

        return Objects.hash( super.hashCode(), getID(), getCompany(),getSalary(), getCity(), getDate(), getURL(), getExpirience() );
    }
}
