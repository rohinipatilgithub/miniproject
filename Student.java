import java.io.*;

public class Student {
    private String User;
    private String Name;
    private String Course;

    public Student(){
    }
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

    public static void createStudent() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the username: ");
            String username = reader.readLine();
            System.out.print("Enter the password: ");
            String password = reader.readLine();
            System.out.print("Enter the user: ");
            String user = reader.readLine();
            System.out.print("Enter the name: ");
            String name = reader.readLine();
            System.out.print("Enter the course: ");
            String course = reader.readLine();

            Student obj = new Student(user, name, course);
            String StudentInfo = obj.getUser() + "," + obj.getName() + "," + obj.getCourse();

            User userObj = new User(username, password, "3");
            String userinfo = userObj.getUsername() + "," + userObj.getPassword() + "," + userObj.getUserType();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("user.txt", true))) {
                writer.newLine();
                writer.write(userinfo);
                System.out.println("Student added successfully in user txt file");
            } catch (IOException e) {
                System.err.println("Error: Unable to add Student in user txt file.");
                e.printStackTrace();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Student.txt", true))) {
                writer.write(StudentInfo);
                writer.newLine();
                System.out.println("Student added Successfully in student txt dile.");
            } catch (IOException e) {
                System.err.println("Error : Unable to add Student in student txt file.");
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void searchStudent(String studentUser) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Student.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(",");
                if (arr.length == 3) {
                    String user = arr[0];
                    String name = arr[1];
                    String course = arr[2];

                    if (name.equalsIgnoreCase(studentUser)) {
                        System.out.println("Student details: ");
                        System.out.println(" User : " + user);
                        System.out.println(" Name : " + name);
                        System.out.println("Course : " + course);
                        System.out.println();
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

    public void printAllStudentDetails() {

        try (BufferedReader reader = new BufferedReader(new FileReader("Student.txt"))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                if (parts.length == 3) {

                    String user = parts[0];

                    String name = parts[1];

                    String course = parts[2];

                    System.out.println("Student User: " + user);

                    System.out.println("Student Name: " + name);

                    System.out.println("Student Course: " + course);

                    System.out.println();

                }

            }

        } catch (FileNotFoundException e) {

            System.err.println("Error: Student file not found.");

            e.printStackTrace();

        } catch (IOException e) {

            System.err.println("Error reading User file.");

            e.printStackTrace();

        }

    }

    public void studentManagementSystem() {
        int count = 0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println("1.Check Attendance");
                System.out.println("2. Print my details");
                System.out.println("3. Exit");

                System.out.println("Enter the No.");
                int input = Integer.parseInt(reader.readLine());

                switch (input) {
                    case 1:
                        // Check attendance function
                        break;

                    case 2:
                        System.out.print("Enter your name: ");
                        String studentUser = reader.readLine();
                        searchStudent(studentUser);
                        break;
                    case 3:
                        count++;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
                if (count> 0){
                    break;}
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

}
