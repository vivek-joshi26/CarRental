package car.rental.services;

import car.rental.model.Booking;
import car.rental.model.Car;
import car.rental.model.User;
import car.rental.repository.BookingRepo;
import car.rental.repository.CarRepo;
import car.rental.repository.UserRepo;
import car.rental.utility.BookingMethods;
import car.rental.model.dto.BookingRequestDTO;
import car.rental.model.dto.BookingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;

@Component
public class BookingService {

    @Autowired
    BookingRepo bookingRepo;

    @Autowired
    CarRepo carRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    BookingMethods bookingMethods;

    Logger logger = LoggerFactory.getLogger(BookingService.class);

    public ResponseEntity addBookingDirectly(Booking booking) {
        bookingRepo.save(booking);
        return ResponseEntity.ok("Added successfully with id:" + booking.getId());
    }


//**************************************************

    public Map<Car, Double> fetchAvailableCars(BookingRequestDTO bookingRequestDTO) throws Exception {
        List<Booking> bookingList = bookingRepo.findBookings(bookingRequestDTO.getStartDate(), bookingRequestDTO.getStartTime(), bookingRequestDTO.getEndDate(), bookingRequestDTO.getEndTime());
        System.out.println(bookingList);    //these are the bookings that is already done for the given time period
        logger.info("*************************** Inside fetchAvailableCars method of BookingService class, bookings done for the given time period -> {} ********************************",bookingList);
        Set carIds = new HashSet();
        List<Car> totalCars = carRepo.findAll();    //total cars available in db
        List<Car> availableCars = new ArrayList<>();    //cars that are available for the given timeframe
        Map<Car, Double> availableCarsWithPrice = new HashMap<>();   // Car and corresponding rates which will be returned to the front end

        for (Booking booking : bookingList) {
            carIds.add(booking.getCar().getId());   //gets the carIds that are already booked during that time
        }

        for (Car car : totalCars) {
            if (carIds.contains(car.getId()) == false)     //checking if carid is already booked then don't show those in the available list
                availableCars.add(car);
        }


        //Find the total time period for which a car is required
        double totalDifferenceInHours = bookingMethods.findTotalTime(bookingRequestDTO);

        for (Car car : availableCars  //Cars alongwith total price for the mentioned time period
        ) {
            availableCarsWithPrice.put(car, (double) Math.round((totalDifferenceInHours * car.getPlan().getPricePerKM())));
        }
        System.out.println(availableCarsWithPrice);
        return availableCarsWithPrice;
    }


//Add booking based on input received for Car id, User id, Amount, booking dates from front end

    public ResponseEntity addBooking(BookingDTO bookingDTO) {
        System.out.println("Inside addBooking in service");
        Booking booking = new Booking();
        User user = userRepo.findById(bookingDTO.getUserId());
        Car car = carRepo.findById(bookingDTO.getCarId());
        Date currentTimeStamp = new Date();

        booking.setBookingDate(new Timestamp(currentTimeStamp.getTime()));
        booking.setCar(car);
        booking.setUser(user);
        booking.setBookingAmount(bookingDTO.getBookingAmount());
        booking.setStartDate(bookingDTO.getStartDate());
        booking.setStartTime(bookingDTO.getStartTime());
        booking.setEndDate(bookingDTO.getEndDate());
        booking.setEndTime(bookingDTO.getEndTime());
        booking.setDLNumber(bookingDTO.getDLNumber());

        bookingRepo.save(booking);
        return ResponseEntity.ok("Added successfully with id:" + booking.getId());
    }


}
