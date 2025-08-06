import java.util.*;

class MenuItem {
    int id;
    String name;
    double price;
    MenuItem left, right;
    public MenuItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

class MenuBST {
    private MenuItem root;
    public void insert(int id, String name, double price) {
        root = insertRecursive(root, new MenuItem(id, name, price));
    }
    private MenuItem insertRecursive(MenuItem root, MenuItem item) {
        if (root == null) return item;
        if (item.id < root.id) root.left = insertRecursive(root.left, item);
        else root.right = insertRecursive(root.right, item);
        return root;
    }
    public void displayMenu() {
        System.out.println("\n=== MENU ===");
        inOrderTraversal(root);
    }
    private void inOrderTraversal(MenuItem root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.printf("ID: %d | Name: %s | Price: $%.2f%n", root.id, root.name, root.price);
            inOrderTraversal(root.right);
        }
    }
}

class Order {
    int orderId;
    String details;
    Order next;
    public Order(int orderId, String details) {
        this.orderId = orderId;
        this.details = details;
    }
}

class OrderQueue {
    private Order front, rear;
    public void placeOrder(int orderId, String details) {
        Order newOrder = new Order(orderId, details);
        if (rear == null) front = rear = newOrder;
        else { rear.next = newOrder; rear = newOrder; }
        System.out.println("Order #" + orderId + " placed.");
    }
    public void processOrder() {
        if (front == null) { System.out.println("No orders to process."); return; }
        System.out.println("Processing Order #" + front.orderId + ": " + front.details);
        front = front.next;
        if (front == null) rear = null;
    }
    public void displayOrders() {
        System.out.println("\n=== CURRENT ORDERS ===");
        Order current = front;
        if (current == null) { System.out.println("No active orders."); return; }
        while (current != null) {
            System.out.printf("Order ID: %d | Details: %s%n", current.orderId, current.details);
            current = current.next;
        }
    }
}

class InventoryManager {
    private HashMap<String, Integer> inventory = new HashMap<>();
    public void addItem(String item, int quantity) {
        inventory.put(item, inventory.getOrDefault(item, 0) + quantity);
    }
    public void consumeItem(String item, int quantity) {
        if (!inventory.containsKey(item) || inventory.get(item) < quantity)
            System.out.println("Insufficient " + item);
        else inventory.put(item, inventory.get(item) - quantity);
    }
    public void displayInventory() {
        System.out.println("\n=== INVENTORY ===");
        for (Map.Entry<String, Integer> entry : inventory.entrySet())
            System.out.println(entry.getKey() + ": " + entry.getValue() + " units");
    }
}

class StaffManager {
    private HashMap<Integer, String> staff = new HashMap<>();
    public void addStaff(int id, String name) {
        staff.put(id, name);
    }
    public void displayStaff() {
        System.out.println("\n=== STAFF ===");
        for (Map.Entry<Integer, String> entry : staff.entrySet())
            System.out.println("ID: " + entry.getKey() + " | Name: " + entry.getValue());
    }
}

public class RestaurantManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuBST menu = new MenuBST();
        OrderQueue orders = new OrderQueue();
        InventoryManager inventory = new InventoryManager();
        StaffManager staff = new StaffManager();
        int choice, orderCounter = 1000;
        while (true) {
            System.out.println("\n===== RESTAURANT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Menu Item");
            System.out.println("2. View Menu");
            System.out.println("3. Place Order");
            System.out.println("4. Process Order");
            System.out.println("5. View Orders");
            System.out.println("6. Add Inventory");
            System.out.println("7. View Inventory");
            System.out.println("8. Add Staff");
            System.out.println("9. View Staff");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter item ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter item name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    menu.insert(id, name, price);
                    break;
                case 2:
                    menu.displayMenu();
                    break;
                case 3:
                    System.out.print("Enter order details (e.g., 1x Burger, 2x Pizza): ");
                    String details = scanner.nextLine();
                    orders.placeOrder(++orderCounter, details);
                    break;
                case 4:
                    orders.processOrder();
                    break;
                case 5:
                    orders.displayOrders();
                    break;
                case 6:
                    System.out.print("Enter item name: ");
                    String item = scanner.nextLine();
                    System.out.print("Enter quantity to add: ");
                    int qty = scanner.nextInt();
                    inventory.addItem(item, qty);
                    break;
                case 7:
                    inventory.displayInventory();
                    break;
                case 8:
                    System.out.print("Enter staff ID: ");
                    int staffId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter staff name: ");
                    String staffName = scanner.nextLine();
                    staff.addStaff(staffId, staffName);
                    break;
                case 9:
                    staff.displayStaff();
                    break;
                case 0:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}