import domain.User;
import exception.UserException;
import service.UserService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\"Enter the directory path where the data is or will be saved: ");
        String filePath = sc.nextLine();

        UserService userService = new UserService(filePath);

        while (true) {
            System.out.println("-----------------------------");
            System.out.println("1 - Register the user");
            System.out.println("2 - List all registered users");
            System.out.println("3 - Search user by email");
            System.out.println("4 - Search users by name");
            System.out.println("5 - Delete user");
            System.out.println("6 - Exit");
            System.out.println("Enter the desired option:");

            int option = sc.nextInt();
            sc.nextLine();

            try {
                switch (option) {
                    case 1:
                        System.out.print("Enter the user's name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter the user's email: ");
                        String email = sc.nextLine();

                        System.out.print("Enter the user's age: ");
                        Integer age = sc.nextInt();

                        System.out.print("Enter the user's height: ");
                        Double height = sc.nextDouble();

                        User user = new User(name, email, age, height);

                        userService.addUser(user, filePath);
                        System.out.println("User successfully registered!");
                        break;
                    case 2:
                        List<User> users = userService.listAllUsers();
                        for (User u : users) {
                            System.out.println("Name: " + u.getName());
                        }
                        break;
                    case 3:
                        System.out.print("Enter the email of the user you want to search for: ");
                        String userEmail = sc.nextLine();
                        User u = userService.findUserByEmail(userEmail);
                        System.out.println("Name: " + u.getName());
                        System.out.println("Email: " + u.getEmail());
                        System.out.println("Age: " + u.getAge());
                        System.out.println("Height: " + u.getHeight());
                        System.out.println("--------------------");
                        break;
                    case 4:
                        System.out.print("Enter the name of the users you want to search for: ");
                        String userName = sc.nextLine();
                        List<User> usersFound = userService.findUserByName(userName);
                        System.out.println("Users found");
                        for (User uf : usersFound) {
                            System.out.println("Name: " + uf.getName());
                        }
                        break;
                    case 5:
                        System.out.print("Enter the email of the user you want to delete: ");
                        String mail = sc.nextLine();
                        userService.deleteUserByEmail(mail, filePath);
                        System.out.println("User successfully deleted!");
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        sc.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (UserException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
