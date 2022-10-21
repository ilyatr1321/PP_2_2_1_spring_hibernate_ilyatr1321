package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Dmitriy", "Petrov", "dima007@mail.ru");
      User user2 = new User("Oleg", "Popov", "oleg007@mail.ru");
      User user3 = new User("Lesha", "Sidorov", "lesha007@mail.ru");
      User user4 = new User("Wowa", "Kozlov", "wowa007@mail.ru");
      User user5 = new User("Sergey", "Kuzmin", "sergey007@mail.ru");

      Car Bmw = new Car("Bmw", 5);
      Car Audi = new Car("Audi", 3);
      Car Lada = new Car("Lada", 2);
      Car Porshe = new Car("Porshe", 7);
      Car Fiat = new Car("Fiat", 1);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      userService.add(user1.setCar(Audi).setUser(user1));
      userService.add(user2.setCar(Bmw).setUser(user2));
      userService.add(user3.setCar(Lada).setUser(user3));
      userService.add(user4.setCar(Porshe).setUser(user4));
      userService.add(user5.setCar(Fiat).setUser(user5));

      System.out.println(userService.getUserByCar("Audi", 3));
      context.close();
   }
}
