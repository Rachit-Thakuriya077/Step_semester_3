class LibraryMember {
    static int count = 0;

    final String memberNumber;

    int borrowLimit;
    int borrowed;

    LibraryMember(int limit) {
        borrowLimit = limit;
        memberNumber = "LIB-" + (++count + 100);
    }

    void borrowBook() {
        if (borrowed < borrowLimit)
            borrowed++;
    }

    void borrowBook(String genre) {
        borrowBook();
    }

    int getBooksBorrowed() {
        return borrowed;
    }

    static boolean isValidRenewalCode(String code) {

        if (code == null || code.length() != 4)
            return false;

        return code.charAt(0) == 'R'
            && Character.isDigit(code.charAt(1))
            && Character.isDigit(code.charAt(2))
            && Character.isUpperCase(code.charAt(3));
    }

    static int getMembersEnrolled() {
        return count;
    }
}

class FacultyMember extends LibraryMember {

    FacultyMember(int limit, String department) {
        super(limit);
    }
}

public class M5 {

    static String processNightlyAudit(LibraryMember[] members) {

        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        for (LibraryMember m : members) {

            if (m == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (m instanceof FacultyMember)
                faculty++;
            else
                regular++;
        }

        return processed + " processed | " +
               nullSkipped + " null skipped | " +
               faculty + " faculty | " +
               regular + " regular";
    }

    public static void main(String[] args) {

        LibraryMember m1 = new LibraryMember(3);

        System.out.println(m1.memberNumber);
        System.out.println(LibraryMember.getMembersEnrolled());

        System.out.println(
            LibraryMember.isValidRenewalCode("R12A")
        );

        System.out.println(
            LibraryMember.isValidRenewalCode("R1A")
        );

        System.out.println(
            LibraryMember.isValidRenewalCode("X12A")
        );

        m1.borrowBook();
        m1.borrowBook("Fiction");

        System.out.println(m1.getBooksBorrowed());

        LibraryMember[] members = {
            new FacultyMember(5, "Physics"),
            null,
            new LibraryMember(3)
        };

        System.out.println(
            processNightlyAudit(members)
        );
    }
}
