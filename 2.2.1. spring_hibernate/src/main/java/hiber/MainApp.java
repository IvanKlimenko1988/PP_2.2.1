package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        Car car1 = new Car("Mazda", 6);
        Car car2 = new Car("Volvo", 90);
        Car car3 = new Car("BMW", 7);
        Car car4 = new Car("Lada", 21015);

        carService.add(car1);
        carService.add(car2);
        carService.add(car3);
        carService.add(car4);

        userService.add(new User("Ivan", "Klimov", "ivan@mail.ru", car1));
        userService.add(new User("Igor", "Belov", "igor@mail.ru", car2));
        userService.add(new User("Gleb", "Sharapov", "gleb@mail.ru", car3));
        userService.add(new User("Stepan", "Smirnov", "stepan@mail.ru", car4));

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
