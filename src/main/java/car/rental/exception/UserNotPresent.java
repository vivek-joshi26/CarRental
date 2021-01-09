package car.rental.exception;

public class UserNotPresent extends Exception{

    public UserNotPresent(){
        super();
    }

    public UserNotPresent(String errors){
        super(errors);
    }
}
