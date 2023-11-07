import java.io.*;

public class Faculty {
    private String user;
    private String name;
    private String subject;

    public Student student =  new Student();
    public Attendence attendance = new Attendence();

    public Faculty() {
    }
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

    public static void createFaculty() {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the username: ");
            String username = reader.readLine();
            System.out.print("Enter the password: ");
            String password = reader.readLine();
            System.out.print("Enter the user: ");
            String user = reader.readLine();
            System.out.print("Enter the Name: ");
            String name = reader.readLine();
            System.out.print("Enter the Subject: ");
            String subject = reader.readLine();

            Faculty faculty = new Faculty(user, name, subject);
            String facultyInfo = faculty.getUser() + "," + faculty.getName() + "," + faculty.getSubject();

            User userObj = new User(username, password, "2");
            String userinfo = userObj.getUsername() + "," + userObj.getPassword() + "," + userObj.getUserType();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("user.txt", true))) {
                writer.newLine();
                writer.write(userinfo);
                System.out.println("Faculty details added successfully in user file.");
            } catch (IOException e) {
                System.err.println("Error: Unable to add faculty details to the user txt file.");
                e.printStackTrace();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("faculties.txt", true))) {
                writer.write(facultyInfo);
                writer.newLine();
                System.out.println("Faculty added successfully.");
            } catch (IOException e) {
                System.err.println("Error: Unable to add faculty to the faculty txt file.");
                e.printStackTrace();
            }
        }
        catch(IOException e)
        {
           e.printStackTrace();
        }

    }

    public void searchFaculty(String facultyUser) {
        try (BufferedReader reader = new BufferedReader(new FileReader("faculties.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String user = parts[0];
                    String name = parts[1];
                    String subject = parts[2];

                    if (name.equalsIgnoreCase(facultyUser)) {
                        System.out.println("Faculty details: ");
                        System.out.println("Faculty User: " + user);
                        System.out.println("Faculty Name: " + name);
                        System.out.println("Faculty Subject: " + subject);
                        System.out.println();
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

    public void printAllFacultyDetails() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Faculties.txt"))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                if (parts.length == 3) {

                    String user = parts[0];
                    String name = parts[1];
                    String subject = parts[2];

                    System.out.println("Faculty User: " + user);
                    System.out.println("Faculty Name: " + name);
                    System.out.println("Faculty Subject: " + subject);
                    System.out.println();
                }
            }

        } catch (FileNotFoundException e) {

            System.err.println("Error: Faculty file not found.");

            e.printStackTrace();

        } catch (IOException e) {

            System.err.println("Error reading User file.");

            e.printStackTrace();

        }

    }

    public void facultyManagementSystem() {
        int count = 0;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println("1. Mark Student Attendance");
                System.out.println("2. Print details of all Absentees");
                System.out.println("3. Print details of Present Students");
                System.out.println("4. Search a Student");
                System.out.println("5. Print Details of all Students");
                System.out.println("6. Exit");

                System.out.println("Enter the No.");
                int input = Integer.parseInt(reader.readLine());

                switch (input) {
                    case 1:
                       attendance.markAttendance();
                        break;

                    case 2:
                       attendance.showAbsentees();
                        break;

                    case 3:
                       attendance.showPresenties();
                        break;
                    case 4:
                        System.out.print("Enter the name of the student: ");
                        String studentUser = reader.readLine();
                        student.searchStudent(studentUser);
                        break;
                    case 5:
                        student.printAllStudentDetails();
                        break;
                    case 6:
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