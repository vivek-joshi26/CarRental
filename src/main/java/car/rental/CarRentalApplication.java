package car.rental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class CarRentalApplication {

	public static void main(String[] args) {

				SpringApplication.run(CarRentalApplication.class, args);

	}

}
