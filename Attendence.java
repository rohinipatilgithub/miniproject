import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Attendence {
    private Node presentList;
    private Node absentList;

    public Attendence() {
        presentList = null;
        absentList = null;
    }

   // private Student student = new Student();
    public void markAttendance() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Enter the Student name: ");
            String username = reader.readLine();

            StudentAttendance studentObj =  new StudentAttendance(username);

            System.out.print("Enter the attendance date: ");
            String date = reader.readLine();
            System.out.print("Enter the attendanceStatus (present / absent): ");
            String attendanceStatus = reader.readLine();
            System.out.print("Enter the subject: ");
            String subject = reader.readLine();

            AttendanceRecord record = new AttendanceRecord(studentObj, date, attendanceStatus, subject);
            Node newNode = new Node(record);

            if (attendanceStatus.equalsIgnoreCase("present")) {
                newNode.next = presentList;
                presentList = newNode;
            } else if (attendanceStatus.equalsIgnoreCase("absent")) {
                newNode.next = absentList;
                absentList = newNode;
            } else {
                System.out.println("Invalid attendance status. Please use 'Present' or 'Absent'.");
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }

    public void showAbsentees() {
        System.out.println("List of Absent Students:");
        printAttendanceList(absentList);
    }

    public void showPresenties() {
        System.out.println("List of Present Students:");
        printAttendanceList(presentList);
    }

    public void checkAttendance(Student student) {
        System.out.println("Attendance for " + student.getName() +" : ");
        findAndPrintStudentAttendance(student, presentList);
        findAndPrintStudentAttendance(student, absentList);
    }

    private void printAttendanceList(Node list) {
        while (list != null) {
            System.out.println(list.data);
            list = list.next;
        }
    }

    private void findAndPrintStudentAttendance(Student student, Node list) {
        while (list != null) {
            if (list.data.getStudent().equals(student)) {
                System.out.println(list.data);
            }
            list = list.next;
        }
    }

    private class Node {
        AttendanceRecord data;
        Node next;

        public Node(AttendanceRecord data) {
            this.data = data;
            this.next = null;
        }
    }

    public class AttendanceRecord {
        private StudentAttendance student;
        private String date;
        private String attendanceStatus;
        private String subject;

        public AttendanceRecord(StudentAttendance student, String date, String attendanceStatus, String subject) {
            this.student = student;
            this.date = date;
            this.attendanceStatus = attendanceStatus;
            this.subject = subject;
        }

        public StudentAttendance getStudent() {
            return student;
        }

        public String getDate() {
            return date;
        }

        public String getAttendanceStatus() {
            return attendanceStatus;
        }

        public String getSubject() {
            return subject;
        }

        @Override
        public String toString() {
            return "Date: " + date + ", Student: " + student.studentName + ", Attendance: " + attendanceStatus + ", Subject: " + subject;
        }
    }

    public class StudentAttendance {
        private String studentName;

        public StudentAttendance(String name) {
            this.studentName = name;
        }

        public String getStudentName() {
            return studentName;
        }
    }
}

