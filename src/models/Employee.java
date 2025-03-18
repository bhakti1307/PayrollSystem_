package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Employee implements Serializable {
    private int id;
    private String name;
    private double salary;
    private double taxRate;
    private double niRate;
    private List<Double> paymentHistory;  // Track monthly payments

    public Employee(int id, String name, double salary, double taxRate, double niRate) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.taxRate = taxRate;
        this.niRate = niRate;
        this.paymentHistory = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public double getNiRate() {
        return niRate;
    }

    public double calculateTax() {
        return Math.round(salary * (taxRate / 100) * 100.0) / 100.0;
    }

    public double calculateNI() {
        return Math.round(salary * (niRate / 100) * 100.0) / 100.0;
    }

    public double calculateNetPay() {
        return Math.round((salary - calculateTax() - calculateNI()) * 100.0) / 100.0;
    }

    public void recordPayment() {
        double netPay = calculateNetPay();
        paymentHistory.add(netPay);
    }

    public List<Double> getPaymentHistory() {
        return paymentHistory;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + salary + "," + taxRate + "," + niRate + "," + paymentHistory.toString();
    }
}
