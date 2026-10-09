
import java.util.HashMap;
import java.util.Map;

public class MostPopularCanteenOrder {

    public static Object[] mostPopular(String[] orders) {
        HashMap<String, Integer> frequency = new HashMap<>();

        // Count the frequency of every item
        for (String item : orders) {
            frequency.put(
                item, frequency.getOrDefault(item, 0) + 1
            );
        }

        String popularItem = orders[0];
        int maxCount = 0;

        // Scan original order to resolve ties
        for (String item : orders) {
            int count = frequency.get(item);

            if (count > maxCount) {
                maxCount = count;
                popularItem = item;
            }
        }

        return new Object[]{popularItem, maxCount};
    }

    public static void main(String[] args) {
        String[] orders = {
            "dosa", "idli", "vada", "dosa",
            "idli", "dosa", "tea"
        };

        Object[] result = mostPopular(orders);

        System.out.println(
            "(" + result[0] + ", " + result[1] + ")"
        );

        String[] orders2 = {
            "tea", "coffee", "coffee", "tea"
        };

        Object[] result2 = mostPopular(orders2);

        System.out.println(
            "(" + result2[0] + ", " + result2[1] + ")"
        );
    }
}

