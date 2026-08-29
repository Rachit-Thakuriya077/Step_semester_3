public class M2 {
    static boolean isPalindromeIterative(String text) {
        int start = 0;
        int end = text.length() - 1;
        while (start < end) {
            if (text.charAt(start) != text.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    static boolean isPalindromeRecursive(String text) {
        if (text.length() <= 1) {
            return true;
        }
        if (text.charAt(0) != text.charAt(text.length() - 1)) {
            return false;
        }
        return isPalindromeRecursive(
            text.substring(1, text.length() - 1)
        );
    }

    static boolean isPalindromeArrayReversal(String text) {
        char[] original = text.toCharArray();
        char[] reversed = new char[original.length];
        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }
        String reverseText = new String(reversed);
        return text.equals(reverseText);
    }
    public static void main(String[] args) {
        String text = "madam";
        System.out.println(
            "Iterative: " +
            (isPalindromeIterative(text)
                ? "Palindrome"
                : "Not Palindrome")
        );
        System.out.println(
            "Recursive: " +
            (isPalindromeRecursive(text)
                ? "Palindrome"
                : "Not Palindrome")
        );
        System.out.println(
            "Array Reversal: " +
            (isPalindromeArrayReversal(text)
                ? "Palindrome"
                : "Not Palindrome")
        );
    }
}
