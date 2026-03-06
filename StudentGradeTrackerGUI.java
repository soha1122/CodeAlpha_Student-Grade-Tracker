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

public class StudentGradeTrackerGUI {

    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {

        JFrame frame = new JFrame("Student Grade Tracker");
        frame.setSize(700,550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10,10));

        // ===== TITLE =====
        JLabel title = new JLabel("STUDENT GRADE TRACKER", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        frame.add(title, BorderLayout.NORTH);

        // ===== MAIN PANEL (vertical flow) =====
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        frame.add(mainPanel, BorderLayout.CENTER);

        // ===== INPUT PANEL (LEFT-ALIGNED) =====
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Student Details"));

        // Roll No Panel
        JPanel rollPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel rollLabel = new JLabel("Student Roll Number:");
        JTextField rollField = new JTextField(10);
        rollPanel.add(rollLabel);
        rollPanel.add(rollField);

        // Name Panel
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel("Student Name:");
        JTextField nameField = new JTextField(15);
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        // Grade Panel
        JPanel gradePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel gradeLabel = new JLabel("Student Grade:");
        JTextField gradeField = new JTextField(10);
        gradePanel.add(gradeLabel);
        gradePanel.add(gradeField);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton enterDataBtn = new JButton("Enter Data");
        JButton confirmBtn = new JButton("Confirm");
        enterDataBtn.setPreferredSize(new Dimension(120,30));
        confirmBtn.setPreferredSize(new Dimension(120,30));
        buttonPanel.add(enterDataBtn);
        buttonPanel.add(confirmBtn);

        // Add all sub-panels to input panel
        inputPanel.add(rollPanel);
        inputPanel.add(namePanel);
        inputPanel.add(gradePanel);
        inputPanel.add(buttonPanel);

        mainPanel.add(inputPanel);

        // ===== TABLE =====
        String columns[] = {"Roll No", "Name", "Grade"};
        DefaultTableModel model = new DefaultTableModel(columns,0);
        JTable table = new JTable(model);
        table.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(650,200));
        mainPanel.add(scrollPane);

        // ===== RESULT PANEL =====
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(3,1));
        resultPanel.setBorder(BorderFactory.createTitledBorder("Results"));
        JLabel avgLabel = new JLabel("Average: ");
        JLabel highLabel = new JLabel("Highest: ");
        JLabel lowLabel = new JLabel("Lowest: ");
        resultPanel.add(avgLabel);
        resultPanel.add(highLabel);
        resultPanel.add(lowLabel);
        mainPanel.add(resultPanel);

        // ===== ENTER DATA BUTTON =====
        enterDataBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(rollField.getText().isEmpty() || nameField.getText().isEmpty() || gradeField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame,"Please enter all details");
                } else {
                    JOptionPane.showMessageDialog(frame,"Data ready. Click Confirm to add.");
                }
            }
        });

        // ===== CONFIRM BUTTON =====
        confirmBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int roll = Integer.parseInt(rollField.getText());
                    String name = nameField.getText();
                    double grade = Double.parseDouble(gradeField.getText());

                    Student s = new Student(roll,name,grade);
                    students.add(s);

                    model.addRow(new Object[]{roll,name,grade});

                    rollField.setText("");
                    nameField.setText("");
                    gradeField.setText("");

                    // CALCULATIONS
                    double total=0;
                    double highest=students.get(0).grade;
                    double lowest=students.get(0).grade;

                    for(Student st: students){
                        total += st.grade;
                        if(st.grade>highest) highest = st.grade;
                        if(st.grade<lowest) lowest = st.grade;
                    }
                    double average = total / students.size();

                    avgLabel.setText("Average: "+average);
                    highLabel.setText("Highest: "+highest);
                    lowLabel.setText("Lowest: "+lowest);

                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(frame,"Please enter valid numeric Roll No and Grade");
                }
            }
        });

        frame.setVisible(true);
    }
}