package camp.Model;

public class Subject {
    private final String subjectId;
    private SubjectType subjectType;

    public SubjectType getSubjectType() {
        return subjectType;
    }

    public Subject(String seq, SubjectType subjectType) {
        this.subjectId = seq;
        this.subjectType = subjectType;
    }

    // Getter
    public String getSubjectId() {
        return subjectId;
    }
}
