package car.rental.repository;

import car.rental.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarRepo extends JpaRepository<Car,Integer> {
    Car findById(long id);

}
