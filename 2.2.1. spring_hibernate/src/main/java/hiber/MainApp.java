package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      Car car1 = new Car("BMW", 4);
      user1.setCar(car1);
      userService.add(user1);

      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      Car car2 = new Car("Porsche", 228);
      user2.setCar(car2);
      userService.add(user2);

      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         if(user.getCar() != null){
            System.out.println("Car_model = "+user.getCar().getModel());
            System.out.println("Car_series = "+user.getCar().getSeries());
         }else {
            System.out.println("User has no Car");
         }
         System.out.println();
      }

      System.out.println();
      System.out.println("------------------------------");
      System.out.println(userService.getUserByCar(car2));
      System.out.println("------------------------------");
      System.out.println();

      context.close();
   }
}
