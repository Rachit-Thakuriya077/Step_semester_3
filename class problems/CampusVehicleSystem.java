
import java.util.*;

interface Chargeable {
    void bookChargingBay();
}

abstract class Vehicle {
    protected String passNumber;
    protected String owner;

    Vehicle(String passNumber, String owner) {
        this.passNumber = passNumber;
        this.owner = owner;
    }

    abstract int getPassFee();
    abstract String getType();

    void printPass() {
        System.out.println(passNumber + " (" + getType()
                + ") pass fee " + getPassFee());
    }
}

class Bike extends Vehicle {
    Bike(String passNumber, String owner) {
        super(passNumber, owner);
    }

    @Override
    int getPassFee() {
        return 300;
    }

    @Override
    String getType() {
        return "Bike";
    }
}

class Car extends Vehicle {
    Car(String passNumber, String owner) {
        super(passNumber, owner);
    }

    @Override
    int getPassFee() {
        return 1000;
    }

    @Override
    String getType() {
        return "Car";
    }
}

class EBike extends Vehicle implements Chargeable {
    EBike(String passNumber, String owner) {
        super(passNumber, owner);
    }

    @Override
    int getPassFee() {
        return 300;
    }

    @Override
    String getType() {
        return "EBike";
    }

    @Override
    public void bookChargingBay() {
        System.out.println(passNumber + " charging bay allotted");
    }
}

class ECar extends Vehicle implements Chargeable {
    ECar(String passNumber, String owner) {
        super(passNumber, owner);
    }

    @Override
    int getPassFee() {
        return 1000;
    }

    @Override
    String getType() {
        return "ECar";
    }

    @Override
    public void bookChargingBay() {
        System.out.println(passNumber + " charging bay allotted");
    }
}

public class CampusVehicleSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Vehicle> vehicles = new HashMap<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\s+");

            if (parts[0].equals("PASS")) {
                String type = parts[1];
                String passNumber = parts[2];
                String owner = parts[3];

                Vehicle vehicle = null;

                switch (type) {
                    case "Bike":
                        vehicle = new Bike(passNumber, owner);
                        break;
                    case "Car":
                        vehicle = new Car(passNumber, owner);
                        break;
                    case "EBike":
                    case "E-Bike":
                        vehicle = new EBike(passNumber, owner);
                        break;
                    case "ECar":
                    case "E-Car":
                        vehicle = new ECar(passNumber, owner);
                        break;
                    default:
                        System.out.println("Invalid vehicle type");
                }

                if (vehicle != null) {
                    vehicles.put(passNumber, vehicle);
                    vehicle.printPass();
                }
            } else if (parts[0].equals("CHARGE")) {
                String passNumber = parts[1];
                Vehicle vehicle = vehicles.get(passNumber);

                if (vehicle == null) {
                    System.out.println("Vehicle not found");
                } else if (vehicle instanceof Chargeable) {
                    ((Chargeable) vehicle).bookChargingBay();
                } else {
                    System.out.println(passNumber
                            + " rejected: charging unsupported");
                }
            }
        }

        sc.close();
    }
}
