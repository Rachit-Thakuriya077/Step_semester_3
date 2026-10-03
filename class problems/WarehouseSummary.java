
import java.util.*;

public class WarehouseSummary {

    public static Object[] warehouseSummary(List<List<Integer>> grid) {
        int totalItems = 0;
        int maxItems = -1;
        int maxRow = 0;
        int maxCol = 0;

        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                int items = grid.get(i).get(j);
                totalItems += items;

                if (items > maxItems) {
                    maxItems = items;
                    maxRow = i;
                    maxCol = j;
                }
            }
        }

        return new Object[]{
            totalItems,
            new int[]{maxRow, maxCol}
        };
    }

    public static void main(String[] args) {
        List<List<Integer>> grid = Arrays.asList(
            Arrays.asList(4, 9, 2),
            Arrays.asList(7, 1, 6),
            Arrays.asList(3, 12, 5)
        );

        Object[] result = warehouseSummary(grid);
        int[] coordinate = (int[]) result[1];

        System.out.println("(" + result[0] + ", (" +
            coordinate[0] + ", " + coordinate[1] + "))");
    }
}
