package car.rental.controller;

import car.rental.exception.ErrorMessage;
import car.rental.model.Plan;
import car.rental.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/plans")
public class PlanController {

    @Autowired
    PlanService planService;

    // Add a new Plan to DB
    @PostMapping
    public ResponseEntity addPlan(@Valid @RequestBody Plan plan, Errors errors){
        if(errors.hasErrors()){
            String response = errors.getAllErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.joining(","));
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());
            errorMessage.setMessage(response);
            return ResponseEntity.ok(errorMessage);
        }
        else
            return planService.addPlan(plan);
    }

    // Get all the plans
    @GetMapping
    public List<Plan> findPlans(){
        return planService.findPlans();
    }

}
