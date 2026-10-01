
import java.util.*;

abstract class WashType {
    public abstract int getDuration();
    public abstract double getCharge();
    public abstract String getName();
}

class QuickWash extends WashType {
    public int getDuration() { return 30; }
    public double getCharge() { return 20; }
    public String getName() { return "Quick"; }
}

class NormalWash extends WashType {
    public int getDuration() { return 45; }
    public double getCharge() { return 30; }
    public String getName() { return "Normal"; }
}

class HeavyWash extends WashType {
    public int getDuration() { return 60; }
    public double getCharge() { return 45; }
    public String getName() { return "Heavy"; }
}

class Student {
    private final String name;

    Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class WashingMachine {
    private final String id;
    private WashCycle currentCycle;

    WashingMachine(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isFree() {
        return currentCycle == null;
    }

    public void startWash(Student student, WashType type) {
        if (!isFree()) {
            System.out.println("Machine " + id + " is currently busy.");
            return;
        }

        currentCycle = new WashCycle(student, this, type);

        System.out.println(type.getName() + " wash started on " + id
                + " for " + student.getName() + " ("
                + type.getDuration() + " min).");
        System.out.printf("Charge: ₹%.2f%n", type.getCharge());
    }

    public void completeWash() {
        if (isFree()) {
            System.out.println("Machine " + id + " has no active cycle.");
            return;
        }

        System.out.println(id + " cycle completed.");
        currentCycle = null;
        System.out.println(id + " is now free.");
    }
}

class WashCycle {
    private final Student student;
    private final WashingMachine machine;
    private final WashType type;

    WashCycle(Student student, WashingMachine machine, WashType type) {
        this.student = student;
        this.machine = machine;
        this.type = type;
    }
}

public class HostelLaundryQueue {
    public static void main(String[] args) {
        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        m1.startWash(asha, new QuickWash());
        m1.startWash(ravi, new HeavyWash());
        m2.startWash(ravi, new HeavyWash());

        m1.completeWash();
        m1.startWash(neha, new NormalWash());
    }
}
