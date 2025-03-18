package models;

import java.util.List;
import java.util.Optional;

public class Payroll {
    private List<Employee> employees;

    public Payroll(List<Employee> employees) {
        this.employees = employees;
    }

    public Optional<Employee> getEmployeeById(int id) {
        return employees.stream().filter(emp -> emp.getId() == id).findFirst();
    }

    public void recordMonthlyPayment(int id) {
        Optional<Employee> employee = getEmployeeById(id);
        if (employee.isPresent()) {
            employee.get().recordPayment();
            System.out.println("✅ Payment recorded for " + employee.get().getName());
        } else {
            System.out.println("❌ Employee not found.");
        }
    }

    public void displayPaySlip(int id) {
        Optional<Employee> employee = getEmployeeById(id);
        if (employee.isPresent()) {
            Employee emp = employee.get();
            System.out.println("\nPay Slip for Employee: " + emp.getName());
            System.out.println("--------------------------------------");
            System.out.printf("ID: %d\n", emp.getId());
            System.out.printf("Gross Pay: $%.2f\n", emp.getSalary());
            System.out.printf("Tax Deduction: $%.2f\n", emp.calculateTax());
            System.out.printf("National Insurance: $%.2f\n", emp.calculateNI());
            System.out.printf("Net Pay: $%.2f\n", emp.calculateNetPay());

            // Display payment history
            System.out.println("\n📜 Payment History:");
            if (emp.getPaymentHistory().isEmpty()) {
                System.out.println("No payments recorded.");
            } else {
                for (int i = 0; i < emp.getPaymentHistory().size(); i++) {
                    System.out.printf("Month %d: $%.2f\n", (i + 1), emp.getPaymentHistory().get(i));
                }
            }
        } else {
            System.out.println("❌ Employee not found.");
        }
    }
}
