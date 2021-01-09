package car.rental.exception;

public class UserAlreadyExists extends Exception {

public UserAlreadyExists(){
    super();
}

public UserAlreadyExists(String errors){
    super(errors);
}
}
