import java.util.Arrays;

class LibraryMember {
    private int[] history = new int[10];
    private int count;

    protected void chargeFine(int amount) {
        history[count++] = amount;
    }

    int[] getFineHistory() {
        return Arrays.copyOf(history, count);
    }

    int getTotalFine() {
        int total = 0;

        for (int i = 0; i < count; i++)
            total += history[i];

        return total;
    }
}

class StudentMember extends LibraryMember {

    @Override
    protected void chargeFine(int amount) {
        super.chargeFine(amount / 2);
    }
}

public class M3{
    public static void main(String[] args) {

        StudentMember s = new StudentMember();

        s.chargeFine(100);
        System.out.println(s.getTotalFine());

        int[] history = s.getFineHistory();
        history[0] = 999;

        System.out.println(
            Arrays.toString(s.getFineHistory())
        );
    }
}
