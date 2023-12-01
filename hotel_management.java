import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class MenuItem {
    int itemId;
    String name;
    double price;
    MenuItem next;

    public MenuItem(int itemId, String name, double price) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.next = null;
    }
}

class Order {
    int orderNumber;
    LinkedList<MenuItem> items;
    String status;

    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
        this.items = new LinkedList<>();
        this.status = "Pending";
    }
}

class Restaurant {
    MenuItem menuHead;
    Queue<Order> orderQueue;
    Stack<Order> preparationStack;
    int orderCounter;

    public Restaurant() {
        this.menuHead = null;
        this.orderQueue = new LinkedList<>();
        this.preparationStack = new Stack<>();
        this.orderCounter = 1;
    }

    public void addMenuItem(String name, double price) {
        int newItemId = menuSize() + 1;
        MenuItem newItem = new MenuItem(newItemId, name, price);
        newItem.next = menuHead;
        menuHead = newItem;
    }

    public void removeMenuItem(int itemId) {
        MenuItem current = menuHead;
        MenuItem prev = null;
        while (current != null) {
            if (current.itemId == itemId) {
                if (prev != null) {
                    prev.next = current.next;
                } else {
                    menuHead = current.next;
                }
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public void displayMenu() {
        MenuItem current = menuHead;
        while (current != null) {
            System.out.println(current.itemId + ". " + current.name + " - $" + current.price);
            current = current.next;
        }
    }

    public void takeOrder(int[] itemIds) {
        Order newOrder = new Order(orderCounter);
        for (int itemId : itemIds) {
            MenuItem item = findMenuItem(itemId);
            if (item != null) {
                newOrder.items.add(item);
            } else {
                System.out.println("Error: Item with ID " + itemId + " not found in the menu.");
                return;
            }
        }
        orderQueue.add(newOrder);
        orderCounter++;
        System.out.println("Order " + newOrder.orderNumber + " taken successfully.");
    }

    public MenuItem findMenuItem(int itemId) {
        MenuItem current = menuHead;
        while (current != null) {
            if (current.itemId == itemId) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void displayOrderQueue() {
        System.out.println("Order Queue:");
        for (Order order : orderQueue) {
            System.out.println("Order " + order.orderNumber + ": " + order.status);
        }
    }

    public void prepareOrders() {
        if (orderQueue.isEmpty()) {
            System.out.println("No orders in the queue.");
            return;
        }

        Order currentOrder = orderQueue.poll();
        System.out.println("Preparing Order " + currentOrder.orderNumber + "...");
        currentOrder.status = "Being Prepared";
        preparationStack.push(currentOrder);
    }

    public void displayPreparationStack() {
        System.out.println("Orders Being Prepared:");
        for (Order order : preparationStack) {
            System.out.println("Order " + order.orderNumber + ": " + order.status);
        }
    }

    private int menuSize() {
        MenuItem current = menuHead;
        int size = 0;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }
}

public class hotel_management {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();

        // Adding menu items
        restaurant.addMenuItem("Burger", 5.99);
        restaurant.addMenuItem("Pizza", 8.99);
        restaurant.addMenuItem("Salad", 3.99);

        // Displaying the menu
        restaurant.displayMenu();

        // Taking orders
        restaurant.takeOrder(new int[]{1, 2});
        restaurant.takeOrder(new int[]{3, 2});

        // Displaying order queue
        restaurant.displayOrderQueue();

        // Preparing orders
        restaurant.prepareOrders();
        restaurant.prepareOrders();

        // Displaying orders being prepared
        restaurant.displayPreparationStack();
    }
}

