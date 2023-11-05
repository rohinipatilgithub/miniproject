package miniproject;
import java.io.*;
import miniproject.Student;
import java.util.Date;

class Attendence3 implements Serializable
{
    private Node presentList;
    private Node absentList;

    public Attendence3() 
    {
        presentList = null;
        absentList = null;
    }

    public void markAttendance(Student student, String date, String attendanceStatus, String subject)
    {
        AttendanceRecord record = new AttendanceRecord(student, date, attendanceStatus, subject);
        Node newNode = new Node(record);

        if (attendanceStatus.equalsIgnoreCase("present")) 
        {
            newNode.next = presentList;
            presentList = newNode;
        } else if (attendanceStatus.equalsIgnoreCase("absent")) {
            newNode.next = absentList;
            absentList = newNode;
        } else {
            System.out.println("Invalid attendance status. Please use 'Present' or 'Absent'.");
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
        System.out.println("Attendance for " + student.getStudentName() + " (" + student.getStudentID() + "):");
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

    public static class Student {
        private String studentName;
        private String studentID;

        public Student(String name, String id) {
            this.studentName = name;
            this.studentID = id;
        }

        public String getStudentName() {
            return studentName;
        }

        public String getStudentID() {
            return studentID;
        }
    }

    public static class AttendanceRecord {
        private Student student;
        private String date;
        private String attendanceStatus;
        private String subject;

        public AttendanceRecord(Student student, String date, String attendanceStatus, String subject) {
            this.student = student;
            this.date = date;
            this.attendanceStatus = attendanceStatus;
            this.subject = subject;
        }

        public Student getStudent() {
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
            return "Date: " + date + ", Student: " + student.getStudentName() + " (" + student.getStudentID() + "), Attendance: " + attendanceStatus + ", Subject: " + subject;
        }
    }

    public static void main(String[] args) {
  
        Attendence3 attendance = new Attendence3();

    
        Student student1 = new Student("Rohini", "123");
        Student student2 = new Student("Bhagya", "456");
        Student student3 = new Student("Pratik", "116");
        Student student4 = new Student("Krushna", "789");
        Student student5 = new Student("Bhupendra", "471");

        attendance.markAttendance(student1, "11/4/2023", "present", "DSA");
        attendance.markAttendance(student2, "10/4/2023", "absent", "JAVA");
        attendance.markAttendance(student3, "12/4/2023", "present", "OOP");
        attendance.markAttendance(student4, "13/4/2023", "absent", "OS");
        attendance.markAttendance(student5, "14/4/2023", "absent", "DBMS");
       
        attendance.showPresenties();
        attendance.showAbsentees();

        attendance.checkAttendance(student1);
    }
}



	


