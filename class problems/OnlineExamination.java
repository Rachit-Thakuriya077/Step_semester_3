import java.util.*;

abstract class Question {
    private final String text;
    private final int marks;

    Question(String text, int marks) {
        this.text = text;
        this.marks = marks;
    }

    public String getText() {
        return text;
    }

    public int getMarks() {
        return marks;
    }

    public abstract boolean evaluate(String answer);
}

class MCQ extends Question {
    private final String correctOption;

    MCQ(String text, int marks, String correctOption) {
        super(text, marks);
        this.correctOption = correctOption;
    }

    public boolean evaluate(String answer) {
        return correctOption.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion extends Question {
    private final String correctAnswer;

    TrueFalseQuestion(String text, int marks, String correctAnswer) {
        super(text, marks);
        this.correctAnswer = correctAnswer;
    }

    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Student {
    private final String name;

    Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Examination {
    private final String name;
    private final List<Question> questions = new ArrayList<>();
    private final Set<String> submittedStudents = new HashSet<>();

    Examination(String name) {
        this.name = name;
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public String getName() {
        return name;
    }

    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }

    public boolean hasSubmitted(String student) {
        return submittedStudents.contains(student);
    }

    public void markSubmitted(String student) {
        submittedStudents.add(student);
    }
}

class Attempt {
    private final Student student;
    private final Examination exam;
    private final Map<Integer, String> answers = new HashMap<>();
    private boolean submitted = false;

    Attempt(Student student, Examination exam) {
        this.student = student;
        this.exam = exam;
    }

    public void answer(int questionNumber, String answer) {
        if (submitted || exam.hasSubmitted(student.getName())) {
            System.out.println(
                    "Cannot change answers for a submitted examination.");
            return;
        }

        if (questionNumber < 1 ||
                questionNumber > exam.getQuestions().size()) {
            System.out.println("Invalid question number.");
            return;
        }

        answers.put(questionNumber - 1, answer);
        System.out.println("Answer recorded for Question "
                + questionNumber + ".");
    }

    public void submit() {
        if (submitted || exam.hasSubmitted(student.getName())) {
            System.out.println("Examination already submitted.");
            return;
        }

        submitted = true;
        exam.markSubmitted(student.getName());

        System.out.println(exam.getName() + " submitted by "
                + student.getName() + ".");

        int score = 0;
        int total = 0;

        List<Question> questions = exam.getQuestions();

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            total += q.getMarks();

            boolean correct = q.evaluate(answers.getOrDefault(i, ""));

            if (correct) {
                score += q.getMarks();
            }

            System.out.println("Question " + (i + 1) + ": "
                    + (correct ? "Correct" : "Incorrect")
                    + " (" + (correct ? q.getMarks() : 0)
                    + " points)");
        }

        System.out.println("Total score: " + score + "/" + total);
    }
}

public class OnlineExamination {
    public static void main(String[] args) {
        Student student = new Student("Student 1");

        Examination exam = new Examination("Exam A");
        exam.addQuestion(new MCQ("Question 1", 5, "C"));
        exam.addQuestion(
                new TrueFalseQuestion("Question 2", 5, "False"));

        System.out.println("Exam A started by Student 1.");

        Attempt attempt = new Attempt(student, exam);
        attempt.answer(1, "C");
        attempt.answer(2, "True");
        attempt.submit();

        attempt.answer(1, "B");
    }
}

