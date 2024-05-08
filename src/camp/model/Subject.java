package camp.model;

public class Subject {
    private final String subjectId;
    private final SubjectType subjectType;

    public Subject(String subjectId, SubjectType subjectType) {
        this.subjectId = subjectId;
        this.subjectType = subjectType;
    }

    // Getter
    public String getSubjectId() {
        return subjectId;
    }

    public SubjectType getSubjectType() {
        return subjectType;
    }
}
