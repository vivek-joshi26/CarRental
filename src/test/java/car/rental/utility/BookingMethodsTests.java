package car.rental.utility;

import car.rental.model.dto.BookingRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@SpringBootTest
class BookingMethodsTests {


	@InjectMocks
	private BookingMethods classUnderTest;

	@Mock
	private BookingRequestDTO mockBooking;


	@BeforeEach
	void initService() {
		MockitoAnnotations.openMocks(this);
	}

	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private LocalDate startDate = LocalDate.parse("2020-01-01", dateFormatter);
	private LocalDate endDate = LocalDate.parse("2020-02-01", dateFormatter);
	private LocalTime startTime = LocalTime.parse("12:00:00");
	private LocalTime endTime = LocalTime.parse("12:00:00");

	@Test
	void findTotalTimeTest() throws Exception {

		Mockito.when(mockBooking.getStartDate()).thenReturn(startDate);
		Mockito.when(mockBooking.getEndDate()).thenReturn(endDate);
		Mockito.when(mockBooking.getStartTime()).thenReturn(startTime);
		Mockito.when(mockBooking.getEndTime()).thenReturn(endTime);

		double res = classUnderTest.findTotalTime(mockBooking);
		Assertions.assertEquals(744.0, res);
	}

}
