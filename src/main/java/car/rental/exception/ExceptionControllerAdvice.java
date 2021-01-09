package car.rental.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    //For generic Exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> genericException(Exception exception){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorMessage.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.OK);
    }


    //To handle user defined exceptions, like in this case when email id is already in use
    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<ErrorMessage> userAlreadyExistsException(UserAlreadyExists exception){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorMessage.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.OK);

    }

    //To handle user defined exceptions, like in this case when email id is not present
    @ExceptionHandler(UserNotPresent.class)
    public ResponseEntity<ErrorMessage> userrNotPresentException(UserNotPresent exception){
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorMessage.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.OK);

    }





    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        System.out.println("**********Inside handlevalidation***********");

        ErrorMessage error = new ErrorMessage();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(", ")));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }



// This handles the NotBlank validation present inside the User entity(When post request comes we validate the data with entity class)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> handleConstraintValidationExceptions(
            ConstraintViolationException ex) {
        System.out.println("**********Inside handleconstraint**********");


        ErrorMessage error = new ErrorMessage();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", ")));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }




    }
