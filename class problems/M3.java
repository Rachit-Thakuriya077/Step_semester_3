public class M3 {
    static String validateFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex == -1) {
            return "Rejected — invalid file type";
        }
        String extension = filename.substring(dotIndex + 1);
        if (extension.equalsIgnoreCase("pdf") ||
            extension.equalsIgnoreCase("docx") ||
            extension.equalsIgnoreCase("zip")) {
            return "Accepted";
        }
        return "Rejected — invalid file type";
    }
    public static void main(String[] args) {
        String filename1 = "Assignment1.PDF";
        String filename2 = "notes.txt";
        System.out.println(validateFileExtension(filename1));
        System.out.println(validateFileExtension(filename2));
    }
}