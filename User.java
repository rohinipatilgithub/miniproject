import java.io.*;

public class User implements Serializable {
    private String username;
    private String password;
    private String userType;

    public User() {
    }

    public User(String username, String password, String userType) {
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

    public String getUserType() {
        return userType;
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

    public String login(String inputUsername, String inputPassword) {
        User[] users = loadUsers();
        for (User user : users) {
            if (user.getUsername().equals(inputUsername) && user.getPassword().equals(inputPassword)) {
                return user.getUserType();
            }
        }
        return "0";
    }

    public static User[] loadUsers() {
        User[] users = null;
        int numberOfUsers = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("User.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 3) {
                    numberOfUsers++;
                }
            }

            if (numberOfUsers > 0) {
                users = new User[numberOfUsers];
                int index = 0;

                try (BufferedReader newBr = new BufferedReader(new FileReader("User.txt"))) {
                    while ((line = newBr.readLine()) != null) {
                        String[] userData = line.split(",");
                        if (userData.length == 3) {
                            String username = userData[0];
                            String password = userData[1];
                            String userType = userData[2];

                            users[index] = new User(username, password, userType);
                            index++;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public void printAllUserData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("User.txt"))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String userName = parts[0];
                    String password = parts[1];
                    String userType = parts[2];

                    System.out.println("Username: " + userName);
                    System.out.println("Password: " + password);
                    if (userType.equals("1")) {
                        System.out.println("UserType: Admin");
                    } else if (userType.equals("2")) {
                        System.out.println("UserType: Faculty");
                    } else {
                        System.out.println("UserType: Student");
                    }

                    System.out.println();
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found.");
        } catch (IOException e) {
        	throw new RuntimeException(e);

        }
    }
}
