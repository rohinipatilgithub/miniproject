import java.io.*;

public class Admin {
    public User user = new User();
    public Faculty faculty = new Faculty();
    public Student student = new Student();
    public Attendence attendence = new Attendence();
    public String name;

    public Admin() {
    }

    public Admin(User user, String name) {
        this.user = user;
        this.name = name;
    }

    public void printDetails() {
        System.out.println("Admin Details:");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Name: " + name);
    }

    public void adminManagementSystem() {
        int count = 0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
                int input = Integer.parseInt(reader.readLine());

                switch (input) {
                    case 1:
                        faculty.createFaculty();
                        break;

                    case 2:
                        student.createStudent();
                        break;

                    case 3:
                        System.out.print("Enter the name of the faculty: ");
                        String facultyUser = reader.readLine();
                        faculty.searchFaculty(facultyUser);
                        break;
                    case 4:
                        System.out.print("Enter the name of the student: ");
                        String studentUser = reader.readLine();
                        student.searchStudent(studentUser);
                        break;
                    case 5:
                        faculty.printAllFacultyDetails();
                        break;
                    case 6:
                        student.printAllStudentDetails();
                        break;
                    case 7:
                        user.printAllUserData();
                        break;
                    case 8:
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


