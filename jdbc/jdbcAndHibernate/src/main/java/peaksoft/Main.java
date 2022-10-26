package peaksoft;

import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;
import java.util.Scanner;

public class Main {
    static Scanner scannerN = new Scanner(System.in);
    static Scanner scannerS = new Scanner(System.in);

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        while (true) {
            commands();
            int a = getButton();
            if (a == 1) {
                userService.createUsersTable();
            } else if (a == 2) {
                userService.dropUsersTable();
            } else if (a == 3) {
                System.out.print("Input first name: ");
                String firstName = scannerS.nextLine();
                System.out.print("Input last name: ");
                String lastName = scannerS.nextLine();
                System.out.print("Input age: ");
                byte age = scannerN.nextByte();
                userService.saveUser(firstName, lastName, age);
            } else if (a == 4) {
                System.out.print("Input user's Id: ");
                long id = scannerN.nextLong();
                userService.removeUserById(id);
            } else if (a == 5) {
                userService.getAllUsers().forEach(System.out::println);
            } else if (a == 6) {
                userService.cleanUsersTable();
            } else if (a == 0) {
                break;
            }
        }
    }

    public static void commands() {
        System.out.println("--------------Commands------------------");
        System.out.println("Press 1 to create user's table");
        System.out.println("Press 2 to drop user's table");
        System.out.println("Press 3 to add new User");
        System.out.println("Press 4 to remove User by Id");
        System.out.println("Press 5 to get all users");
        System.out.println("Press 6 to clean user's table");
        System.out.println("Press 0 to exit the App");
        System.out.println("----------------------------------------");
    }

    public static int getButton() {
        System.out.print("Input number: ");
        int num = scannerN.nextInt();
        return num;
    }
}
