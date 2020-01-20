import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Data
public class Occupation {

    private int ID;
    private String company;
    private String position;
    private int salary;
    private String city;
    private Date date;
    private String URL;
    private int expirience;/*counted in years*/



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
                Objects.equals( getPosition(), that.getPosition() ) &&
                Objects.equals( getCity(), that.getCity() ) &&
                Objects.equals( getDate(), that.getDate() ) &&
                Objects.equals( getURL(), that.getURL() );
    }

    @Override
    public int hashCode() {

        return Objects.hash( super.hashCode(), getID(), getCompany(), getPosition(), getSalary(), getCity(), getDate(), getURL(), getExpirience() );
    }
}
