    package service;

    import domain.User;
    import exception.UserException;

    import java.io.BufferedReader;
    import java.io.BufferedWriter;
    import java.io.File;
    import java.io.FileReader;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;
    import java.util.ArrayList;
    import java.util.Comparator;
    import java.util.List;
    import java.util.Objects;
    import java.util.stream.Collectors;


    public class UserService {
        private List<User> users;

        public UserService(String filePath) {
            this.users = new ArrayList<>();
            loadUsersFromFile(filePath);
        }

        public void addUser(User user, String filePath) throws UserException {
            if (!user.getEmail().contains("@")) {
                throw new UserException(UserException.INVALID_EMAIL);
            }

            if (user.getAge() < 18) {
                throw new UserException(UserException.INVALID_AGE);
            }

            if (user.getHeight() % 1 == 0) {
                throw new UserException(UserException.INVALID_HEIGHT);
            }

            for (User u : users) {
                if (u.getEmail().equals(user.getEmail())) {
                    throw new UserException(UserException.DUPLICATE_EMAIL);
                }
            }

            users.add(user);
            saveUserToFile(user, filePath);
        }

        public List<User> listAllUsers() {
            return users;
        }

        public User findUserByEmail(String email) throws UserException {
            if (!email.contains("@")) {
                throw new UserException(UserException.INVALID_EMAIL);
            }
            User user = users.stream()
                    .filter(u -> u.getEmail().equals(email))
                    .findFirst()
                    .orElse(null);
            if (user == null) {
                throw new UserException(UserException.EMAIL_NOT_FOUND);
            }
            return user;
        }

        public void deleteUserByEmail(String email, String filePath) {
            User user = findUserByEmail(email);
            if (user != null) {
                users.remove(user);
                try {
                    Path path = Paths.get(filePath + "\\"+ user.getName().toUpperCase() + ".txt");
                    Files.delete(path);
                } catch (IOException e) {
                    System.out.println("Error deleting file: " + e.getMessage());
                }
            }
        }

        public List<User> findUserByName(String name) throws UserException {
            List<User> userList = users.stream()
                    .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                    .sorted(Comparator.comparing(User::getName))
                    .collect(Collectors.toList());
            if (users.isEmpty()) {
                throw new UserException(UserException.USER_NOT_FOUND);
            }
            return userList;
        }


        private void saveUserToFile(User user, String filePath) {
            String fileName = filePath + "\\" + user.getName().toUpperCase() + ".txt";
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
                bw.write("Name: " + user.getName() + "\n");
                bw.write("Email: " + user.getEmail() + "\n");
                bw.write("Age: " + user.getAge() + "\n");
                bw.write("Height: " + user.getHeight() + "\n");
                bw.flush();
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        private void loadUsersFromFile(String filePath) {
            File folder = new File(filePath);
            for (File file : Objects.requireNonNull(folder.listFiles())) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String name = br.readLine().split(": ")[1];
                    String email = br.readLine().split(": ")[1];
                    Integer age = Integer.parseInt(br.readLine().split(": ")[1]);
                    Double height = Double.parseDouble(br.readLine().split(": ")[1]);
                    User user = new User(name, email, age, height);
                    users.add(user);
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }
