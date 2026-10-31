
public class ContestLeaderboard {

    static class Entry {
        String name;
        int marks;

        Entry(String name, int marks) {
            this.name = name;
            this.marks = marks;
        }
    }

    static Entry[] leaderboard(Entry[] entries) {

        int n = entries.length;

        int[] count = new int[101];

        Entry[] output = new Entry[n];

        // Step 1: Count the occurrences of each key
        for (int i = 0; i < n; i++) {

            int key = 100 - entries[i].marks;

            count[key]++;
        }

        // Step 2: Calculate cumulative counts
        for (int i = 1; i < 101; i++) {

            count[i] += count[i - 1];
        }

        // Step 3: Place entries in stable order
        for (int i = n - 1; i >= 0; i--) {

            int key = 100 - entries[i].marks;

            output[count[key] - 1] = entries[i];

            count[key]--;
        }

        return output;
    }

    public static void main(String[] args) {

        Entry[] entries = {
            new Entry("Asha", 90),
            new Entry("Ravi", 75),
            new Entry("Neha", 90),
            new Entry("Kiran", 100),
            new Entry("Meera", 75)
        };

        Entry[] result = leaderboard(entries);

        System.out.println("Contest Leaderboard:");

        for (int i = 0; i < result.length; i++) {

            System.out.println(
                result[i].name + " " + result[i].marks
            );
        }
    }
}

