import java.io.*;

public class StudentAttendanceProgram {
    private User user = new User();
    private Student student = new Student();
    private Faculty faculty = new Faculty();
    private Admin admin = new Admin();

    public void loadCorrespondingMenu(int result) {
        switch (result) {
            case 1:
                admin.adminManagementSystem();
                break;

            case 2:
                faculty.facultyManagementSystem();
                break;

            case 3:
                student.studentManagementSystem();
                break;

            default:
                System.out.println("Error in login !!!");
                break;

        }
    }

    public static int takeInputCredentials()
    {
        User user = new User();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Enter username for login: ");
            String inputUsername = reader.readLine();

            System.out.print("Enter password for login: ");
            String inputPassword = reader.readLine();

            int result = Integer.parseInt(user.login(inputUsername, inputPassword));
            return result;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {

        StudentAttendanceProgram obj = new StudentAttendanceProgram();

        do {
           int result = takeInputCredentials();
            if (result != 0) {
                obj.loadCorrespondingMenu(result);
                break;
            } else {
                System.out.println("Login failed. Please try again.");
            }
        } while (true);
    }
}
