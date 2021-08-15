package car.rental.services;

import car.rental.model.Plan;
import car.rental.repository.PlanRepo;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlanService {

    // ************* LOGGER ****************
    // Default log level in Spring application is info, so if we set logger.trace then it won't show
    Logger logger = LoggerFactory.getLogger(PlanService.class);

    /*
    @Autowired
    PlanRepo planRepo;

     */
    // Testing to see if Autowired can be replaced

    // **********  Autowired can be avoided by declaring a member and intializing in the constructor
    private final PlanRepo planRepo;

    public PlanService(PlanRepo planRepo) {
        this.planRepo = planRepo;
    }

    public ResponseEntity addPlan(Plan plan){
        planRepo.save(plan);
        String response = "Plan added successfully, plan id :" + plan.getId();
        logger.info("*************************** Adding a PLAN ********************************");
        return ResponseEntity.ok(response);
    }

    public List<Plan> findPlans(){
        // ************* Logger ******************
        logger.info("*************************** Getting all the PLANS -> {} ********************************",planRepo.findAll());
        return planRepo.findAll();

    }
}
