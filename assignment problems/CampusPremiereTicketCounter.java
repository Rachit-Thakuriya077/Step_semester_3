
import java.time.LocalDateTime;
import java.util.*;

abstract class Seat {
    private final String id;

    Seat(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract double getPrice();
}

class RegularSeat extends Seat {
    RegularSeat(String id) { super(id); }
    public double getPrice() { return 150; }
}

class PremiumSeat extends Seat {
    PremiumSeat(String id) { super(id); }
    public double getPrice() { return 250; }
}

class ReclinerSeat extends Seat {
    ReclinerSeat(String id) { super(id); }
    public double getPrice() { return 400; }
}

class Customer {
    private final String name;

    Customer(String name) { this.name = name; }

    public String getName() { return name; }
}

class Show {
    private final LocalDateTime startTime;
    private final Set<String> bookedSeats = new HashSet<>();

    Show(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public boolean isAvailable(Seat seat) {
        return !bookedSeats.contains(seat.getId());
    }

    public boolean hasStarted() {
        return !LocalDateTime.now().isBefore(startTime);
    }

    public void reserve(Seat seat) {
        bookedSeats.add(seat.getId());
    }

    public void release(Seat seat) {
        bookedSeats.remove(seat.getId());
    }
}

class Booking {
    private final Customer customer;
    private final Show show;
    private final List<Seat> seats;
    private boolean active = true;

    Booking(Customer customer, Show show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = new ArrayList<>(seats);
    }

    public boolean isActive() {
        return active;
    }

    public void cancel() {
        if (!active) {
            System.out.println("Booking is already cancelled.");
            return;
        }

        if (show.hasStarted()) {
            System.out.println("Cannot cancel after the show starts.");
            return;
        }

        for (Seat seat : seats) {
            show.release(seat);
        }

        active = false;
        System.out.println(customer.getName() + "'s booking cancelled.");
        System.out.println("Seats " + seatNames() + " released.");
    }

    private String seatNames() {
        List<String> names = new ArrayList<>();
        for (Seat seat : seats) names.add(seat.getId());
        return String.join(", ", names);
    }
}

class TicketCounter {
    public Booking book(Customer customer, Show show, Seat... seats) {
        if (seats.length == 0 || seats.length > 6) {
            System.out.println("Booking must contain 1 to 6 seats.");
            return null;
        }

        Set<String> requested = new HashSet<>();

        for (Seat seat : seats) {
            if (!requested.add(seat.getId())) {
                System.out.println("Duplicate seat in booking: "
                        + seat.getId());
                return null;
            }

            if (!show.isAvailable(seat)) {
                System.out.println("Seat " + seat.getId()
                        + " is already booked for this show.");
                return null;
            }
        }

        List<Seat> list = Arrays.asList(seats);

        for (Seat seat : seats) show.reserve(seat);

        Booking booking = new Booking(customer, show, list);

        double total = 0;
        for (Seat seat : seats) total += seat.getPrice();

        List<String> names = new ArrayList<>();
        for (Seat seat : seats) names.add(seat.getId());

        System.out.println("Booking confirmed for " + customer.getName()
                + ": " + String.join(", ", names) + ".");
        System.out.printf("Total: ₹%.2f%n", total);

        return booking;
    }
}

public class CampusPremiereTicketCounter {
    public static void main(String[] args) {
        Show show = new Show(LocalDateTime.now().plusHours(2));
        TicketCounter counter = new TicketCounter();

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Booking b1 = counter.book(asha, show,
                new RegularSeat("A1"),
                new RegularSeat("A2"),
                new PremiumSeat("F5"));

        counter.book(ravi, show, new RegularSeat("A2"));
        counter.book(ravi, show, new ReclinerSeat("R1"));

        if (b1 != null) b1.cancel();

        counter.book(neha, show, new RegularSeat("A2"));
    }
}
