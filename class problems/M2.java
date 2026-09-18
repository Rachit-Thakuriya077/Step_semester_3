class LibraryMember {
    int borrowed;

    LibraryMember(String id, int limit) {}

    void borrowBook() {
        borrowed++;
    }

    int getBooksBorrowed() {
        return borrowed;
    }

    void displayInfo() {
        System.out.println(
            "General Member | Books Borrowed: " + borrowed
        );
    }
}

class StudentMember extends LibraryMember {
    String course;

    StudentMember(String id, int limit, String course) {
        super(id, limit);
        this.course = course;
    }

    @Override
    void displayInfo() {
        System.out.println(
            "Student Member | Course: " + course +
            " | Books Borrowed: " + borrowed
        );
    }
}

class HonorsStudentMember extends StudentMember {
    int bonusLimit;

    HonorsStudentMember(String id, int limit,
                        String course, int bonusLimit) {
        super(id, limit, course);
        this.bonusLimit = bonusLimit;
    }

    @Override
    void displayInfo() {
        System.out.println(
            "Honors Student Member | Course: " + course +
            " | Bonus Limit: " + bonusLimit +
            " | Books Borrowed: " + borrowed
        );
    }
}

class FacultyMember extends LibraryMember {
    String department;

    FacultyMember(String id, int limit, String department) {
        super(id, limit);
        this.department = department;
    }

    @Override
    void displayInfo() {
        System.out.println(
            "Faculty Member | Department: " + department +
            " | Books Borrowed: " + borrowed
        );
    }
}

public class M2 {

    static String classifyGeneration(LibraryMember m) {
        if (m instanceof HonorsStudentMember)
            return "Multilevel descendant (3 generations deep)";

        if (m instanceof FacultyMember)
            return "Hierarchical sibling (independent branch)";

        return "General Member";
    }

    static int getTotalBooksBorrowed(LibraryMember[] members) {
        int total = 0;

        for (LibraryMember m : members)
            total += m.getBooksBorrowed();

        return total;
    }

    public static void main(String[] args) {

        LibraryMember general =
            new LibraryMember("STU1", 3);

        StudentMember student =
            new StudentMember("STU2", 3, "CSE");

        HonorsStudentMember honors =
            new HonorsStudentMember("STU3", 3, "ECE", 2);

        FacultyMember faculty =
            new FacultyMember("STU4", 5, "Physics");

        general.displayInfo();
        student.displayInfo();
        honors.displayInfo();
        faculty.displayInfo();

        System.out.println(classifyGeneration(honors));
        System.out.println(classifyGeneration(faculty));

        student.borrowBook();
        student.borrowBook();

        honors.borrowBook();

        faculty.borrowBook();
        faculty.borrowBook();
        faculty.borrowBook();

        LibraryMember[] members = {
            student, honors, faculty
        };

        System.out.println(getTotalBooksBorrowed(members));
    }
}
