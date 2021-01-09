package car.rental.controller;

import car.rental.exception.ErrorMessage;
import car.rental.model.Booking;
import car.rental.model.Car;
import car.rental.repository.BookingRepo;
import car.rental.utility.BookingRequest;
import car.rental.services.BookingService;
import car.rental.utility.BookingRequestFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;

    //Ignore
    @PostMapping
    public ResponseEntity addBooking(@RequestBody Booking booking){
        return bookingService.addBookingDirectly(booking);
    }


    //Return Map with cars and corresponding TotalCharges
    @PostMapping("/add")
    public ResponseEntity addBooking2(@RequestBody @Valid BookingRequest bookingRequest, Errors errors) throws Exception{
        if(errors.hasErrors()){
            String response =  errors.getAllErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.joining(","));
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(response);
            errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.ok(errorMessage);
        }
        else {
            //System.out.println("Calling Checkbooking");
            Map<Car,Double> availableCars = bookingService.fetchAvailableCars(bookingRequest);
            if(availableCars.size()>0)
                return ResponseEntity.ok(availableCars);
            else
                return ResponseEntity.ok("No cars available for this timeframe");
        }

    }


    //Make the final booking
    @PostMapping("/booking")
    public ResponseEntity addBooking3(@RequestBody @Valid BookingRequestFinal bookingRequestFinal, Errors errors) throws Exception{
        if(errors.hasErrors()){
            String response =  errors.getAllErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.joining(","));
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(response);
            errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.ok(errorMessage);
        }
        else {
            return bookingService.addBooking(bookingRequestFinal);
        }
    }
}

