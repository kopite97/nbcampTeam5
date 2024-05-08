package camp.model;

public class Subject {
    private final String subjectId;
    private SubjectType subjectType;

    public SubjectType getSubjectType() {
        return subjectType;
    }

    public Subject(String seq, SubjectType subjectType) {
        this.subjectId = seq;
    }

    // Getter
    public String getSubjectId() {
        return subjectId;
    }
}
