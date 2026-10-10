
import java.util.*;

public class ClassMarksGridAnalysis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> namesList = new ArrayList<>();
        List<int[]> marksList = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            int bracketIndex = line.indexOf('[');

            String name = line.substring(0, bracketIndex).trim();
            String marksText = line.substring(bracketIndex);

            String[] values = marksText
                    .replaceAll("[^0-9,]", "")
                    .split(",");

            int[] marks = new int[3];

            for (int i = 0; i < 3; i++) {
                marks[i] = Integer.parseInt(values[i].trim());
            }

            namesList.add(name);
            marksList.add(marks);
        }

        int n = namesList.size();

        if (n == 0) {
            sc.close();
            return;
        }

        String[] names = namesList.toArray(new String[0]);
        int[][] grid = marksList.toArray(new int[0][]);

        int[] totals = new int[n];
        double[] subjectTotals = new double[3];
        int topperIndex = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                totals[i] += grid[i][j];
                subjectTotals[j] += grid[i][j];
            }

            if (totals[i] > totals[topperIndex]) {
                topperIndex = i;
            }
        }

        System.out.print("Totals ");

        for (int i = 0; i < n; i++) {
            if (i > 0) {
                System.out.print(", ");
            }

            System.out.print(names[i] + " " + totals[i]);
        }

        System.out.println();
        System.out.print("averages ");

        for (int j = 0; j < 3; j++) {
            if (j > 0) {
                System.out.print(", ");
            }

            System.out.printf("%.2f", subjectTotals[j] / n);
        }

        System.out.println();

        System.out.println("topper " + names[topperIndex]
                + " (" + totals[topperIndex] + ")");

        sc.close();
    }
}
