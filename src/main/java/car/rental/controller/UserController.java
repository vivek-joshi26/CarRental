package car.rental.controller;

import car.rental.exception.ErrorMessage;
import car.rental.exception.UserAlreadyExists;
import car.rental.exception.UserNotPresent;
import car.rental.model.User;
import car.rental.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    UserService userService;

    //TO add user details
    @PostMapping(value = "/add", consumes = {"application/json","application/xml"})
    public ResponseEntity  addUser(@Valid @RequestBody User user, Errors errors) throws UserAlreadyExists {
        System.out.println(user);
        String response = "";
        if(errors.hasErrors()) {      // If we are using Valid then we should use Errors alongwith it to catch all the exceptions and these will be handled by class ConstraintViolationException and if Errors is not being handled like this and we are only giving Valid then those will be handled by MethodArgumentNotValidException
            System.out.println(errors.getAllErrors());
            response = errors.getAllErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.joining(","));
            //System.out.println(response);
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(response);
            errorMessage.setErrorCode(HttpStatus.NOT_ACCEPTABLE.value());
            return ResponseEntity.ok(errorMessage);
        }
        else {

            return userService.addUser(user);
        }
    }


    //To update the data of an existing user
    @PutMapping(value = "/add", consumes = "application/json")
    public ResponseEntity  updateUser(@Valid @RequestBody User user, Errors errors) throws UserNotPresent {
        System.out.println(user);
        String response = "";
        if(errors.hasErrors()) {      // If we are using Valid then we should use Errors alongwith it to catch all the exceptions and these will be handled by class ConstraintViolationException and if Errors is not being handled like this and we are only giving Valid then those will be handled by MethodArgumentNotValidException
            System.out.println(errors.getAllErrors());
            response = errors.getAllErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.joining(","));
            //System.out.println(response);
            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage(response);
            errorMessage.setErrorCode(HttpStatus.NOT_ACCEPTABLE.value());
            return ResponseEntity.ok(errorMessage);
        }
        else {

            return userService.updateUser(user);
        }
    }


    //TO delete an existing user
    @GetMapping(value = "/delete/{emailid}")
    public ResponseEntity deleteUser(@PathVariable("emailid") String emailid) throws UserNotPresent{
      return userService.deleteUser(emailid);
    }


    //To login user using data in body
    @PostMapping(value = "/login")
    public ResponseEntity loginUser(@RequestBody User user) throws UserNotPresent {
        return userService.loginUser(user.getEmailid(),user.getPassword());
    }

    //To login user using URI parameters, for special character we need to encode them in request like for #,replace it with %23
    @GetMapping(value = "/login/{emailid}/{password}")
    public ResponseEntity loginUserPathVar(@PathVariable("emailid") String emailid,@PathVariable("password") String password) throws UserNotPresent{
        System.out.println(emailid+password);
        return userService.loginUser(emailid,password);
    }

    //To login user using query parameters
    @GetMapping(value = "/login")
    public ResponseEntity loginUserQueryParam(@RequestParam("emailid") String emailid,@RequestParam("password") String password) throws UserNotPresent{
        return userService.loginUser(emailid,password);
    }

}
