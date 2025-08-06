import java.util.*;
class Food {
    int id;
    String name;
    double price;
    ArrayList<Double> ratings = new ArrayList<>();
    double averageRating = 0.0;
    Food(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    
    void addRating(double rating) {
        ratings.add(rating);
        calculateAverageRating();
    }
    
    void calculateAverageRating() {
        if (ratings.isEmpty()) {
            averageRating = 0.0;
            return;
        }
        double sum = 0;
        for (double rating : ratings) {
            sum += rating;
        }
        averageRating = sum / ratings.size();
    }
    
    String getRatingDisplay() {
        if (ratings.isEmpty()) {
            return "No ratings yet";
        }
        return String.format("%.1f/5 (%d reviews)", averageRating, ratings.size());
    }
}

class CustomFoodRequest {
    String customerName;
    String foodName;
    boolean approved = false;
    double price;
    
    CustomFoodRequest(String customerName, String foodName) {
        this.customerName = customerName;
        this.foodName = foodName;
    }
}

class PaymentRecord {
    int orderId;
    String customerName;
    double amount;
    String method;
    boolean paid;

    PaymentRecord(int orderId, String customerName, double amount, String method) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.amount = amount;
        this.method = method;
        this.paid = true;
    }
}

class Order {
    int orderId;
    String customerName;
    ArrayList<Food> items = new ArrayList<>();
    double total = 0;
    boolean paid = false;
    boolean cancelled = false;
    String status = "Active";

    Order(int orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
    }
    void addItem(Food food) {
        items.add(food);
        total += food.price;
    }    
    boolean removeItem(int foodId) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).id == foodId) {
                Food removedFood = items.remove(i);
                total -= removedFood.price;
                return true;
            }
        }
        return false;
    }
    
    void cancelOrder() {
        cancelled = true;
        status = "Cancelled";
    }
    
    void completeOrder() {
        status = "Completed";
    }
}

class Booking {
    String name, contact;
    int tableNo, members;
    Booking(String name, String contact, int tableNo, int members) {
        this.name = name;
        this.contact = contact;
        this.tableNo = tableNo;
        this.members = members;
    }
}

public class RestaurantSystem {
    ArrayList<Food> menu = new ArrayList<>();
    ArrayList<Order> orders = new ArrayList<>();
    Queue<Order> orderQueue = new LinkedList<>();
    Stack<Order> orderHistory = new Stack<>();
    ArrayList<Booking> bookings = new ArrayList<>();
    ArrayList<PaymentRecord> paymentHistory = new ArrayList<>();
    ArrayList<CustomFoodRequest> foodRequests = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    int orderCounter = 1;
    int nextFoodId = 6; 
    public RestaurantSystem() {
        menu.add(new Food(1, "Biriyani", 150.0));
        menu.add(new Food(2, "Chicken Lollipop", 300.0));
        menu.add(new Food(3, "Pasta", 100.0));
        menu.add(new Food(4, "Egg Rice", 120.0));
        menu.add(new Food(5, "Curd Rice", 60.0));
    }

    Food findFood(int id) {
        for (Food food : menu) {
            if (food.id == id) return food;
        }
        return null;
    }

    void requestCustomFood(String customerName) {
        System.out.print("Enter food name: ");
        String foodName = sc.nextLine();
        for (Food food : menu) {
            if (food.name.equalsIgnoreCase(foodName)) {
                System.out.println("This food item already exists in our menu!");
                return;
            }
        }
        
        CustomFoodRequest newRequest = new CustomFoodRequest(customerName, foodName);
        foodRequests.add(newRequest);
        System.out.println("Your food request for '" + foodName + "' has been submitted. Waiting for admin approval.");
    }

    void searchFood() {
        System.out.print("Enter food ID to search: ");
        int id = sc.nextInt();
        Food food = findFood(id);
        if (food != null) {
            System.out.println("Found: " + food.id + ". " + food.name + " - Rs " + food.price);
        } else {
            System.out.println("Food item not found!");
        }
    }

