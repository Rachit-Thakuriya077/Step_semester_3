public class P4 {
    static String normalizeCode(String raw) {
        String code = raw.trim();
        if (code.length() < 3) {
            return code.toUpperCase();
        }
        String publisherCode = code.substring(0, 3).toUpperCase();
        String remaining = code.substring(3);
        return publisherCode + remaining;
    }
    static String validateAndFormat(String code) {
        if (code.length() != 13) {
            return "Invalid: wrong length";
        }
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: body must contain only digits";
            }
        }
        String publisherCode = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7, 13);
        StringBuilder result = new StringBuilder();

        result.append("[");
        result.append(publisherCode);
        result.append("] YEAR: ");
        result.append(year);
        result.append(" | CATALOG: ");
        result.append(catalog);

        return result.toString();
    }
    public static void main(String[] args) {
        String rawCode = " pen2026004251 ";
        String code = normalizeCode(rawCode);
        System.out.println(validateAndFormat(code));
        String invalidCode = normalizeCode("12N2026004251");
        System.out.println(validateAndFormat(invalidCode));
    }
}
