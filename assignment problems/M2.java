
class LibraryMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().length() < 4)
            throw new IllegalArgumentException("Invalid ID");

        if (borrowLimit <= 0)
            throw new IllegalArgumentException("Invalid limit");

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        booksBorrowed = 0;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit)
            booksBorrowed++;
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public void displayInfo() {
        System.out.println("General Member | Books Borrowed: "
                + booksBorrowed);
    }
}

class StudentMember extends LibraryMember {
    protected String course;

    public StudentMember(String id, int limit, String course) {
        super(id, limit);
        this.course = course;
    }

    @Override
    public void displayInfo() {
        System.out.println("Student Member | Course: " + course
                + " | Books Borrowed: " + booksBorrowed);
    }
}

class HonorsStudentMember extends StudentMember {
    private int bonusLimit;

    public HonorsStudentMember(String id, int limit,
                               String course, int bonusLimit) {
        super(id, limit, course);
        this.bonusLimit = bonusLimit;
    }

    @Override
    public void displayInfo() {
        System.out.println("Honors Student Member | Course: " + course
                + " | Bonus Limit: " + bonusLimit
                + " | Books Borrowed: " + booksBorrowed);
    }
}

class FacultyMember extends LibraryMember {
    private String department;

    public FacultyMember(String id, int limit, String department) {
        super(id, limit);
        this.department = department;
    }

    @Override
    public void displayInfo() {
        System.out.println("Faculty Member | Department: " + department
                + " | Books Borrowed: " + booksBorrowed);
    }
}

public class M2 {
    static String classifyGeneration(LibraryMember member) {
        if (member instanceof HonorsStudentMember)
            return "Multilevel descendant (3 generations deep)";

        if (member instanceof FacultyMember)
            return "Hierarchical sibling (independent branch)";

        return "Base or direct subclass";
    }

    static int getTotalBooksBorrowed(LibraryMember[] members) {
        int total = 0;

        for (LibraryMember member : members) {
            total += member.getBooksBorrowed();
        }

        return total;
    }

    public static void main(String[] args) {
        StudentMember s = new StudentMember("STU2", 3, "CSE");
        HonorsStudentMember h =
                new HonorsStudentMember("STU3", 3, "ECE", 2);
        FacultyMember f = new FacultyMember("STU4", 5, "Physics");

        s.borrowBook();
        s.borrowBook();

        h.borrowBook();

        f.borrowBook();
        f.borrowBook();
        f.borrowBook();

        LibraryMember[] members = {s, h, f};

        System.out.println(classifyGeneration(h));
        System.out.println(classifyGeneration(f));
        System.out.println(getTotalBooksBorrowed(members));

        for (LibraryMember member : members) {
            member.displayInfo();
        }
    }
}
