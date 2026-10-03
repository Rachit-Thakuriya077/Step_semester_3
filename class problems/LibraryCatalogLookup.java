
import java.util.*;

public class LibraryCatalogLookup {

    public static String findBook(List<String[]> catalog, String targetIsbn) {
        int low = 0;
        int high = catalog.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int result = catalog.get(mid)[0].compareTo(targetIsbn);

            if (result == 0) {
                return catalog.get(mid)[1];
            } else if (result < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return "Not Found";
    }

    public static void main(String[] args) {
        List<String[]> catalog = Arrays.asList(
            new String[]{"0001112223", "Introduction to Algebra"},
            new String[]{"0002223334", "Beginning Python"},
            new String[]{"0003334445", "Classic Mythology"},
            new String[]{"0004445556", "Data and Society"},
            new String[]{"0005556667", "European History"}
        );

        String targetIsbn = "0003334445";
        System.out.println(findBook(catalog, targetIsbn));
    }
}
