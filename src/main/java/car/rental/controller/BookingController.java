package car.rental.controller;

import car.rental.exception.ErrorMessage;
import car.rental.model.Booking;
import car.rental.model.Car;
import car.rental.model.dto.BookingRequestDTO;
import car.rental.services.BookingService;
import car.rental.model.dto.BookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    //Ignore, to directly post a booking without checking availability
    @PostMapping
    public ResponseEntity addBooking(@RequestBody Booking booking){
        return bookingService.addBookingDirectly(booking);
    }


    //Return Map with cars and corresponding TotalCharges
    @PostMapping("/availableCars")
    public ResponseEntity findAvailableCars(@RequestBody @Valid BookingRequestDTO bookingRequestDTO, Errors errors) throws Exception{
        if(errors.hasErrors()){
            String response =  errors.getAllErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.joining(","));
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(response);
            errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.ok(errorMessage);
        }
        else {
            //System.out.println("Calling Checkbooking");
            // ***************** Add logger *********************
            Map<Car,Double> availableCars = bookingService.fetchAvailableCars(bookingRequestDTO);
            if(availableCars.size()>0)
                return ResponseEntity.ok(availableCars);
            else
                return ResponseEntity.ok("No cars available for this timeframe");
        }

    }


    //Make the final booking
    @PostMapping("/makeBooking")
    public ResponseEntity makeBooking(@RequestBody @Valid BookingDTO bookingDTO, Errors errors) throws Exception{
        if(errors.hasErrors()){
            String response =  errors.getAllErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.joining(","));
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(response);
            errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.ok(errorMessage);
        }
        else {
            // ***************** Add logger *********************
            return bookingService.addBooking(bookingDTO);
        }
    }
}

