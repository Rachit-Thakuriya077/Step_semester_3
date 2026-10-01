
import java.util.*;

interface NotificationChannel {
    void send(String student, String title);
    String getName();
}

class EmailChannel implements NotificationChannel {
    public String getName() { return "Email"; }

    public void send(String student, String title) {
        System.out.println("[Email → " + student + "] " + title);
    }
}

class SmsChannel implements NotificationChannel {
    public String getName() { return "SMS"; }

    public void send(String student, String title) {
        System.out.println("[SMS → " + student + "] " + title);
    }
}

class AppChannel implements NotificationChannel {
    public String getName() { return "App"; }

    public void send(String student, String title) {
        System.out.println("[App → " + student + "] " + title);
    }
}

class Student {
    private final String name;
    private final String department;
    private final List<NotificationChannel> channels = new ArrayList<>();

    Student(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void addChannel(NotificationChannel channel) {
        for (NotificationChannel existing : channels) {
            if (existing.getName().equals(channel.getName())) {
                return;
            }
        }
        channels.add(channel);
    }

    public List<NotificationChannel> getChannels() {
        return Collections.unmodifiableList(channels);
    }
}

class Notice {
    private final String title;
    private final Set<String> departments;

    Notice(String title, Set<String> departments) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Notice title is required.");
        }

        if (departments == null || departments.isEmpty()) {
            throw new IllegalArgumentException(
                    "At least one target department is required.");
        }

        this.title = title;
        this.departments = new HashSet<>(departments);
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getDepartments() {
        return Collections.unmodifiableSet(departments);
    }
}

class NoticeBoard {
    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void postNotice(String title, Set<String> departments) {
        Notice notice;

        try {
            notice = new Notice(title, departments);
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot post notice: " + e.getMessage());
            return;
        }

        System.out.println("Notice '" + notice.getTitle()
                + "' posted to "
                + String.join(", ", new TreeSet<>(
                        notice.getDepartments())) + ".");

        for (Student student : students) {
            if (notice.getDepartments().contains(
                    student.getDepartment())) {

                for (NotificationChannel channel : student.getChannels()) {
                    channel.send(student.getName(), notice.getTitle());
                }
            }
        }
    }
}

public class CampusNoticeBroadcaster {
    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();

        Student asha = new Student("Asha", "CSE");
        asha.addChannel(new EmailChannel());
        asha.addChannel(new AppChannel());

        Student ravi = new Student("Ravi", "ECE");
        ravi.addChannel(new SmsChannel());

        board.addStudent(asha);
        board.addStudent(ravi);

        board.postNotice("Lab Closed Tomorrow",
                new HashSet<>(Arrays.asList("CSE")));

        board.postNotice("Fee Deadline Extended",
                new HashSet<>(Arrays.asList("CSE", "ECE")));

        board.postNotice("Sports Day", Collections.emptySet());
    }
}