
import java.util.*;

class Student {
    private String rollNumber;
    private String name;

    Student(String rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Student)) {
            return false;
        }

        Student other = (Student) obj;
        return rollNumber.equals(other.rollNumber);
    }

    @Override
    public int hashCode() {
        return rollNumber.hashCode();
    }
}

public class StudentClubRegistration {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Set<Student> members = new HashSet<>();
        List<String> addResults = new ArrayList<>();
        List<String> containsResults = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\s+");
            String operation = parts[0];

            Student student = new Student(parts[1], parts[2]);

            if (operation.equals("ADD")) {
                if (members.add(student)) {
                    addResults.add("Added");
                } else {
                    addResults.add("duplicate rejected");
                }
            } else if (operation.equals("CONTAINS")) {
                containsResults.add(
                    "contains: " + members.contains(student)
                );
            }
        }

        for (String result : addResults) {
            System.out.println(result);
        }

        System.out.println("member count " + members.size());

        for (String result : containsResults) {
            System.out.println(result);
        }

        sc.close();
    }
}

