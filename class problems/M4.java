import java.util.HashMap;
public class M4 {
    static char findFirstNonRepeatingChar(String text) {
        HashMap<Character, Integer> map =
            new HashMap<>();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (map.get(ch) == 1) {
                return ch;
            }
        }
        return '\0';
    }
    public static void main(String[] args) {
        String text = "swiss";
        char result = findFirstNonRepeatingChar(text);
        if (result == '\0') {
            System.out.println(
                "No Non-Repeating Character Found"
            );
        } else {
            System.out.println(
                "First Non-Repeating Character: '" +
                result + "'"
            );
        }
    }
}
