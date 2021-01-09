package car.rental.services;

import car.rental.exception.UserAlreadyExists;
import car.rental.exception.UserNotPresent;
import car.rental.model.User;
import car.rental.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    UserRepo userRepo;

    //To add an user
    public ResponseEntity addUser(User user) throws UserAlreadyExists {

        User check = null;
        check = userRepo.findByEmailid(user.getEmailid());

        if(check!=null)
            throw new UserAlreadyExists("User with email id-"+user.getEmailid()+" already exists, please try with a unique email id.");
        else {
            userRepo.save(user);
            String response = "User with email id-" + user.getEmailid() + " added successfully";
             return ResponseEntity.ok(response);
        }

    }

    //To update an existing user
    public ResponseEntity updateUser(User user) throws UserNotPresent {

        User check = null;
        String response = "";
        check = userRepo.findByEmailid(user.getEmailid());
        if(check==null)
            throw new UserNotPresent("User with emaid id-"+user.getEmailid()+" does not exist, please first register this user");
        else {
            // TO get a copy of existing entry such that a new entry won't be created and existing one will be updated
            User oldUser = userRepo.getOne((int) check.getId());
            oldUser.setAge(user.getAge());
            oldUser.setEmailid(user.getEmailid());
            oldUser.setName(user.getName());
            oldUser.setNumber(user.getNumber());
            oldUser.setPassword(user.getPassword());
            userRepo.save(oldUser);
            response = "User details with email id-"+user.getEmailid()+" updated successfully.";
            return ResponseEntity.ok(response);
        }

    }



    //To login a user
    public ResponseEntity loginUser(String emailid,String password) throws UserNotPresent {
        User check = null;
        String response = "";
        check = userRepo.findByEmailid(emailid);
        if(check==null)
            throw new UserNotPresent("User with emaid id-"+emailid+" does not exist, please first register this user");
        else {
            if(password.equals(check.getPassword()))
                response = "You are logged in!!";
            else
                response = "Incorrect password, please try again with correct password.";

            return ResponseEntity.ok(response);
        }

    }


    //To delete a user
    public ResponseEntity deleteUser(String emailid) throws UserNotPresent{
        User check = null;
        String response = "";
        check = userRepo.findByEmailid(emailid);
        if(check==null)
            throw new UserNotPresent("User with emaid id-"+emailid+" does not exist.");
        else {
                userRepo.delete(check);
                response = "User with emaid id-"+emailid+" deleted successfully.";
                return ResponseEntity.ok(response);
        }
    }
}
