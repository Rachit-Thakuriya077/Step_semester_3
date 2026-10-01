
abstract class MembershipPlan {
    protected final double baseRate = 1000;

    public abstract int getMonths();
    public abstract double getDiscount();

    public double calculateFee() {
        return baseRate * getMonths() * (1 - getDiscount());
    }

    public abstract String getName();
}

class MonthlyPlan extends MembershipPlan {
    public int getMonths() { return 1; }
    public double getDiscount() { return 0; }
    public String getName() { return "Monthly"; }
}

class QuarterlyPlan extends MembershipPlan {
    public int getMonths() { return 3; }
    public double getDiscount() { return 0.10; }
    public String getName() { return "Quarterly"; }
}

class AnnualPlan extends MembershipPlan {
    public int getMonths() { return 12; }
    public double getDiscount() { return 0.25; }
    public String getName() { return "Annual"; }
}

class Member {
    private final String name;

    Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Membership {
    private final Member member;
    private final MembershipPlan plan;
    private String status = "Active";

    Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;

        System.out.println(plan.getName() + " membership created for "
                + member.getName() + ".");
        System.out.printf("Fee: ₹%.2f%n", plan.calculateFee());
        System.out.println("Status: " + status);
    }

    public void checkIn() {
        if (status.equals("Active")) {
            System.out.println(member.getName()
                    + " checked in successfully.");
        } else {
            System.out.println("Check-in denied: "
                    + member.getName() + "'s membership is " + status + ".");
        }
    }

    public void freeze() {
        if (status.equals("Expired")) {
            System.out.println("Cannot freeze an Expired membership.");
        } else if (status.equals("Frozen")) {
            System.out.println("Membership is already Frozen.");
        } else {
            status = "Frozen";
            System.out.println(member.getName() + "'s membership frozen.");
            System.out.println("Status: " + status);
        }
    }

    public void unfreeze() {
        if (status.equals("Expired")) {
            System.out.println("Cannot unfreeze an Expired membership.");
        } else if (status.equals("Frozen")) {
            status = "Active";
            System.out.println(member.getName()
                    + "'s membership unfrozen.");
            System.out.println("Status: " + status);
        } else {
            System.out.println("Membership is already Active.");
        }
    }

    public void expire() {
        status = "Expired";
        System.out.println(member.getName() + "'s membership expired.");
        System.out.println("Status: " + status);
    }
}

public class FitZoneMembershipDesk {
    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership m1 = new Membership(asha, new QuarterlyPlan());
        Membership m2 = new Membership(ravi, new MonthlyPlan());

        m1.checkIn();
        m1.freeze();
        m1.checkIn();

        m2.expire();
        m2.freeze();
    }
}