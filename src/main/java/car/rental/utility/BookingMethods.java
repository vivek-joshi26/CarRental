package car.rental.utility;

import car.rental.model.dto.BookingRequestDTO;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class BookingMethods {

    public double findTotalTime(BookingRequestDTO bookingRequestDTO) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date startDate = formatter.parse(bookingRequestDTO.getStartDate() + " " + bookingRequestDTO.getStartTime());
        Date endDate = formatter.parse(bookingRequestDTO.getEndDate() + " " + bookingRequestDTO.getEndTime());

        long difference_In_Time = endDate.getTime() - startDate.getTime();
        long difference_In_Days = TimeUnit.MILLISECONDS.toDays(difference_In_Time) % 365;
        long difference_In_Hours = TimeUnit.MILLISECONDS.toHours(difference_In_Time) % 24;
        double difference_In_Minutes = TimeUnit.MILLISECONDS.toMinutes(difference_In_Time) % 60;
        double totalDifference = (difference_In_Days * 24) + difference_In_Hours + (difference_In_Minutes / 60);

        return totalDifference;
    }
}
