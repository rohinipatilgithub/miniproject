//package miniproject;

import java.io.*;

class Student {
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

    public static void createStudent(String User, String Name, String Course) {
        Student obj = new Student(User, Name, Course);
        String StudentInfo = obj.getUser() + "," + obj.getName() + "," + obj.getCourse();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Student.txt", true))) {
            writer.write(StudentInfo);
            writer.newLine();
            System.out.println("Student added Successfully.");
        } catch (IOException e) {
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
