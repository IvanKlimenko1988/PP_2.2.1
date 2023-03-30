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

        User user1 = context.getBean(User.class);
        User user2 = context.getBean(User.class);
        User user3 = context.getBean(User.class);
        User user4 = context.getBean(User.class);

        user1.setUser("Ivan", "Klimov", "ivan@mail.ru");
        user2.setUser("Igor", "Belov", "igor@mail.ru");
        user3.setUser("Stepan", "Smirnov", "stepan@mail.ru");
        user4.setUser("Gleb", "Sharapov", "gleb@mail.ru");

        Car car1 = context.getBean(Car.class);
        Car car2 = context.getBean(Car.class);
        Car car3 = context.getBean(Car.class);
        Car car4 = context.getBean(Car.class);

        car1.setCar("Mazda", 6);
        car2.setCar("Volvo", 90);
        car3.setCar("BMW", 7);
        car4.setCar("Lada", 21015);

        user1.setUserCar(car1);
        user2.setUserCar(car2);
        user3.setUserCar(car3);
        user4.setUserCar(car4);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.getUsersList();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car model = " + user.getUserCar().getModel());
            System.out.println("Car series = " + user.getUserCar().getSeries());
            System.out.println();
        }

        System.out.println(userService.findUser("Mazda", 6));

        context.close();
    }
}
