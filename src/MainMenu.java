import models.Employee;
import models.Payroll;
import services.FileHandler;
import java.util.*;

public class MainMenu {
    private static List<Employee> employees = FileHandler.readEmployeesFromFile();
    private static Payroll payroll = new Payroll(employees);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEmployee Payroll System");
            System.out.println("1. Add Employee");
            System.out.println("2. Display Pay Slip");
            System.out.println("3. Record Monthly Payment");
            System.out.println("4. Save and Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    displayPaySlip(scanner);
                    break;
                case 3:
                    recordMonthlyPayment(scanner);
                    break;
                case 4:
                    FileHandler.writeEmployeesToFile(employees);
                    System.out.println("✅ Data saved. Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Invalid choice, please try again.");
            }
        }
    }

    private static void addEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Monthly Salary: ");
        double salary = scanner.nextDouble();
        System.out.print("Enter Tax Rate (%): ");
        double taxRate = scanner.nextDouble();
        System.out.print("Enter NI Rate (%): ");
        double niRate = scanner.nextDouble();

        employees.add(new Employee(id, name, salary, taxRate, niRate));
        System.out.println("✅ Employee added.");
    }

    private static void displayPaySlip(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        payroll.displayPaySlip(id);
    }

    private static void recordMonthlyPayment(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        payroll.recordMonthlyPayment(id);
    }
}
