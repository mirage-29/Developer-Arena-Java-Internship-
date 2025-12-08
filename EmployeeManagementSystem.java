import java.io.*;
import java.util.*;

public class EmployeeManagementSystem {

    private static HashMap<Integer, Employee> employees = new HashMap<>();
    private static final String FILE_NAME = "employees.txt";
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadFromFile();

        while (true) {
            System.out.println("\n===== Employee Management System =====");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Save & Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> viewEmployees();
                case 3 -> updateEmployee();
                case 4 -> deleteEmployee();
                case 5 -> saveAndExit();
                default -> System.out.println("Invalid Input! Try Again.");
            }
        }
    }

    private static void addEmployee() {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (employees.containsKey(id)) {
            System.out.println("Employee with this ID already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Department: ");
        String dept = sc.nextLine();

        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();

        Employee emp = new Employee(id, name, dept, salary);
        employees.put(id, emp);
        System.out.println("Employee Added Successfully!");
    }

    private static void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found!");
            return;
        }

        System.out.println("\n--- Employee List ---");
        employees.values().forEach(emp -> {
            System.out.println("ID: " + emp.getId() +
                               " | Name: " + emp.getName() +
                               " | Dept: " + emp.getDepartment() +
                               " | Salary: " + emp.getSalary());
        });
    }

    private static void updateEmployee() {
        System.out.print("Enter Employee ID to Update: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (!employees.containsKey(id)) {
            System.out.println("Employee not found!");
            return;
        }

        Employee emp = employees.get(id);

        System.out.print("Enter New Name: ");
        emp.setName(sc.nextLine());

        System.out.print("Enter New Department: ");
        emp.setDepartment(sc.nextLine());

        System.out.print("Enter New Salary: ");
        emp.setSalary(sc.nextDouble());

        System.out.println("Employee Updated Successfully!");
    }

    private static void deleteEmployee() {
        System.out.print("Enter Employee ID to Delete: ");
        int id = sc.nextInt();

        if (employees.remove(id) != null) {
            System.out.println("Employee Deleted Successfully!");
        } else {
            System.out.println("Employee not found!");
        }
    }

    private static void saveAndExit() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Employee emp : employees.values()) {
                bw.write(emp.toString());
                bw.newLine();
            }
            System.out.println("Data Saved Successfully. Exiting...");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    private static void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Employee emp = new Employee(
                        Integer.parseInt(data[0]),
                        data[1],
                        data[2],
                        Double.parseDouble(data[3])
                );
                employees.put(emp.getId(), emp);
            }
            System.out.println("Data Loaded Successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found, starting fresh!");
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}
