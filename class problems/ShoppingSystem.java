import java.util.*;

interface PaymentMethod {
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        System.out.println("Processing Credit Card payment: $" + amount);
        return true;
    }
}

class PayPalPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        System.out.println("Processing PayPal payment: $" + amount);
        return false; // Simulate a failed payment
    }
}

class BankTransferPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        System.out.println("Processing Bank Transfer: $" + amount);
        return true;
    }
}

class Product {
    private final String name;
    private final double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class OrderItem {
    private final Product product;
    private final int quantity;

    OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }
}

class Customer {
    private final String name;

    Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Order {
    private final String orderId;
    private final Customer customer;
    private final List<OrderItem> items = new ArrayList<>();
    private String status = "Pending";

    Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    public void addProduct(Product product, int quantity) {
        if (quantity <= 0) {
            System.out.println("Quantity must be positive.");
            return;
        }

        items.add(new OrderItem(product, quantity));
    }

    public double getTotal() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.getSubtotal();
        }

        return total;
    }

    public void pay(PaymentMethod method) {
        if (items.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }

        if (status.equals("Paid")) {
            System.out.println("Order is already paid.");
            return;
        }

        System.out.println("Payment initiated via "
                + method.getClass().getSimpleName()
                + " for Order " + orderId + ".");

        boolean success = method.processPayment(getTotal());

        if (success) {
            status = "Paid";
            System.out.println("Payment for Order "
                    + orderId + " successful.");
        } else {
            System.out.println("Payment for Order "
                    + orderId + " failed.");
        }

        System.out.println("Order status: " + status);
    }
}

public class ShoppingSystem {
    public static void main(String[] args) {
        Customer x = new Customer("Customer X");
        Customer y = new Customer("Customer Y");
        Customer z = new Customer("Customer Z");

        Product a = new Product("Product A", 100);
        Product b = new Product("Product B", 200);
        Product c = new Product("Product C", 300);

        Order orderX = new Order("X", x);
        orderX.addProduct(a, 2);
        orderX.addProduct(b, 1);
        System.out.println("Order created for Customer X.");
        orderX.pay(new CreditCardPayment());

        System.out.println();

        Order orderY = new Order("Y", y);
        System.out.println("Order created for Customer Y.");
        orderY.pay(new CreditCardPayment());

        System.out.println();

        Order orderZ = new Order("Z", z);
        orderZ.addProduct(c, 1);
        System.out.println("Order created for Customer Z.");
        orderZ.pay(new PayPalPayment());
    }
}

