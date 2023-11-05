package faculty;

import java.io.*;

public class Faculty {
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
