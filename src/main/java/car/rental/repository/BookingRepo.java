package car.rental.repository;

import car.rental.model.Booking;
import car.rental.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public interface BookingRepo extends JpaRepository<Booking,Integer> {


//select * from Booking where startTime between "14:00:00" AND "17:00:00" OR endTime between "14:00:00" AND "17:00:00"
// AND(startDate BETWEEN "2020-12-19" AND "2020-12-20" OR endDate  BETWEEN "2020-12-19" AND "2020-12-20");
//    BETWEEN :startDate AND :endDate"
    @Query("from Booking where startTime between :startTime and :endTime or endTime between :startTime and :endTime and(startDate BETWEEN :startDate and :endDate OR endDate  BETWEEN :startDate and :endDate)")
    List<Booking> findBookings(@Param("startDate")LocalDate startDate,@Param("startTime") LocalTime startTime,@Param("endDate") LocalDate endDate,@Param("endTime") LocalTime endTime);
}
