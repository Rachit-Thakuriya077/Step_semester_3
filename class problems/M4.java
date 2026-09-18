class LibraryMember {
    int borrowed;

    LibraryMember(String id, int limit) {}

    void borrowBook() {
        borrowed++;
    }

    int getBooksBorrowed() {
        return borrowed;
    }

    String displayInfo() {
        return "General | Books: " + borrowed;
    }
}

class StudentMember extends LibraryMember {
    String course;

    StudentMember(String id, int limit, String course) {
        super(id, limit);
        this.course = course;
    }

    @Override
    String displayInfo() {
        return "Student | Course: " + course +
               " | Books: " + borrowed;
    }
}

public class M4 {

    static String batchPrint(LibraryMember[] members) {

        StringBuilder sb = new StringBuilder();

        for (LibraryMember m : members) {

            sb.append(m.displayInfo());

            if (m instanceof StudentMember) {
                StudentMember s = (StudentMember)m;

                sb.append(" [Course via downcast: ")
                  .append(s.course)
                  .append("]");
            }

            sb.append(" | ");
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        LibraryMember[] members = {
            new LibraryMember("LB5", 3),
            new StudentMember("STU6", 3, "ECE")
        };

        System.out.println(batchPrint(members));
    }
}