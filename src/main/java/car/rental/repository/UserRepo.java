package car.rental.repository;

import car.rental.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepo extends JpaRepository<User,Integer> {


    // Query used to check if user with this email id is already present
    User findByEmailid(String Emailid);

    User findById(long Id);


        /*
    // We don't need to provide more details the name of the method should start with findBy or getBy{data JPA is helping with the query} and should end with property name
    List<Alien> findByTech(String tech);
    // Similarly findBy property name alongwith GreaterThan or LessThan also works to give a list of values based on value given
    List<Alien> findByAidGreaterThan(int aid);


     */

    /*
    //We can also create our own custom queries, suppose we want all based on tech and sorted on names then
    @Query("from Alien where tech=?1 order by aname")
    List<Alien> findByTechSorted(String tech);

     */


}
