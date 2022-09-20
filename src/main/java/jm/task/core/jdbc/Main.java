package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args)  {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Petr", "Efimov", (byte) 12);
        userService.saveUser("Ivan", "Somov", (byte) 43);
        userService.saveUser("Efim", "Petrov", (byte) 64);
        userService.saveUser("John", "Ivanov", (byte) 22);
        List<User> allUsers = userService.getAllUsers();
        System.out.println(allUsers);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
