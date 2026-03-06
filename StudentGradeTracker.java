import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    double grade;

    Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class StudentGradeTracker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("===== Student Grade Tracker =====");

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        // Input data of student
        for (int i = 0; i < n; i++) {

            System.out.println("\nStudent " + (i + 1));

            System.out.print("Enter student name: ");
            String name = sc.nextLine();

            System.out.print("Enter student grade: ");
            double grade = sc.nextDouble();
            sc.nextLine();

            students.add(new Student(name, grade));
        }

        double highest = students.get(0).grade;
        double lowest = students.get(0).grade;
        double total = 0;

        // Calculate the statistics
        for (Student s : students) {

            total += s.grade;

            if (s.grade > highest) {
                highest = s.grade;
            }

            if (s.grade < lowest) {
                lowest = s.grade;
            }
        }

        double average = total / students.size();

        // Display report
        System.out.println("\n===== Summary Report =====");

        for (Student s : students) {
            System.out.println("Name: " + s.name + " | Grade: " + s.grade);
        }

        System.out.println("\nAverage Score: " + average);
        System.out.println("Highest Score: " + highest);
        System.out.println("Lowest Score: " + lowest);

        System.out.println("\nProgram Finished Successfully!");

        sc.close();
    }
}