
public class ClassTopperFinder {

    public static int[] findTopper(int[][] marks) {
        int bestIndex = 0;
        int highestTotal = -1;

        for (int i = 0; i < marks.length; i++) {
            int total = 0;

            for (int j = 0; j < marks[i].length; j++) {
                total += marks[i][j];
            }

            if (total > highestTotal) {
                highestTotal = total;
                bestIndex = i;
            }
        }

        return new int[]{bestIndex, highestTotal};
    }

    public static void main(String[] args) {
        int[][] marks = {
            {78, 85, 90},
            {88, 92, 79},
            {65, 70, 95}
        };

        int[] result = findTopper(marks);

        System.out.println(
            "(" + result[0] + ", " + result[1] + ")"
        );
    }
}
