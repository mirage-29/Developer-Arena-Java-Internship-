import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private String name;
    private int age;
    private String grade;

    public Student(String name, int age, String grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return String.format("%-20s | %-5d | %-5s", name, age, grade);
    }
}

class StudentInformationSystem {
    private List<Student> students;

    public StudentInformationSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(String name, int age, String grade) {
        students.add(new Student(name, age, grade));
        System.out.println("Student '" + name + "' added successfully.\n");
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the system yet.\n");
            return;
        }

        System.out.println("\nSTUDENT INFORMATION SYSTEM");
        System.out.println("=".repeat(45));
        System.out.printf("%-20s | %-5s | %-5s%n", "Name", "Age", "Grade");
        System.out.println("-".repeat(45));
        for (Student s : students) {
            System.out.println(s);
        }
        System.out.println("=".repeat(45) + "\n");
    }
}

public class StudentInfo {
    public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
        StudentInformationSystem sis = new StudentInformationSystem();

        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                try{
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter age: ");
                    int age = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter grade: ");
                    String grade = sc.nextLine();
                    sis.addStudent(name, age, grade);
                    break;
                }catch(Exception e){
                    System.err.println("Input type error");
                }

                case "2":
                    sis.displayStudents();
                    break;

                case "3":
                    System.out.println("Exiting system. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.\n");
            }
        }
    }
}
