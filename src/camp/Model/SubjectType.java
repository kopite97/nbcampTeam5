package camp.Model;

public enum SubjectType {
    JAVA("Java", "MANDATORY"),
    OBJECT_ORIENTED("객체지향", "MANDATORY"),
    SPRING("Spring", "MANDATORY"),
    JPA("JPA", "MANDATORY"),
    MYSQL("MySQL", "MANDATORY"),
    DESIGN_PATTERN("디자인 패턴", "CHOICE"),
    SPRING_SECURITY("Spring Security", "CHOICE"),
    REDIS("Redis", "CHOICE"),
    MONGO_DB("MongoDB", "CHOICE");

    private final String subjectName;
    private final String subjectType;

    SubjectType(String subjectName, String subjectType) {
        this.subjectName = subjectName;
        this.subjectType = subjectType;
    }

    // Getter
    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectType() {
        return subjectType;
    }
}
