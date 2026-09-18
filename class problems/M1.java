class LibraryMember {
    String memberId;
    int borrowLimit, booksBorrowed;

    LibraryMember(String id, int limit) {
        if (id == null || id.trim().length() < 4)
            throw new IllegalArgumentException();

        if (limit <= 0)
            throw new IllegalArgumentException();

        memberId = id;
        borrowLimit = limit;
    }

    void borrowBook() {
        if (booksBorrowed < borrowLimit)
            booksBorrowed++;
    }

    int getBooksBorrowed() {
        return booksBorrowed;
    }

    static String enrollBatch(String[] ids, int limit) {
        int enrolled = 0, rejected = 0;

        for (String id : ids) {
            try {
                new LibraryMember(id, limit);
                enrolled++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Enrolled: " + enrolled + " | Rejected: " + rejected;
    }
}

class StudentMember extends LibraryMember {
    String course;

    StudentMember(String id, int limit, String course) {
        super(id, limit);
        this.course = course;
    }
}

public class M1 {
    public static void main(String[] args) {

        try {
            new LibraryMember("LB1", 3);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        StudentMember s = new StudentMember("STU10", 3, "CSE");
        s.borrowBook();
        s.borrowBook();
        System.out.println(s.getBooksBorrowed());

        String[] ids = {"STU1", "LB1", "STU2", " ", "STU3"};
        System.out.println(LibraryMember.enrollBatch(ids, 3));
    }
}