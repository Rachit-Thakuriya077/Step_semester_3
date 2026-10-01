
enum Status {
    PENDING, APPROVED, REJECTED
}

abstract class Employee {
    private final String name;

    Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean canTakeLeave(int days);
}

class FullTimeEmployee extends Employee {
    FullTimeEmployee(String name) {
        super(name);
    }

    public boolean canTakeLeave(int days) {
        return days > 0 && days <= 30;
    }
}

class PartTimeEmployee extends Employee {
    PartTimeEmployee(String name) {
        super(name);
    }

    public boolean canTakeLeave(int days) {
        return days > 0 && days <= 10;
    }
}

class LeaveRequest {
    private final Employee employee;
    private final String dates;
    private final int days;
    private Status status = Status.PENDING;

    LeaveRequest(Employee employee, String dates, int days) {
        this.employee = employee;
        this.dates = dates;
        this.days = days;
    }

    public void review(boolean approve, String manager) {
        if (status != Status.PENDING) {
            System.out.println("Request has already been reviewed.");
            return;
        }

        if (!employee.canTakeLeave(days)) {
            status = Status.REJECTED;
            System.out.println("Leave policy limit exceeded for "
                    + employee.getName());
            return;
        }

        status = approve ? Status.APPROVED : Status.REJECTED;

        System.out.println(employee.getName() + "'s leave request ("
                + dates + ") " + status.toString().toLowerCase()
                + " by " + manager + ".");
        System.out.println("Status: " + status);
    }

    public void changeStatus(Status newStatus) {
        if (status != Status.PENDING || newStatus == Status.PENDING) {
            System.out.println("Cannot change leave request status from "
                    + status + " to " + newStatus + ".");
            return;
        }

        status = newStatus;
    }

    public Status getStatus() {
        return status;
    }
}

public class Leave_Request {
    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");

        LeaveRequest r1 = new LeaveRequest(john, "Jan 1-5", 5);
        LeaveRequest r2 = new LeaveRequest(jane, "Feb 10-11", 2);

        System.out.println("Leave request submitted for John (Jan 1-5).");
        System.out.println("Status: " + r1.getStatus());
        r1.review(true, "Alice");

        System.out.println();

        System.out.println("Leave request submitted for Jane (Feb 10-11).");
        System.out.println("Status: " + r2.getStatus());
        r2.review(false, "Bob");

        r1.changeStatus(Status.PENDING);
    }
}