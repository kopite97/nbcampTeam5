package camp.model;

public class Subject {
    private final String subjectId;

    public Subject(String seq, SubjectType subjectType) {
        this.subjectId = seq;
    }

    // Getter
    public String getSubjectId() {
        return subjectId;
    }
}
