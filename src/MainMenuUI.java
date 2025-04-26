import models.Employee;
import models.Payroll;
import services.FileHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MainMenuUI extends JFrame {
    private List<Employee> employees = FileHandler.readEmployeesFromFile();
    private Payroll payroll = new Payroll(employees);

    private JTextArea outputArea;

    public MainMenuUI() {
        setTitle("Employee Payroll System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout());

        // Set the whole window background color to yellow
        getContentPane().setBackground(Color.YELLOW);

        // Heading Label (Centered)
        JLabel headingLabel = new JLabel("Payroll System", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headingLabel.setForeground(Color.BLACK); // Heading text color
        headingLabel.setOpaque(true);
        headingLabel.setBackground(Color.YELLOW); // Make background of heading same as frame
        add(headingLabel, BorderLayout.NORTH);

        // Output Area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel - Centered at bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(Color.YELLOW); // Match the background color

        JButton addButton = new JButton("Add Employee");
        JButton paySlipButton = new JButton("Display Pay Slip");
        JButton recordPaymentButton = new JButton("Record Payment");
        JButton saveExitButton = new JButton("Save and Exit");

        buttonPanel.add(addButton);
        buttonPanel.add(paySlipButton);
        buttonPanel.add(recordPaymentButton);
        buttonPanel.add(saveExitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        addButton.addActionListener(e -> addEmployee());
        paySlipButton.addActionListener(e -> displayPaySlip());
        recordPaymentButton.addActionListener(e -> recordMonthlyPayment());
        saveExitButton.addActionListener(e -> saveAndExit());
    }

    private void addEmployee() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Employee ID:"));
            String name = JOptionPane.showInputDialog(this, "Enter Name:");
            double salary = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Monthly Salary:"));
            double taxRate = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Tax Rate (%):"));
            double niRate = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter NI Rate (%):"));

            employees.add(new Employee(id, name, salary, taxRate, niRate));
            outputArea.append("✅ Employee added: " + name + "\n");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ Invalid input, try again.");
        }
    }

    private void displayPaySlip() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Employee ID:"));
            StringBuilder paySlip = new StringBuilder();
            Employee employee = payroll.getEmployeeById(id).orElse(null);
            if (employee != null) {
                paySlip.append("\nPay Slip for ").append(employee.getName()).append("\n");
                paySlip.append("--------------------------------------\n");
                paySlip.append("ID: ").append(employee.getId()).append("\n");
                paySlip.append(String.format("Gross Pay: $%.2f\n", employee.getSalary()));
                paySlip.append(String.format("Tax Deduction: $%.2f\n", employee.calculateTax()));
                paySlip.append(String.format("National Insurance: $%.2f\n", employee.calculateNI()));
                paySlip.append(String.format("Net Pay: $%.2f\n", employee.calculateNetPay()));

                paySlip.append("\n📜 Payment History:\n");
                if (employee.getPaymentHistory().isEmpty()) {
                    paySlip.append("No payments recorded.\n");
                } else {
                    for (int i = 0; i < employee.getPaymentHistory().size(); i++) {
                        paySlip.append(String.format("Month %d: $%.2f\n", (i + 1), employee.getPaymentHistory().get(i)));
                    }
                }

                outputArea.append(paySlip.toString() + "\n");
            } else {
                outputArea.append("❌ Employee not found.\n");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ Invalid input, try again.");
        }
    }

    private void recordMonthlyPayment() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Employee ID:"));
            payroll.recordMonthlyPayment(id);
            outputArea.append("✅ Payment recorded for employee ID " + id + "\n");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ Invalid input, try again.");
        }
    }

    private void saveAndExit() {
        FileHandler.writeEmployeesToFile(employees);
        JOptionPane.showMessageDialog(this, "✅ Data saved successfully. Exiting...");
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainMenuUI().setVisible(true);
        });
    }
}
