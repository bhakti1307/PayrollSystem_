package services;

import models.Employee;
import java.io.*;
import java.util.*;

public class FileHandler {
    private static final String FILE_PATH = "data/employees.txt";

    public static List<Employee> readEmployeesFromFile() {
        ensureFileExists();
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    double salary = Double.parseDouble(parts[2].trim());
                    double taxRate = Double.parseDouble(parts[3].trim());
                    double niRate = Double.parseDouble(parts[4].trim());
                    Employee emp = new Employee(id, name, salary, taxRate, niRate);
                    employees.add(emp);
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
        }
        return employees;
    }

    public static void writeEmployeesToFile(List<Employee> employees) {
        ensureFileExists();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (Employee emp : employees) {
                bw.write(emp.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Error writing to file: " + e.getMessage());
        }
    }

    private static void ensureFileExists() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("❌ Error creating file: " + e.getMessage());
            }
        }
    }
}
