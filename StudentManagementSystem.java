import java.io.*;
import java.util.ArrayList;

public class StudentManagementSystem {
    private ArrayList<Student> students = new ArrayList<>();
    private final String FILE_NAME = "students.dat";

    public void addStudent(Student s) {
        students.add(s);
    }

    public void removeStudent(int rollNo) {
        System.out.println("Deleting Roll No: " + rollNo); // debug
        students.removeIf(s -> s.getRollNo() == rollNo);
    }

    public Student searchStudent(int rollNo) {
        for (Student s : students) {
            if (s.getRollNo() == rollNo) return s;
        }
        return null;
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (ArrayList<Student>) ois.readObject();
        } catch (Exception e) {
            System.out.println("No previous data found.");
        }
    }
}