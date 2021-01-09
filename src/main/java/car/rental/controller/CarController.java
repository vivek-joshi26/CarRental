package car.rental.controller;

import car.rental.exception.ErrorMessage;
import car.rental.model.Car;
import car.rental.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarService carService;

    // Add a new car to DB
    @PostMapping
    public ResponseEntity addCar(@Valid @RequestBody Car car, Errors errors) throws Exception{
        if(errors.hasErrors()){
            String response =  errors.getAllErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.joining(","));
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(response);
            errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.ok(errorMessage);
        }
        else
            return carService.addCar(car);
    }

    // Fetch all the cars
    @GetMapping
    public List<Car> fetchCars(){
        return carService.fetchCars();
    }


    @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable("id") long id){
        return carService.deleteCar(id);
    }
}
