import java.time.LocalDate;
import java.util.*;

abstract class Room {
    private final String roomNumber;
    private final double rate;

    Room(String roomNumber, double rate) {
        this.roomNumber = roomNumber;
        this.rate = rate;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public abstract double calculatePrice(long nights);

    protected double getRate() {
        return rate;
    }
}

class StandardRoom extends Room {
    StandardRoom(String number, double rate) {
        super(number, rate);
    }

    public double calculatePrice(long nights) {
        return getRate() * nights;
    }
}

class DeluxeRoom extends Room {
    DeluxeRoom(String number, double rate) {
        super(number, rate);
    }

    public double calculatePrice(long nights) {
        return getRate() * nights + 500;
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

class Reservation {
    private final Room room;
    private final Customer customer;
    private final LocalDate start;
    private final LocalDate end;
    private final LocalDate cancellationDeadline;
    private boolean active = true;

    Reservation(Room room, Customer customer,
                LocalDate start, LocalDate end,
                LocalDate cancellationDeadline) {
        this.room = room;
        this.customer = customer;
        this.start = start;
        this.end = end;
        this.cancellationDeadline = cancellationDeadline;
    }

    public boolean overlaps(LocalDate s, LocalDate e) {
        return active && start.isBefore(e) && s.isBefore(end);
    }

    public boolean belongsTo(Room r) {
        return room == r;
    }

    public boolean belongsTo(Customer c) {
        return customer == c;
    }

    public boolean isActive() {
        return active;
    }

    public boolean cancel(LocalDate today) {
        if (!active || today.isAfter(cancellationDeadline)) {
            return false;
        }

        active = false;
        return true;
    }

    public double getPrice() {
        long nights = java.time.temporal.ChronoUnit.DAYS
                .between(start, end);
        return room.calculatePrice(nights);
    }

    public String getDetails() {
        return customer.getName() + ", " + room.getRoomNumber()
                + " (" + start + " to " + end + ")";
    }
}

class HotelService {
    private final List<Reservation> reservations = new ArrayList<>();

    public boolean isAvailable(Room room, LocalDate start, LocalDate end) {
        if (!start.isBefore(end)) {
            return false;
        }

        for (Reservation r : reservations) {
            if (r.belongsTo(room) && r.overlaps(start, end)) {
                return false;
            }
        }
        return true;
    }

    public void book(Room room, Customer customer,
                     LocalDate start, LocalDate end,
                     LocalDate deadline) {
        if (!isAvailable(room, start, end)) {
            System.out.println(room.getRoomNumber()
                    + " is not available from " + start + " to " + end);
            return;
        }

        Reservation r = new Reservation(
                room, customer, start, end, deadline);
        reservations.add(r);

        System.out.println("Reservation confirmed for "
                + r.getDetails());
        System.out.println("Price: $" + r.getPrice());
    }

    public void cancel(Room room, Customer customer, LocalDate today) {
        for (Reservation r : reservations) {
            if (r.belongsTo(room) && r.belongsTo(customer)
                    && r.isActive()) {
                if (r.cancel(today)) {
                    System.out.println("Reservation for "
                            + r.getDetails()
                            + " cancelled successfully.");
                } else {
                    System.out.println(
                            "Cancellation deadline has passed.");
                }
                return;
            }
        }

        System.out.println("Active reservation not found.");
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {
        Room standard = new StandardRoom("Standard Room 101", 100);
        Room deluxe = new DeluxeRoom("Deluxe Room 201", 200);

        Customer a = new Customer("Customer A");
        Customer b = new Customer("Customer B");
        Customer c = new Customer("Customer C");

        HotelService hotel = new HotelService();

        LocalDate start = LocalDate.of(2027, 1, 1);
        LocalDate end = LocalDate.of(2027, 1, 5);
        LocalDate deadline = LocalDate.of(2026, 12, 25);

        if (hotel.isAvailable(standard, start, end)) {
            System.out.println(
                    "Standard Room 101 is available from "
                    + start + " to " + end);
        }

        hotel.book(standard, a, start, end, deadline);

        hotel.book(standard, b,
                LocalDate.of(2027, 1, 3),
                LocalDate.of(2027, 1, 7), deadline);

        hotel.cancel(standard, a, LocalDate.of(2026, 12, 20));

        hotel.book(deluxe, c,
                LocalDate.of(2027, 2, 10),
                LocalDate.of(2027, 2, 12),
                LocalDate.of(2027, 2, 1));
    }
}

