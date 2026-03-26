import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GradeCalculatorGUI {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Grade Calculator");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Grade Calculator", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.BLUE);
        frame.add(title, BorderLayout.NORTH);

        // Panel for inputs
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField sub1 = new JTextField();
        JTextField sub2 = new JTextField();
        JTextField sub3 = new JTextField();
        JTextField sub4 = new JTextField();
        JTextField sub5 = new JTextField();

        panel.add(new JLabel("Subject 1:")); panel.add(sub1);
        panel.add(new JLabel("Subject 2:")); panel.add(sub2);
        panel.add(new JLabel("Subject 3:")); panel.add(sub3);
        panel.add(new JLabel("Subject 4:")); panel.add(sub4);
        panel.add(new JLabel("Subject 5:")); panel.add(sub5);

        JButton calculateBtn = new JButton("Calculate");
        panel.add(calculateBtn);

        frame.add(panel, BorderLayout.CENTER);

        // Result Area
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.BOLD, 14));
        resultArea.setBackground(new Color(240, 248, 255));
        frame.add(resultArea, BorderLayout.SOUTH);

        // Button Action
        calculateBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int m1 = Integer.parseInt(sub1.getText());
                    int m2 = Integer.parseInt(sub2.getText());
                    int m3 = Integer.parseInt(sub3.getText());
                    int m4 = Integer.parseInt(sub4.getText());
                    int m5 = Integer.parseInt(sub5.getText());

                    int total = m1 + m2 + m3 + m4 + m5;
                    double average = total / 5.0;

                    String grade;
                    if (average >= 90) grade = "A+";
                    else if (average >= 80) grade = "A";
                    else if (average >= 70) grade = "B";
                    else if (average >= 60) grade = "C";
                    else if (average >= 50) grade = "D";
                    else grade = "F";

                    resultArea.setText("Total Marks: " + total + "\n" +
                            "Average Percentage: " + average + "%\n" +
                            "Grade: " + grade);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numbers!");
                }
            }
        });

        frame.setVisible(true);
    }
}
