import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class ToDoListApp {
    static Scanner scanner = new Scanner(System.in);
    static List<String> tasks = new ArrayList<>();
    public static void main(String[] args) {
        boolean running = true;
        System.out.println("===== Welcome to To-Do List Application =====");
        while (running) {
            showMenu();
            int choice = getChoice();
            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    displayTasks();
                    break;
                case 3:
                    removeTask();
                    break;
                case 4:
                    System.out.println("Exiting application. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Add Task");
        System.out.println("2. Display Tasks");
        System.out.println("3. Remove Task (by index or name)");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getChoice() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void addTask() {
        scanner.nextLine();  
        System.out.print("Enter task name: ");
        String task = scanner.nextLine().trim();
        if (!task.isEmpty()) {
            tasks.add(task);
            System.out.println("Task added successfully.");
        } else {
            System.out.println("Task name cannot be empty.");
        }
    }

    private static void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks in the list.");
            return;
        }

        System.out.println("Current Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void removeTask() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to remove.");
            return;
        }

        scanner.nextLine();  
        System.out.print("Enter task number or name to remove: ");
        String input = scanner.nextLine().trim();

        try {
            int index = Integer.parseInt(input);
            if (index > 0 && index <= tasks.size()) {
                String removed = tasks.remove(index - 1);
                System.out.println("Removed task: " + removed);
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            if (tasks.remove(input)) {
                System.out.println("Removed task: " + input);
            } else {
                System.out.println("Task not found.");
            }
        }
    }
}