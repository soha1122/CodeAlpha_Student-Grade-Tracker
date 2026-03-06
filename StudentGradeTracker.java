import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Student {
    int roll;
    String name;
    double grade;

    Student(int roll, String name, double grade) {
        this.roll = roll;
        this.name = name;
        this.grade = grade;
    }
}

public class StudentGradeTracker {

    static int rollNumber = 1;
    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {

        JFrame frame = new JFrame("Student Grade Tracker");
        frame.setSize(600,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // ===== TITLE =====
        JLabel title = new JLabel("STUDENT GRADE TRACKER", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        frame.add(title, BorderLayout.NORTH);

        // ===== INPUT PANEL =====
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,2,10,10));

        JLabel rollLabel = new JLabel("Student Roll Number:");
        JLabel rollValue = new JLabel(String.valueOf(rollNumber));

        JLabel nameLabel = new JLabel("Student Name:");
        JTextField nameField = new JTextField();

        JLabel gradeLabel = new JLabel("Student Grade:");
        JTextField gradeField = new JTextField();

        JButton enterDataBtn = new JButton("Enter Data");
        JButton confirmBtn = new JButton("Confirm");

        panel.add(rollLabel);
        panel.add(rollValue);

        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(gradeLabel);
        panel.add(gradeField);

        panel.add(new JLabel());
        panel.add(enterDataBtn);

        panel.add(new JLabel());
        panel.add(confirmBtn);

        frame.add(panel, BorderLayout.WEST);

        // ===== TABLE =====
        String columns[] = {"Roll No", "Name", "Grade"};
        DefaultTableModel model = new DefaultTableModel(columns,0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(scrollPane, BorderLayout.CENTER);

        // ===== RESULT PANEL =====
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(3,1));

        JLabel avgLabel = new JLabel("Average: ");
        JLabel highLabel = new JLabel("Highest: ");
        JLabel lowLabel = new JLabel("Lowest: ");

        resultPanel.add(avgLabel);
        resultPanel.add(highLabel);
        resultPanel.add(lowLabel);

        frame.add(resultPanel, BorderLayout.SOUTH);

        // ===== ENTER DATA BUTTON =====
        enterDataBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(nameField.getText().isEmpty() || gradeField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame,"Please enter all data");
                } else {
                    JOptionPane.showMessageDialog(frame,"Data ready. Click Confirm to add.");
                }
            }
        });

        // ===== CONFIRM BUTTON =====
        confirmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {

                    String name = nameField.getText();
                    double grade = Double.parseDouble(gradeField.getText());

                    Student s = new Student(rollNumber,name,grade);
                    students.add(s);

                    model.addRow(new Object[]{rollNumber,name,grade});

                    rollNumber++;
                    rollValue.setText(String.valueOf(rollNumber));

                    nameField.setText("");
                    gradeField.setText("");

                    // CALCULATIONS
                    double total=0;
                    double highest=students.get(0).grade;
                    double lowest=students.get(0).grade;

                    for(Student st: students){

                        total += st.grade;

                        if(st.grade>highest)
                            highest = st.grade;

                        if(st.grade<lowest)
                            lowest = st.grade;
                    }

                    double average = total / students.size();

                    avgLabel.setText("Average: "+average);
                    highLabel.setText("Highest: "+highest);
                    lowLabel.setText("Lowest: "+lowest);

                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(frame,"Enter valid grade");
                }
            }
        });

        frame.setVisible(true);
    }
}