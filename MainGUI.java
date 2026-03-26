import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainGUI {
    private StudentManagementSystem sms = new StudentManagementSystem();
    private JFrame frame;
    private JTextField nameField, rollField, gradeField;
    private JTable table;
    private DefaultTableModel model;

    public MainGUI() {
        sms.loadFromFile();

        frame = new JFrame("🎓 Student Management System");
        frame.setSize(750, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // ===== TOP PANEL =====
        JPanel topPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        topPanel.setBorder(BorderFactory.createTitledBorder("Student Details"));
        topPanel.setBackground(Color.LIGHT_GRAY);

        nameField = new JTextField();
        rollField = new JTextField();
        gradeField = new JTextField();

        topPanel.add(new JLabel("Name:"));
        topPanel.add(nameField);
        topPanel.add(new JLabel("Roll No:"));
        topPanel.add(rollField);

        topPanel.add(new JLabel("Grade:"));
        topPanel.add(gradeField);

        JButton addBtn = new JButton("Add");
        JButton deleteBtn = new JButton("Delete");

        topPanel.add(addBtn);
        topPanel.add(deleteBtn);

        frame.add(topPanel, BorderLayout.NORTH);

        // ===== TABLE =====
        model = new DefaultTableModel(new String[]{"Name", "Roll No", "Grade"}, 0);
        table = new JTable(model);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== BOTTOM PANEL =====
        JPanel bottomPanel = new JPanel();

        JButton searchBtn = new JButton("Search");
        JButton updateBtn = new JButton("Update");
        JButton showBtn = new JButton("Show All");
        JButton exitBtn = new JButton("Exit");

        bottomPanel.add(searchBtn);
        bottomPanel.add(updateBtn);
        bottomPanel.add(showBtn);
        bottomPanel.add(exitBtn);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        // ===== BUTTON ACTIONS =====

        // ADD
        addBtn.addActionListener(e -> {
            if (!validateInput()) return;

            String name = nameField.getText();
            int roll = Integer.parseInt(rollField.getText());
            String grade = gradeField.getText();

            sms.addStudent(new Student(name, roll, grade));
            sms.saveToFile();
            refreshTable();

            JOptionPane.showMessageDialog(frame, "Student Added!");
            clearFields();
        });

        // DELETE
        deleteBtn.addActionListener(e -> {
            if (rollField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Enter Roll No!");
                return;
            }

            int roll = Integer.parseInt(rollField.getText());
            Student s = sms.searchStudent(roll);

            if (s != null) {
                sms.removeStudent(roll);
                sms.saveToFile();
                refreshTable();

                JOptionPane.showMessageDialog(frame, "Student Deleted!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(frame, "Student Not Found!");
            }
        });

        // SEARCH
        searchBtn.addActionListener(e -> {
            if (rollField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Enter Roll No!");
                return;
            }

            int roll = Integer.parseInt(rollField.getText());
            Student s = sms.searchStudent(roll);

            if (s != null) {
                nameField.setText(s.getName());
                gradeField.setText(s.getGrade());
            } else {
                JOptionPane.showMessageDialog(frame, "Student Not Found!");
            }
        });

        // UPDATE
        updateBtn.addActionListener(e -> {
            if (!validateInput()) return;

            int roll = Integer.parseInt(rollField.getText());
            Student s = sms.searchStudent(roll);

            if (s != null) {
                s.setName(nameField.getText());
                s.setGrade(gradeField.getText());
                sms.saveToFile();
                refreshTable();

                JOptionPane.showMessageDialog(frame, "Updated Successfully!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(frame, "Student Not Found!");
            }
        });

        // SHOW ALL
        showBtn.addActionListener(e -> refreshTable());

        // EXIT
        exitBtn.addActionListener(e -> System.exit(0));

        // TABLE CLICK AUTO-FILL
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.getSelectedRow();
                nameField.setText(model.getValueAt(row, 0).toString());
                rollField.setText(model.getValueAt(row, 1).toString());
                gradeField.setText(model.getValueAt(row, 2).toString());
            }
        });

        frame.setVisible(true);
    }

    // ===== VALIDATION =====
    private boolean validateInput() {
        if (nameField.getText().isEmpty() ||
            rollField.getText().isEmpty() ||
            gradeField.getText().isEmpty()) {

            JOptionPane.showMessageDialog(frame, "All fields are required!");
            return false;
        }

        try {
            Integer.parseInt(rollField.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Roll No must be number!");
            return false;
        }

        return true;
    }

    // ===== TABLE REFRESH =====
    private void refreshTable() {
        model.setRowCount(0);
        for (Student s : sms.getAllStudents()) {
            model.addRow(new Object[]{
                s.getName(),
                s.getRollNo(),
                s.getGrade()
            });
        }
    }

    // ===== CLEAR FIELDS =====
    private void clearFields() {
        nameField.setText("");
        rollField.setText("");
        gradeField.setText("");
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}