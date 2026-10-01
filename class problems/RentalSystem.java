import java.util.*;

abstract class Vehicle {
    private final String name;
    private final double rate;
    private boolean available = true;

    Vehicle(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    protected double getRate() {
        return rate;
    }

    public abstract double calculateCharge(int days);
}

class Sedan extends Vehicle {
    Sedan(String name, double rate) {
        super(name, rate);
    }

    public double calculateCharge(int days) {
        return getRate() * days;
    }
}

class SUV extends Vehicle {
    SUV(String name, double rate) {
        super(name, rate);
    }

    public double calculateCharge(int days) {
        return getRate() * days + 200;
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

class Rental {
    private final Vehicle vehicle;
    private final Customer customer;
    private boolean active = true;

    Rental(Vehicle vehicle, Customer customer) {
        this.vehicle = vehicle;
        this.customer = customer;
    }

    public boolean matches(Vehicle v, Customer c) {
        return active && vehicle == v && customer == c;
    }

    public void returnVehicle() {
        active = false;
        vehicle.setAvailable(true);
        System.out.println(vehicle.getName()
                + " returned by " + customer.getName());
    }
}

class RentalService {
    private final List<Rental> rentals = new ArrayList<>();

    public void rentVehicle(Vehicle v, Customer c, int days) {
        if (days <= 0) {
            System.out.println("Invalid rental duration.");
            return;
        }

        if (!v.isAvailable()) {
            System.out.println(v.getName() + " is currently unavailable.");
            return;
        }

        v.setAvailable(false);
        rentals.add(new Rental(v, c));

        System.out.println(v.getName() + " rented successfully by "
                + c.getName());
        System.out.println("Rental charge: $"
                + v.calculateCharge(days));
    }

    public void returnVehicle(Vehicle v, Customer c) {
        for (Rental r : rentals) {
            if (r.matches(v, c)) {
                r.returnVehicle();
                return;
            }
        }
        System.out.println("No active rental found.");
    }
}

public class RentalSystem {
    public static void main(String[] args) {
        Vehicle sedan = new Sedan("Sedan A", 50);
        Vehicle suv = new SUV("SUV B", 80);

        Customer c1 = new Customer("Customer 1");
        Customer c2 = new Customer("Customer 2");
        Customer c3 = new Customer("Customer 3");

        RentalService service = new RentalService();

        service.rentVehicle(sedan, c1, 3);
        service.rentVehicle(sedan, c2, 2);
        service.returnVehicle(sedan, c1);
        service.rentVehicle(suv, c3, 5);
    }
}
