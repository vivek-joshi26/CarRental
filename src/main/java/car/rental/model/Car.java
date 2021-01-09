package car.rental.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy="native")
    private long id;

    private String name;

    private String color;

    @NotBlank(message = "Please provide car type(Hatchback,Sedan,SUV)")
    private String type;

    @NotBlank(message = "Please provide fuel type(Petrol,Diesel)")
    private String fuel;

    @NotBlank(message = "Please provide car registration Number")
    private String registrationNumber;

    //Many cars can be associated with the same plan
    @ManyToOne
    private Plan plan;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", type='" + type + '\'' +
                ", fuel='" + fuel + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", plan=" + plan +
                '}';
    }
}