    void viewAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders yet!");
            return;
        }
        System.out.println("\n=== ALL ORDERS ===");
        for (Order order : orders) {
            String paymentStatus = order.paid ? "[PAID]" : "[PENDING PAYMENT]";
            System.out.printf("Order #%d - %s - Total: Rs %.2f %s [%s]%n", 
                order.orderId, order.customerName, order.total, paymentStatus, order.status);
            for (Food food : order.items) {
                System.out.printf("  - %d. %s - Rs %.2f%n", food.id, food.name, food.price);
            }
            System.out.println();
        }
    }

    void addMenuItem() {
        System.out.print("Enter food ID: ");
        int id = sc.nextInt();
        if (findFood(id) != null) {
            System.out.println("Food item with this ID already exists!");
            return;
        }
        
        sc.nextLine();
        System.out.print("Enter food name: ");
        String name = sc.nextLine();
        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        
        menu.add(new Food(id, name, price));
        System.out.println("Menu item '" + name + "' added successfully!");
    }

    void processOrder() {
        if (orderQueue.isEmpty()) {
            System.out.println("No orders to process!");
            return;
        }
        Order order = orderQueue.poll();
        orderHistory.push(order);
        System.out.println("Processed: Order #" + order.orderId + " - " + order.customerName + " - Total: Rs " + order.total);
    }

    void Payment(Order order) {
        if (order.paid) {
            System.out.println("This order has already been paid for!");
            return;
        }
        
        if (order.cancelled) {
            System.out.println("Cannot process payment for cancelled order!");
            return;
        }

        System.out.println("\n=== PAYMENT DETAILS ===");
        System.out.println("Order #" + order.orderId + " - Customer: " + order.customerName);
        System.out.println("Items ordered:");
        for (Food item : order.items) {
            System.out.println(" - " + item.name + ": Rs " + item.price);
        }
        
        System.out.println("Total Amount: Rs " + order.total);
        System.out.println("\nSelect Payment Method:");
        System.out.println("1. Cash\n2. Card\n3. UPI\n4. Cancel");
        System.out.print("Choose payment method: ");
        
        int choice = sc.nextInt();
        String method = "";
        
        switch (choice) {
            case 1 -> method = "Cash";
            case 2 -> method = "Card";
            case 3 -> method = "UPI";
            case 4 -> {
                System.out.println("Payment canceled.");
                return;
            }
            default -> {
                System.out.println("Invalid payment method!");
                return;
            }
        }

        System.out.print("Enter payment amount: Rs ");
        double amountPaid = sc.nextDouble();
        
        if (amountPaid < order.total) {
            System.out.printf("Insufficient payment! You need Rs %.2f more.%n", (order.total - amountPaid));
            return;
        } else if (amountPaid == order.total) {
            System.out.println("Exact payment received!");
        } else {
            double change = amountPaid - order.total;
            System.out.printf("Payment received! Your change: Rs %.2f%n", change);
        }
        order.paid = true;
        order.completeOrder();
        paymentHistory.add(new PaymentRecord(order.orderId, order.customerName, order.total, method));        
        System.out.println("Payment of Rs " + order.total + " completed via " + method);
        System.out.println("Thank you for your payment! You may now rate your food items.");
        rateOrderItems(order);
    }

    void viewPaymentHistory() {
        if (paymentHistory.isEmpty()) {
            System.out.println("No payments recorded!");
            return;
        }

        System.out.println("\n=== PAYMENT HISTORY ===");
        double totalRevenue = 0;
        for (PaymentRecord record : paymentHistory) {
            System.out.printf("Order #%d - %s - Rs %.2f - %s%n",
                record.orderId, record.customerName, record.amount, record.method);
            totalRevenue += record.amount;
        }
        System.out.printf("Total Revenue: Rs %.2f%n", totalRevenue);
    }

    void showMenu() {
        System.out.println("\n=== MENU ===");
        for (Food food : menu) {
            System.out.printf("%d. %s - Rs %.2f [%s]%n", 
                food.id, food.name, food.price, food.getRatingDisplay());
        }
    }

    void bookTable(String customerName) {
        System.out.print("Contact Number: ");
        String contact = sc.nextLine();
        System.out.print("Table Number: ");
        int tableNo = sc.nextInt();
        for (Booking booking : bookings) {
            if (booking.tableNo == tableNo) {
                System.out.println("Table " + tableNo + " is already booked!");
                return;
            }
        }
        
        System.out.print("Number of Members: ");
        int members = sc.nextInt();
        
        bookings.add(new Booking(customerName, contact, tableNo, members));
        System.out.println("Table " + tableNo + " booked successfully for " + members + " members under name: " + customerName);
    }

    void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No table bookings!");
            return;
        }
        
        System.out.println("\n=== TABLE BOOKINGS ===");
        for (Booking booking : bookings) {
            System.out.printf("Table %d - %s - Contact: %s - Members: %d%n",
                booking.tableNo, booking.name, booking.contact, booking.members);
        }
    }

    void placeOrder(String customerName) {
        Order order = new Order(orderCounter++, customerName);
        
        while (true) {
            showMenu();
            System.out.print("Enter food ID (0 to finish): ");
            String input = sc.nextLine();
            
            if (input.equals("0") || input.toLowerCase().equals("finish")) {
                break;
            }
            
            try {
                int id = Integer.parseInt(input);
                Food food = findFood(id);
                if (food != null) {
                    order.addItem(food);
                    System.out.println("Added: " + food.name + " - Rs " + food.price);
                } else {
                    System.out.println("Food item not found!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid food ID!");
            }
        }
        
        if (!order.items.isEmpty()) {
            orders.add(order);
            orderQueue.add(order);
            System.out.println("\nOrder placed successfully!");
            System.out.printf("Order #%d - %s - Total: Rs %.2f%n", 
                order.orderId, order.customerName, order.total);
        } else {
            System.out.println("No items added to order!");
        }
    }

    Order getCurrentCustomerOrder(String customerName) {
        for (int i = orders.size() - 1; i >= 0; i--) {
            Order order = orders.get(i);
            if (order.customerName.equalsIgnoreCase(customerName) && !order.paid && !order.cancelled) {
                return order;
            }
        }
        return null;
    }
    
    void rateOrderItems(Order order) {
        System.out.println("\n=== RATE YOUR FOOD ITEMS ===");
        System.out.println("Would you like to rate the food items? (yes/no)");
        String response = sc.next().toLowerCase();
        
        if (response.equals("yes")) {
            for (Food food : order.items) {
                boolean validRating = false;
                while (!validRating) {
                    System.out.print("Rate " + food.name + " (1-5 stars): ");
                    try {
                        if (sc.hasNextInt()) {
                            int rating = sc.nextInt();
                            if (rating >= 1 && rating <= 5) {
                                food.addRating(rating);
                                System.out.println("Thank you for rating " + food.name + "!");
                                validRating = true;
                            } else {
                                System.out.println("Invalid rating! Please rate between 1-5.");
                            }
                        } else {
                            String invalidInput = sc.next(); // Clear the invalid input
                            System.out.println("Invalid input! Please enter a whole number between 1-5.");
                        }
                    } catch (Exception e) {
                        sc.next(); // Clear the invalid input
                        System.out.println("Invalid input! Please enter a whole number between 1-5.");
                    }
                }
            }
            System.out.println("Thank you for your feedback!");
        }
    }
    
    void modifyOrder(String customerName) {
        Order order = getCurrentCustomerOrder(customerName);
        if (order == null) {
            System.out.println("No active order found to modify!");
            return;
        }
        
        while (true) {
            System.out.println("\n=== MODIFY ORDER #" + order.orderId + " ===");
            System.out.println("Current items:");
            for (int i = 0; i < order.items.size(); i++) {
                Food food = order.items.get(i);
                System.out.printf("%d. %s - Rs %.2f%n", (i+1), food.name, food.price);
            }
            System.out.printf("Current Total: Rs %.2f%n", order.total);
            
            System.out.println("\nOptions:");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Cancel Entire Order");
            System.out.println("4. Finish Modifications");
            System.out.print("Choose option: ");
            
            int choice = sc.nextInt();
            
            switch (choice) {
                case 1 -> {
                    showMenu();
                    System.out.print("Enter food ID to add: ");
                    int foodId = sc.nextInt();
                    Food food = findFood(foodId);
                    if (food != null) {
                        order.addItem(food);
                        System.out.println("Added: " + food.name);
                    } else {
                        System.out.println("Food item not found!");
                    }
                }
                case 2 -> {
                    if (order.items.isEmpty()) {
                        System.out.println("No items to remove!");
                        break;
                    }
                    System.out.print("Enter item number to remove (1-" + order.items.size() + "): ");
                    int itemNum = sc.nextInt();
                    if (itemNum >= 1 && itemNum <= order.items.size()) {
                        Food removedFood = order.items.remove(itemNum - 1);
                        order.total -= removedFood.price;
                        System.out.println("Removed: " + removedFood.name);
                    } else {
                        System.out.println("Invalid item number!");
                    }
                }
                case 3 -> {
                    System.out.print("Are you sure you want to cancel this order? (yes/no): ");
                    String confirm = sc.next().toLowerCase();
                    if (confirm.equals("yes")) {
                        order.cancelOrder();
                        System.out.println("Order #" + order.orderId + " has been cancelled.");
                        return;
                    }
                }
                case 4 -> {
                    if (order.items.isEmpty()) {
                        System.out.print("Your order is empty. Cancel the order? (yes/no): ");
                        String confirm = sc.next().toLowerCase();
                        if (confirm.equals("yes")) {
                            order.cancelOrder();
                            System.out.println("Order #" + order.orderId + " has been cancelled.");
                        }
                    } else {
                        System.out.println("Order modifications completed!");
                        System.out.printf("Final Total: Rs %.2f%n", order.total);
                    }
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
    
    void removeMenuItem() {
        if (menu.isEmpty()) {
            System.out.println("Menu is empty!");
            return;
        }
        
        showMenu();
        System.out.print("Enter food ID to remove: ");
        int foodId = sc.nextInt();
        
        Food foodToRemove = findFood(foodId);
        if (foodToRemove != null) {
            System.out.print("Are you sure you want to remove '" + foodToRemove.name + "'? (yes/no): ");
            String confirm = sc.next().toLowerCase();
            if (confirm.equals("yes")) {
                menu.remove(foodToRemove);
                System.out.println("Menu item '" + foodToRemove.name + "' removed successfully!");
            } else {
                System.out.println("Removal cancelled.");
            }
        } else {
            System.out.println("Food item not found!");
        }
    }
    
    void viewFoodRatings() {
        if (menu.isEmpty()) {
            System.out.println("No food items in menu!");
            return;
        }
        
        System.out.println("\n=== FOOD RATINGS ===");
        for (Food food : menu) {
            System.out.printf("%s - Rs %.2f%n", food.name, food.price);
            System.out.printf("  Rating: %s%n", food.getRatingDisplay());
            if (!food.ratings.isEmpty()) {
                System.out.print("  Individual ratings: ");
                for (int i = 0; i < food.ratings.size(); i++) {
                    System.out.print(food.ratings.get(i));
                    if (i < food.ratings.size() - 1) System.out.print(", ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    void customerPanel() {
        System.out.print("Enter your name: ");
        sc.nextLine();
        String customerName = sc.nextLine();
        
        while (true) {
            System.out.println("\n=== CUSTOMER PANEL - Welcome " + customerName + " ===");
            System.out.println("1. View Menu");
            System.out.println("2. Book Table");
            System.out.println("3. Place Order");
            System.out.println("4. View My Orders");
            System.out.println("5. Modify Current Order");
            System.out.println("6. Request Custom Food");
            System.out.println("7. Make Payment");
            System.out.println("8. Exit Restaurant");
            System.out.print("Choose option: ");
            
            int ch = sc.nextInt();
            
            switch (ch) {
                case 1 -> showMenu();
                case 2 -> {
                    sc.nextLine(); 
                    bookTable(customerName);
                }
                case 3 -> {
                    sc.nextLine(); 
                    placeOrder(customerName);
                }
                case 4 -> {
                    boolean hasOrders = false;
                    System.out.println("\n=== YOUR ORDERS ===");
                    for (Order order : orders) {
                        if (order.customerName.equalsIgnoreCase(customerName)) {
                            hasOrders = true;
                            String paymentStatus = order.paid ? "[PAID]" : "[PENDING PAYMENT]";
                            System.out.printf("Order #%d - Total: Rs %.2f %s [%s]%n", 
                                order.orderId, order.total, paymentStatus, order.status);
                            for (Food food : order.items) {
                                System.out.printf("  - %s - Rs %.2f%n", food.name, food.price);
                            }
                            System.out.println();
                        }
                    }
                    if (!hasOrders) {
                        System.out.println("You have no orders yet!");
                    }
                }
                case 5 -> modifyOrder(customerName);
                case 6 -> {
                    sc.nextLine(); 
                    requestCustomFood(customerName);
                }
                case 7 -> {
                    Order unpaidOrder = getCurrentCustomerOrder(customerName);
                    if (unpaidOrder != null) {
                        Payment(unpaidOrder);
                    } else {
                        System.out.println("No pending orders found for payment!");
                    }
                }
                case 8 -> {
                    Order unpaidOrder = getCurrentCustomerOrder(customerName);
                    if (unpaidOrder != null) {
                        System.out.println("Please complete payment for Order #" + unpaidOrder.orderId + " before leaving!");
                        System.out.println("Total amount due: Rs " + unpaidOrder.total);
                    } else {
                        System.out.println("Thank you for visiting! Have a great day!");
                        return;
                    }
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    void adminApproval() {
        System.out.print("Enter admin passkey: ");
        int passkey = sc.nextInt();
        if (passkey != 10) {
            System.out.println("Invalid passkey!");
            return;
        }

        if (foodRequests.isEmpty()) {
            System.out.println("No custom food requests.");
            return;
        }        
        System.out.println("\n=== PENDING CUSTOM FOOD REQUESTS ===");
        boolean hasUnapproved = false;        
        for (CustomFoodRequest request : foodRequests) {
            if (!request.approved) {
                hasUnapproved = true;
                System.out.println("Request by " + request.customerName + ": " + request.foodName);
                System.out.print("Approve this item? (yes/no): ");
                String response = sc.next().toLowerCase();
                
                if (response.equals("yes")) {
                    System.out.print("Set price for " + request.foodName + ": Rs ");
                    request.price = sc.nextDouble();
                    request.approved = true;
                    menu.add(new Food(nextFoodId++, request.foodName, request.price));
                    System.out.println(request.foodName + " approved and added to menu at Rs " + request.price);
                } else {
                    System.out.println("Request for " + request.foodName + " rejected.");
                }
            }
        }        
        if (!hasUnapproved) {
            System.out.println("All requests have been processed.");
        }
    }
    void adminPanel() {
        System.out.print("Enter admin passkey: ");
        int passkey = sc.nextInt();
        if (passkey != 10) {
            System.out.println("Invalid passkey!");
            return;
        }
        while (true) {
            System.out.println("\n=== ADMIN PANEL ===");
            System.out.println("1. View All Orders");
            System.out.println("2. Process Order");
            System.out.println("3. Add Menu Item");
            System.out.println("4. Remove Menu Item");
            System.out.println("5. View Menu");
            System.out.println("6. Search Food");
            System.out.println("7. View Payment History");
            System.out.println("8. View Table Bookings");
            System.out.println("9. View Food Ratings");
            System.out.println("10. Back to Main Menu");
            System.out.print("Choose option: ");            
            int ch = sc.nextInt();            
            switch (ch) {
                case 1 -> viewAllOrders();
                case 2 -> processOrder();
                case 3 -> addMenuItem();
                case 4 -> removeMenuItem();
                case 5 -> showMenu();
                case 6 -> searchFood();
                case 7 -> viewPaymentHistory();
                case 8 -> viewBookings();
                case 9 -> viewFoodRatings();
                case 10 -> { return; }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    public static void main(String[] args) {
        RestaurantSystem app = new RestaurantSystem();        
        System.out.println("=== WELCOME TO RESTAURANT MANAGEMENT SYSTEM ===");       
        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Customer Portal");
            System.out.println("2. Admin Panel");
            System.out.println("3. Admin Food Approval");
            System.out.println("4. Exit System");
            System.out.print("Choose option: ");            
            int ch = app.sc.nextInt();            
            switch (ch) {
                case 1 -> app.customerPanel();
                case 2 -> app.adminPanel();
                case 3 -> app.adminApproval();
                case 4 -> {
                    System.out.println("Thank you for using Restaurant Management System!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}