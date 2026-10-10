
import java.util.*;

abstract class Staff {
    protected String name;

    Staff(String name) {
        this.name = name;
    }

    abstract long calculatePay();
    abstract String getType();

    Payslip generatePayslip() {
        return new Payslip(name, getType(), calculatePay());
    }
}

class FullTime extends Staff {
    private long salary;

    FullTime(String name, long salary) {
        super(name);
        this.salary = salary;
    }

    @Override
    long calculatePay() {
        return salary;
    }

    @Override
    String getType() {
        return "FullTime";
    }
}

class PartTime extends Staff {
    private long hours;
    private long rate;

    PartTime(String name, long hours, long rate) {
        super(name);
        this.hours = hours;
        this.rate = rate;
    }

    @Override
    long calculatePay() {
        return hours * rate;
    }

    @Override
    String getType() {
        return "PartTime";
    }
}

class Intern extends Staff {
    private long stipend;

    Intern(String name, long stipend) {
        super(name);
        this.stipend = stipend;
    }

    @Override
    long calculatePay() {
        return stipend;
    }

    @Override
    String getType() {
        return "Intern";
    }
}

class Payslip {
    private String name;
    private String type;
    private long pay;

    Payslip(String name, String type, long pay) {
        this.name = name;
        this.type = type;
        this.pay = pay;
    }

    long getPay() {
        return pay;
    }

    String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Payslip[name=" + name
                + ", type=" + type
                + ", pay=" + pay + "]";
    }
}

public class PayrollRegisterSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Payslip> payslipList = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\s+");
            String type = parts[0];
            String name = parts[1];

            Staff staff = null;

            switch (type) {
                case "FullTime":
                    staff = new FullTime(
                        name, Long.parseLong(parts[2]));
                    break;

                case "PartTime":
                    staff = new PartTime(
                        name,
                        Long.parseLong(parts[2]),
                        Long.parseLong(parts[3]));
                    break;

                case "Intern":
                    staff = new Intern(
                        name, Long.parseLong(parts[2]));
                    break;

                default:
                    System.out.println("Invalid staff type");
            }

            if (staff != null) {
                payslipList.add(staff.generatePayslip());
            }
        }

        Payslip[] payslips =
                payslipList.toArray(new Payslip[0]);

        long totalPay = 0;
        int topEarnerIndex = 0;

        for (int i = 0; i < payslips.length; i++) {
            System.out.println(payslips[i]);

            totalPay += payslips[i].getPay();

            if (payslips[i].getPay()
                    > payslips[topEarnerIndex].getPay()) {
                topEarnerIndex = i;
            }
        }

        System.out.println("Total " + totalPay);

        if (payslips.length > 0) {
            System.out.println("top earner "
                    + payslips[topEarnerIndex].getName());
        }

        sc.close();
    }
}
