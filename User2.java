package miniproject;
import java.io.*;
import java.util.Arrays;

class User2 implements Serializable {
    private String username;
    private String password;
    private int userType;

    public User2(String username, String password, int userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getUserType() {
        return userType;
    }

    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            User[] users = new User[0];

            while (true) {
                System.out.print("Enter username (or type 'exit' to stop): ");
                String inputUsername = reader.readLine();

                if (inputUsername.equalsIgnoreCase("exit")) {
                    break; 
                }

                System.out.print("Enter password: ");
                String inputPassword = reader.readLine();
                
                User newUser = new User(inputUsername, inputPassword, 1);
                users = Arrays.copyOf(users, users.length + 1);
                users[users.length - 1] = newUser;

                System.out.println("User added.");
            }

            saveUsers(users);

            System.out.print("Enter username for login: ");
            String inputUsername = reader.readLine();

            System.out.print("Enter password for login: ");
            String inputPassword = reader.readLine();

            if (login(inputUsername, inputPassword)) {
                System.out.println("Login successful");
            } else {
                System.out.println("Login failed");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveUsers(User[] users) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("User.txt"))) {
            for (User user : users) {
                outputStream.writeObject(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean login(String inputUsername, String inputPassword) {
        User[] users = loadUsers();

        for (User user : users) {
            if (user.getUsername().equals(inputUsername) && user.getPassword().equals(inputPassword)) {
                return true;
            }
        }

        return false;
    }

    public static User[] loadUsers() {
        User[] users = new User[0];

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("User.txt"))) {
            while (true) {
                User user = (User) inputStream.readObject();
                users = Arrays.copyOf(users, users.length + 1);
                users[users.length - 1] = user;
            }
        } catch (EOFException e) {
            // End of file , do nothing
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return users;
    }
}
