package car.rental.services;

import car.rental.model.Plan;
import car.rental.repository.PlanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlanService {

    @Autowired
    PlanRepo planRepo;

    public ResponseEntity addPlan(Plan plan){
        planRepo.save(plan);
        String response = "Plan added successfully, plan id :"+plan.getId();
        return ResponseEntity.ok(response);
    }

    public List<Plan> findPlans(){
        return planRepo.findAll();

    }
}
