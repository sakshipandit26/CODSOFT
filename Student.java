import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private int rollNo;
    private String grade;

    public Student(String name, int rollNo, String grade) {
        this.name = name;
        this.rollNo = rollNo;
        this.grade = grade;
    }

    public String getName() { return name; }
    public int getRollNo() { return rollNo; }
    public String getGrade() { return grade; }

    public void setName(String name) { this.name = name; }
    public void setGrade(String grade) { this.grade = grade; }
}