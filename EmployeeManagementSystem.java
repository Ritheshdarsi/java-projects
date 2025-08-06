import java.util.*;
import java.util.stream.*;

class Employee {
    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }

    // Setters
    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee ID: " + id + ", Name: " + name + ", Salary: ₹" + salary;
    }
}

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        // Adding Employees
        employees.add(new Employee(101, "Rithesh", 50000));
        employees.add(new Employee(102, "Anjali", 60000));
        employees.add(new Employee(103, "Vikram", 55000));
        employees.add(new Employee(104, "Priya", 45000));

        // Display all employees
        System.out.println("All Employees:");
        employees.forEach(System.out::println);

        // Filter high earners using Streams
        System.out.println("\nEmployees with salary > ₹50,000:");
        employees.stream()
                 .filter(emp -> emp.getSalary() > 50000)
                 .forEach(System.out::println);

        // Increase salary by 10% for all employees
        employees.forEach(emp -> emp.setSalary(emp.getSalary() * 1.10));

        // Display after increment
        System.out.println("\nEmployees after 10% salary increment:");
        employees.forEach(System.out::println);

        // Sort by name
        System.out.println("\nEmployees sorted by name:");
        employees.stream()
                 .sorted(Comparator.comparing(Employee::getName))
                 .forEach(System.out::println);
    }
}
