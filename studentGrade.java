import java.util.*;

public class studentGrade {
    static ArrayList<Student> list = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n1. Add new Student Data");
            System.out.println("2. View all students data");
            System.out.println("3. View any specific Student Data");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input!");
                continue;
            }

            if (choice == 4) {
                System.out.println("Exiting...");
                break;
            }

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAllStudents();
                case 3 -> searchStudent();
                default -> System.out.println("Invalid Input");
            }
        }

        sc.close();
    }

    // ============================
    // Add Student
    // ============================
    static void addStudent() {
        System.out.println("\nEnter Student Details");
        System.out.print("Name : ");
        String name = sc.nextLine();

        try {
            System.out.print("Marks in English : ");
            int eng = Integer.parseInt(sc.nextLine());

            System.out.print("Marks in Hindi : ");
            int hin = Integer.parseInt(sc.nextLine());

            System.out.print("Marks in Maths : ");
            int math = Integer.parseInt(sc.nextLine());

            System.out.print("Marks in Science : ");
            int sci = Integer.parseInt(sc.nextLine());

            if(eng < 0 || eng > 100 || hin < 0 || hin > 100 || math < 0 || math > 100 || sci < 0 || sci > 100) {
                System.out.println("Marks should be between 0 and 100!");
                return;
            }

            list.add(new Student(name, eng, hin, math, sci));
            System.out.println("Student added successfully.");
        }
        catch (Exception e) {
            System.out.println("Invalid input for marks!");
        }
    }

    // ============================
    // View All Students
    // ============================
    static void viewAllStudents() {
        if (list.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }

        System.out.println("\nSelect display mode:");
        System.out.println("1. Marks View");
        System.out.println("2. Grades View");
        System.out.print("Enter choice: ");
        int mode = Integer.parseInt(sc.nextLine());

        boolean showGrades = (mode == 2);

        System.out.printf(
            "\n%-10s %-20s %-12s %-12s %-12s %-12s %-10s %-10s\n",
            "S.No", "Name", "English", "Hindi", "Maths", "Science", "Average", (showGrades ? "Grade" : " ")
        );

        int i = 1;
        for (Student st : list) {
            if (showGrades) {
                System.out.printf(
                    "%-10d %-20s %-12s %-12s %-12s %-12s %-10.2f %-10c\n",
                    i, st.name,
                    st.getGrade(st.english),
                    st.getGrade(st.hindi),
                    st.getGrade(st.maths),
                    st.getGrade(st.science),
                    st.average,
                    st.grade
                );
            } else {
                System.out.printf(
                    "%-10d %-20s %-12d %-12d %-12d %-12d %-10.2f %-10c\n",
                    i, st.name,
                    st.english, st.hindi, st.maths, st.science,
                    st.average, st.grade
                );
            }
            i++;
        }
    }

    // ============================
    // Search Student
    // ============================
    static void searchStudent() {
        System.out.print("\nEnter name of Student: ");
        String name = sc.nextLine();

        for (Student st : list) {
            if (st.name.equalsIgnoreCase(name)) {

                System.out.println("\nStudent Found:");
                System.out.println("Name: " + st.name);
                System.out.println("English: " + st.english);
                System.out.println("Hindi: " + st.hindi);
                System.out.println("Maths: " + st.maths);
                System.out.println("Science: " + st.science);
                System.out.println("Average: " + st.average);
                System.out.println("Grade: " + st.grade);

                return;
            }
        }

        System.err.println("Record not found");
    }
}



// =====================================================
// Student Class
// =====================================================
class Student {
    String name;
    int english, hindi, maths, science;
    double average;
    char grade;

    Student(String name, int english, int hindi, int maths, int science) {
        this.name = name;
        this.english = english;
        this.hindi = hindi;
        this.maths = maths;
        this.science = science;

        this.average = calculateAverage();
        this.grade = calculateGrade(average);
    }

    // ----------- Calculate Average -----------
    double calculateAverage() {
        return (english + hindi + maths + science) / 4.0;
    }

    // ----------- Grade Calculation (School Style) -----------
    char calculateGrade(double avg) {
        if (avg >= 90) return 'A';
        else if (avg >= 80) return 'B';
        else if (avg >= 70) return 'C';
        else if (avg >= 60) return 'D';
        else if (avg >= 50) return 'E';
        else return 'F';
    }

    // ----------- Grade Calculation for individual subjects -----------
    char getGrade(int marks) {
        if (marks >= 90) return 'A';
        else if (marks >= 80) return 'B';
        else if (marks >= 70) return 'C';
        else if (marks >= 60) return 'D';
        else if (marks >= 50) return 'E';
        else return 'F';
    }
}
