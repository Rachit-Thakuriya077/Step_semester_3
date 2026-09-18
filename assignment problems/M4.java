
class LibraryMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMember(String id, int limit) {
        if (id == null || id.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid ID");
        }

        if (limit <= 0) {
            throw new IllegalArgumentException("Invalid limit");
        }

        memberId = id;
        borrowLimit = limit;
        booksBorrowed = 0;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public void displayInfo() {
        System.out.print("General | Books: " + booksBorrowed);
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
        System.out.print("Student | Course: " + course
                + " | Books: " + booksBorrowed);
    }
}

public class M4 {

    static String batchPrint(LibraryMember[] members) {
        StringBuilder report = new StringBuilder();

        for (LibraryMember member : members) {
            // Polymorphic method call
            member.displayInfo();

            // Build report using StringBuilder
            if (member instanceof StudentMember) {
                StudentMember student = (StudentMember) member;

                report.append("Student | Course: ")
                      .append(student.course)
                      .append(" | Books: ")
                      .append(student.getBooksBorrowed());

                report.append(" [Course via downcast: ")
                      .append(student.course)
                      .append("] | ");
            } else {
                report.append("General | Books: ")
                      .append(member.getBooksBorrowed())
                      .append(" | ");
            }
        }

        return report.toString();
    }

    public static void main(String[] args) {
        LibraryMember[] members = {
            new LibraryMember("LB05", 3),
            new StudentMember("STU6", 3, "ECE")
        };

        String result = batchPrint(members);

        System.out.println();
        System.out.println(result);
    }
}
