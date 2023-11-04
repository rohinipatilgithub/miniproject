package miniproject;
import java.io.*;


class Attendence3 implements Serializable {
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

    
        attendance.markAttendance(student1, "2023-11-04", "present", "DSA");
        attendance.markAttendance(student2, "2023-11-04", "absent", "JAVA");

     
        attendance.showPresenties();
        attendance.showAbsentees();

         attendance.checkAttendance(student1);
    }
}
 