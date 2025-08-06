import java.util.*;

class MenuItem {
    String name;
    double price;
    boolean isCustomizable;

    MenuItem(String name, double price, boolean isCustomizable) {
        this.name = name;
        this.price = price;
        this.isCustomizable = isCustomizable;
    }
}

class Customer {
    String name;
    int loyaltyPoints;

    Customer(String name) {
        this.name = name;
        this.loyaltyPoints = 0;
    }

    void addPoints(int points) {
        loyaltyPoints += points;
    }
}

class Table {
    int tableNumber;
    boolean isAC;
    boolean isBooked;

    Table(int tableNumber, boolean isAC) {
        this.tableNumber = tableNumber;
        this.isAC = isAC;
        this.isBooked = false;
    }
}

class Order {
    Customer customer;
    Table table;
    List<MenuItem> items = new ArrayList<>();
    boolean isPaid = false;

    Order(Customer customer, Table table) {
        this.customer = customer;
        this.table = table;
    }

    void addItem(MenuItem item) {
        items.add(item);
    }

    double getTotal() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.price;
        }
        return total;
    }
}

public class AdvancedRestaurantSystem {
    static Scanner scanner = new Scanner(System.in);
    static List<MenuItem> menu = new ArrayList<>();
    static List<Table> tables = new ArrayList<>();
    static Map<String, Customer> customers = new HashMap<>();
    static List<Order> allOrders = new ArrayList<>();

    public static void main(String[] args) {
        initMenu();
        initTables();

        System.out.println("Login as: 1. Admin  2. Customer");
        int role = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (role == 1) adminPanel();
        else customerPanel();
    }

    static void initMenu() {
        menu.add(new MenuItem("Paneer Butter Masala", 150.0, false));
        menu.add(new MenuItem("Veg Biryani", 120.0, false));
        menu.add(new MenuItem("Ice Cream", 60.0, true));
        menu.add(new MenuItem("Cold Drink", 30.0, false));
    }

    static void initTables() {
        for (int i = 1; i <= 5; i++)
            tables.add(new Table(i, i % 2 == 0)); // even tables are AC
    }

    static void adminPanel() {
        while (true) {
            System.out.println("\n=== Admin Panel ===");
            System.out.println("1. View All Orders");
            System.out.println("2. View Customer Loyalty Points");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> viewAllOrders();
                case 2 -> viewLoyaltyPoints();
                case 3 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void viewAllOrders() {
        for (Order order : allOrders) {
            System.out.println("Order for " + order.customer.name +
                    " (Table " + order.table.tableNumber + "):");
            for (MenuItem item : order.items)
                System.out.println("- " + item.name + ": " + item.price);
            System.out.printf("Total: ₹%.2f | Paid: %s%n",
                    order.getTotal(), order.isPaid ? "Yes" : "No");
        }
    }

    static void viewLoyaltyPoints() {
        for (Customer c : customers.values())
            System.out.println(c.name + " - Loyalty Points: " + c.loyaltyPoints);
    }

    static void customerPanel() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        Customer customer = customers.computeIfAbsent(name, Customer::new);

        Table bookedTable = bookTable();
        if (bookedTable == null) return;

        Order order = new Order(customer, bookedTable);
        takeOrder(order);
        allOrders.add(order);

        System.out.printf("Total Bill: ₹%.2f%n", order.getTotal());
        System.out.print("Enter payment amount: ");
        double payment = scanner.nextDouble();
        if (payment >= order.getTotal()) {
            order.isPaid = true;
            int earnedPoints = (int) order.getTotal() / 100 * 10;
            customer.addPoints(earnedPoints);
            System.out.printf("Payment successful. Loyalty Points earned: %d%n", earnedPoints);
        } else {
            System.out.println("Insufficient payment. Order marked unpaid.");
        }
    }

    static Table bookTable() {
        System.out.print("AC Room? (yes/no): ");
        String acPref = scanner.nextLine();
        boolean wantAC = acPref.equalsIgnoreCase("yes");

        for (Table t : tables) {
            if (!t.isBooked && t.isAC == wantAC) {
                t.isBooked = true;
                System.out.println("Table " + t.tableNumber + " booked.");
                return t;
            }
        }

        System.out.println("No " + (wantAC ? "AC" : "Non-AC") + " tables available.");
        return null;
    }

    static void takeOrder(Order order) {
        while (true) {
            System.out.println("\n--- Menu ---");
            for (int i = 0; i < menu.size(); i++) {
                MenuItem item = menu.get(i);
                System.out.printf("%d. %s - ₹%.2f%s%n", i + 1, item.name, item.price,
                        item.isCustomizable ? " (Customizable)" : "");
            }

            System.out.print("Enter item number to add to order (0 to finish): ");
            int choice = scanner.nextInt();
            if (choice == 0) break;

            if (choice < 1 || choice > menu.size()) {
                System.out.println("Invalid item number.");
                continue;
            }

            MenuItem selected = menu.get(choice - 1);
            order.addItem(selected);
            System.out.println(selected.name + " added to order.");
        }
    }
}
