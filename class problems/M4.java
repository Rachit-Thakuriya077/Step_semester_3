class SrmStudent {

    static String collegeName;
    static int academicYear;

    String name;

    // Static block
    static {
        collegeName = "SRM";
        academicYear = 2026;

        System.out.println("College info loaded");
    }

    // Constructor
    SrmStudent(String name) {
        this.name = name;
    }

    // Display student information
    void display() {
        System.out.println(
            "Student record created: " + name
        );
    }
}

public class M4 {

    public static void main(String[] args) {

        String[] names = {
            "Ravi",
            "Meera",
            "Karthik",
            "Divya",
            "Anitha"
        };

        // Create students
        for (String name : names) {

            SrmStudent student =
                new SrmStudent(name);

            student.display();
        }
    }
}
