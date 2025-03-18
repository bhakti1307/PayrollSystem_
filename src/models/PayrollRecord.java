package models;

import java.io.Serializable;
import java.time.LocalDate;

public class PayrollRecord implements Serializable {
    private int employeeId;
    private LocalDate paymentDate;
    private double grossPay;
    private double tax;
    private double nationalInsurance;
    private double netPay;

    public PayrollRecord(int employeeId, LocalDate paymentDate, double grossPay, double tax, double nationalInsurance) {
        this.employeeId = employeeId;
        this.paymentDate = paymentDate;
        this.grossPay = grossPay;
        this.tax = tax;
        this.nationalInsurance = nationalInsurance;
        this.netPay = grossPay - (tax + nationalInsurance);
    }

    public PayrollRecord(String month, int year, double calculateGrossPay, double calculateTax,
            double calculateNationalInsurance, double calculateNetPay) {
        //TODO Auto-generated constructor stub
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public double getTax() {
        return tax;
    }

    public double getNationalInsurance() {
        return nationalInsurance;
    }

    public double getNetPay() {
        return netPay;
    }

    @Override
    public String toString() {
        return String.format("Employee ID: %d, Date: %s, Gross Pay: $%.2f, Tax: $%.2f, NI: $%.2f, Net Pay: $%.2f",
                employeeId, paymentDate, grossPay, tax, nationalInsurance, netPay);
    }
}
