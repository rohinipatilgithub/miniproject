package miniproject;
import java.io.*;

import java.util.Arrays;

class User implements Serializable {
    private String username;
    private String password;
    private int userType;

    public User(String username, String password, int userType) {
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

   // public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));{

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

    //Faculty
class Faculty implements Serializable{
    private String user;
    private String name;
    private String subject;

   public Faculty(String user, String name, String subject) {
        this.user = user;
        this.name = name;
        this.subject = subject;
    }

    public String getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public static void createFaculty(String user, String name, String subject) {
        Faculty faculty = new Faculty(user, name, subject);
        String facultyInfo = faculty.getUser() + "," + faculty.getName() + "," + faculty.getSubject();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("faculties.txt", true))) {
            writer.write(facultyInfo);
            writer.newLine();
            System.out.println("Faculty added successfully.");
        } catch (IOException e) {
            System.err.println("Error: Unable to add faculty to the file.");
            e.printStackTrace();
        }
    }

    public static void searchFaculty(String facultyUser) {
        try (BufferedReader reader = new BufferedReader(new FileReader("faculties.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String user = parts[0];
                    String name = parts[1];
                    String subject = parts[2];

                    if (user.equals(facultyUser)) {
                        System.out.println("Faculty User: " + user);
                        System.out.println("Faculty Name: " + name);
                        System.out.println("Faculty Subject: " + subject);
                        return;
                    }
                }
            }

            System.out.println("Faculty not found.");
        } catch (FileNotFoundException e) {
            System.err.println("Error: Faculty file not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error reading faculty file.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createFaculty("faculty1", "mr krushna", "physics");
        createFaculty("faculty2", "miss bhagya", "english");

        searchFaculty("faculty1");
    }
}
  //Student
 class Student implements Serializable {

    private String User;
    private String Name;
    private String Course;

    public Student(String User, String Name, String Course) {
        this.User = User;
        this.Name = Name;
        this.Course = Course;
    }

    public String getUser() {
        return User;
    }

    public String getName() {
        return Name;
    }

    public String getCourse() {
        return Course;

    }

    public void printDetails() {
        System.out.println("Students Details : ");
        System.out.println("User : " + getUser());
        System.out.println("Name : " + getName());
        System.out.println("Course : " + getCourse());
    }

    public static void createStudent(String User, String Name, String Course)
    {
        Student obj = new Student(User, Name, Course);
        String StudentInfo = obj.getUser() + "," + obj.getName() + "," + obj.getCourse();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Student.txt", true))) 
        {
            writer.write(StudentInfo);
            writer.newLine();
            System.out.println("Student added Successfully.");
        } 
        catch (IOException e) {
            System.err.println("Error : Unable to add Student to the file.");
            e.printStackTrace();
        }
    }

    public static void searchStudent(String StudentUser) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Student.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(",");
                if (arr.length == 3) {
                    String User = arr[0];
                    String Name = arr[1];
                    String Course = arr[2];

                    if (User.equals(StudentUser)) {
                        System.out.println(" User : " + User);
                        System.out.println(" Name : " + Name);
                        System.out.println("Course : " + Course);
                        return;
                    }
                }
            }
            System.out.println("Student not Found.");
        } catch (FileNotFoundException e) {
            System.err.println("Error : Student file not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error reading Student file.");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        createStudent("Student1", "Pratik", "11th");
        createStudent("Student2", "Krushna", "12th");
        searchStudent("Student1");
    }


}
                   //Admin Start
 
 class User1 {
	    private final String username;

	    public User1(String username) {
	        this.username = username;
	    }

	    public String getUsername() {
	        return username;
	    }
	}  

	class Admin {
	    public User1 user;
	    public String name;

	    public Admin(User1 user, String name) {
	        this.user = user;
	        this.name = name;
	    }

	    public void printDetails() {
	        System.out.println("Admin Details:");
	        System.out.println("Username: " + user.getUsername());
	        System.out.println("Name: " + name);
	    }
	}

	 class AdminManagement implements Serializable {

	    public static void createFaculty(String username, String name) {
	        // Implement Create Faculty
	    	
	    	
	        // You can create a new Faculty object and add it to your data structure.
	    }

	    public static void createStudent(String username, String name) {
	        // Implement Create Student
	        // You can create a new Student object and add it to your data structure.
	    }

	    public static void searchFaculty() {
	        // Implement Search A Faculty
	        // Search for a Faculty using the provided username.
	    }

	    public static void searchStudent() {
	        // Implement Search A Student
	        // Search for a Student using the provided username.
	    }

	    public static void printAllFacultyDetails() {
	        // Implement Print Details of all Faculties
	        // Print details of all faculty members.
	    }

	    public static void printAllStudentDetails() {
	        // Implement Print Details of all Students
	        // Print details of all students.
	    }

	    public static void printAllUserData() {
	        // Implement Print all User Data
	        // Print details of all users (both faculty and students).
	    }

	    public static void AdminManagementSystem(String[] args) {
	        boolean exit = false;

	        while (true) {
	            System.out.println("1. Create Faculty");
	            System.out.println("2. Create Student");
	            System.out.println("3. Search A Faculty");
	            System.out.println("4. Search A Student");
	            System.out.println("5. Print Details of all Faculties");
	            System.out.println("6. Print Details of all Students");
	            System.out.println("7. Print all User Data");
	            System.out.println("8. Exit");

	            System.out.println("Enter the No.");

	            // Read user input using your preferred method (e.g., command-line arguments, file input, etc.).

	            int input = 0; /* Read input from your preferred source */;

	            switch (input) {
	                case 1:
	                    createFaculty(/* Pass required parameters */);
	                    break;
	                case 2:
	                    createStudent(/* Pass required parameters */);
	                    break;
	                case 3:
	                    searchFaculty(/* Pass required parameters */);
	                    break;
	                case 4:
	                    searchStudent(/* Pass required parameters */);
	                    break;
	                case 5:
	                    printAllFacultyDetails();
	                    break;
	                case 6:
	                    printAllStudentDetails();
	                    break;
	                case 7:
	                    printAllUserData();
	                    break;
	                case 8:
	                    exit = true;
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	                    break;
	            }
	        }
	    }

	    private static void createStudent() {
	    }

	    private static void createFaculty() {
	    }
	}



	


