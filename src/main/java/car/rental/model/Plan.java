package car.rental.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;

@Entity
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @NotBlank(message = "Fuel type can't be empty.")
    private String fuelType;

    @NotBlank(message = "Car type can't be empty.")
    private String carType;

    @Min(value = 1,message = "Price per km must be positive.")
    private int pricePerKM;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public int getPricePerKM() {
        return pricePerKM;
    }

    public void setPricePerKM(int pricePerKM) {
        this.pricePerKM = pricePerKM;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", fuelType='" + fuelType + '\'' +
                ", carType='" + carType + '\'' +
                ", pricePerKM=" + pricePerKM +
                '}';
    }
}
