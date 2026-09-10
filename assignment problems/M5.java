import java.util.Arrays;

class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId,
                       String[] bookIds) {

        this.memberId = memberId;

        // Defensive copy
        this.bookIds =
            Arrays.copyOf(bookIds, bookIds.length);
    }

    // Defensive copy
    public String[] getBookIds() {

        return Arrays.copyOf(
            bookIds,
            bookIds.length
        );
    }

    // With-style method
    public LoanReceipt withCorrectedBookId(
            int index,
            String newId) {

        String[] newBooks = getBookIds();

        if (index >= 0 &&
            index < newBooks.length) {

            newBooks[index] = newId;
        }

        return new LoanReceipt(
            memberId,
            newBooks
        );
    }
}


// Reference-only receipt
class ReferenceOnlyLoanReceipt extends LoanReceipt {

    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(
            String memberId,
            String[] bookIds,
            String roomNumber) {

        super(memberId, bookIds);

        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}


// Circulation Ledger
public class M5 {

    private static String branchCode;

    // Static block
    static {
        branchCode = "PT-BRANCH-01";
    }

    static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (int i = 0; i < receipts.length; i++) {

            if (receipts[i] == null) {

                nullSkipped++;
                continue;
            }

            processed++;

            if (receipts[i]
                    instanceof ReferenceOnlyLoanReceipt) {

                referenceOnly++;

            } else {

                regular++;
            }
        }

        return processed + " processed | "
             + nullSkipped + " null skipped | "
             + referenceOnly + " reference-only | "
             + regular + " regular";
    }

    public static void main(String[] args) {

        // Test defensive copy
        LoanReceipt r =
            new LoanReceipt(
                "LIB-8841",
                new String[]{"BK-100", "BK-101"}
            );

        String[] ids = r.getBookIds();

        ids[0] = "HACKED";

        System.out.println(
            Arrays.toString(r.getBookIds())
        );

        // Test wither
        LoanReceipt corrected =
            r.withCorrectedBookId(
                1,
                "BK-102"
            );

        System.out.println(
            Arrays.toString(r.getBookIds())
        );

        System.out.println(
            Arrays.toString(corrected.getBookIds())
        );

        // Test nightly circulation
        LoanReceipt[] receipts = {

            new ReferenceOnlyLoanReceipt(
                "LIB-001",
                new String[]{"BK-200"},
                "Reading Room 3"
            ),

            null,

            new LoanReceipt(
                "LIB-002",
                new String[]{"BK-201"}
            )
        };

        System.out.println(
            processNightlyCirculation(receipts)
        );
    }
}
