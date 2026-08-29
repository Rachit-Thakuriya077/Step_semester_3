public class M4 {
    static String maskPhoneNumber(String phone) {
        if (phone.length() != 10) {
            return "Invalid phone number";
        }
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }
        String lastFour = phone.substring(6);
        StringBuilder result = new StringBuilder("XXXXXX");
        result.insert(6, "-");
        result.append(lastFour);
        return result.toString();
    }
    public static void main(String[] args) {
        String phone1 = "9876543210";
        String phone2 = "98765";
        System.out.println(maskPhoneNumber(phone1));
        System.out.println(maskPhoneNumber(phone2));
    }
}
