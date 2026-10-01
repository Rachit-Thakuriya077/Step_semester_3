
import java.time.LocalDate;

abstract class Assignment {
    private final String title;
    private final int maxMarks;
    private final LocalDate dueDate;

    Assignment(String title, int maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public int getLateDays(LocalDate submissionDate) {
        long days = java.time.temporal.ChronoUnit.DAYS.between(
                dueDate, submissionDate);
        return (int) Math.max(0, days);
    }

    public abstract double applyPenalty(double marks, int lateDays);
}

class CodingAssignment extends Assignment {
    CodingAssignment(String title, int max, LocalDate due) {
        super(title, max, due);
    }

    public double applyPenalty(double marks, int lateDays) {
        return marks * Math.max(0, 1 - 0.10 * lateDays);
    }
}

class WrittenAssignment extends Assignment {
    WrittenAssignment(String title, int max, LocalDate due) {
        super(title, max, due);
    }

    public double applyPenalty(double marks, int lateDays) {
        return marks * Math.max(0, 1 - 0.20 * lateDays);
    }
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

class Submission {
    private final Student student;
    private final Assignment assignment;
    private final LocalDate submissionDate;
    private String status = "Submitted";
    private double finalMarks;

    Submission(Student student, Assignment assignment,
               LocalDate submissionDate) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDate = submissionDate;

        int lateDays = assignment.getLateDays(submissionDate);

        System.out.println(student.getName() + "'s submission for '"
                + assignment.getTitle() + "' received ("
                + (lateDays == 0 ? "on time" : lateDays + " days late")
                + "). Status: Submitted.");
    }

    public void grade(double awardedMarks) {
        if (!status.equals("Submitted")) {
            System.out.println("Cannot grade this submission again.");
            return;
        }

        if (awardedMarks < 0 || awardedMarks > assignment.getMaxMarks()) {
            System.out.println("Invalid marks.");
            return;
        }

        int lateDays = assignment.getLateDays(submissionDate);
        finalMarks = assignment.applyPenalty(awardedMarks, lateDays);
        status = "Graded";

        System.out.printf("%s graded: %.0f/%d",
                student.getName(), finalMarks, assignment.getMaxMarks());

        if (lateDays > 0) {
            System.out.printf(" after %.0f%% late penalty",
                    lateDays * (assignment instanceof CodingAssignment
                            ? 10.0 : 20.0));
        }

        System.out.println(". Status: " + status + ".");
    }

    public void resubmit() {
        if (status.equals("Graded")) {
            System.out.println("Cannot resubmit: '"
                    + assignment.getTitle() + "' has already been graded.");
        } else {
            System.out.println("Resubmission allowed.");
        }
    }
}

public class AssignmentSubmissionPortal {
    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding = new CodingAssignment(
                "Linked List Lab", 50,
                LocalDate.of(2027, 3, 10));

        Assignment written = new WrittenAssignment(
                "Design Essay", 50,
                LocalDate.of(2027, 3, 12));

        Submission s1 = new Submission(
                asha, coding, LocalDate.of(2027, 3, 10));

        Submission s2 = new Submission(
                ravi, written, LocalDate.of(2027, 3, 14));

        s1.grade(45);
        s2.grade(40);
        s1.resubmit();
    }
}