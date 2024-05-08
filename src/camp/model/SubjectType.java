package camp.model;

public enum SubjectType {
    JAVA("Java", "MANDATORY"),
    OBJECT_ORIENTED("객체지향", "MANDATORY"),
    SPRING("Spring", "MANDATORY"),
    JPA("JPA", "MANDATORY"),
    MYSQL("MySQL", "MANDATORY"),
    DESIGN_PATTERN("디자인 패턴", "MANDATORY"),
    SPRING_SECURITY("Spring Security", "MANDATORY"),
    REDIS("Redis", "MANDATORY"),
    MONGO_DB("MongoDB", "MANDATORY");

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
