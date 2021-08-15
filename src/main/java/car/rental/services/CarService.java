package car.rental.services;

import car.rental.model.Car;
import car.rental.model.Plan;
import car.rental.repository.CarRepo;
import car.rental.repository.PlanRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarService {

    @Autowired
    CarRepo carRepo;

    @Autowired
    PlanRepo planRepo;

    Logger logger = LoggerFactory.getLogger(CarService.class);

    public ResponseEntity addCar(Car car) throws Exception {
        Plan plan = planRepo.findByCarFuel(car.getType(), car.getFuel());
        if (plan == null) {
            logger.error("***************** Inside addCar method of CarService class, Plan is not available, ERROR ********************************");
            throw new Exception("Please provide a valid combination for type(Hatchback,Sedan,SUV) and Fuel(Petrol,Diesel)");
        }
        else {
            car.setPlan(plan);
            carRepo.save(car);
            String response = "Car added successfully with id:" + car.getId();
            logger.info("*************************** Inside addCar method of CarService class ********************************");
            return ResponseEntity.ok(response);
        }
    }

    public List<Car> fetchCars() {
        return carRepo.findAll();
    }


    public String deleteCar(long id) {
        Car car = carRepo.findById(id);
        if (car == null)
            return "Car with id:" + id + " does not exist.";
        else {
            logger.info("*************************** Inside deleteCar method of CarService class ********************************");
            carRepo.delete(car);
            return "Car with id: " + id + " deleted successfully.";
        }

    }

}
