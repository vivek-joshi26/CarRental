package car.rental.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    //If we have multiple tables and all Ids are auto generated then we should have the above setting such that ids are maintained separately
    private long id;

    @NotBlank(message = "Name Cannot be empty.")
    private String name;

    @NotBlank(message = "Email Cannot be empty.")
    @Email(message = "Please provide a valid email address.")
    private String emailid;

    @NotBlank(message = "Password Cannot be empty.")
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{5,}$", message = "Password must be strong,combination of small and capital alphabets and numerals.")
    private String password;


    @Range(min = 1000l,max = 9999999999l, message = "Number should be of at least 4 digits and max 10 digits")
    //@Size(max = 10, message = "Number can be of 10 digits.")  (can't be used with Long)
    private long number;


    @Min(value = 18, message = "Minimum age should be 18")
    @Max(value = 80, message = "Maximum age can be 80.")
    private int age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emailid='" + emailid + '\'' +
                ", password='" + password + '\'' +
                ", number=" + number +
                ", age=" + age +
                '}';
    }
}
