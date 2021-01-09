package car.rental.repository;

import car.rental.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface PlanRepo extends JpaRepository<Plan,Integer> {

    @Query("from Plan where carType=?1 and fuelType=?2")
    Plan findByCarFuel(String carType, String fuelType);
}
