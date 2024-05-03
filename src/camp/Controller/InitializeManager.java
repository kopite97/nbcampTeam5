package camp.Controller;

import camp.model.Subject;

import java.util.ArrayList;

public class InitializeManager {
    private static InitializeManager instance;

    public static InitializeManager getInstance() {
        if(instance == null) {
            instance = new InitializeManager();
            return instance;
        }
        else {
            return instance;
        }
    }

    // index 관리 필드
    private int studentIndex;
    public final String INDEX_TYPE_STUDENT = "ST";
    private int subjectIndex;
    public final String INDEX_TYPE_SUBJECT = "SU";
    private int scoreIndex;
    public final String INDEX_TYPE_SCORE = "SC";

    // 과목 타입
    private final String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private final String SUBJECT_TYPE_CHOICE = "CHOICE";

    // 초기 데이터 생성
    public void setInitData() {
        StudentManager.getInstance().Initialize();

        // 과목 생성
        ArrayList<Subject> subjectStore = StudentManager.getInstance().getSubjectStore();
        subjectStore.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "Java", SUBJECT_TYPE_MANDATORY));
        subjectStore.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "객체지향", SUBJECT_TYPE_MANDATORY));
        subjectStore.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "Spring", SUBJECT_TYPE_MANDATORY));
        subjectStore.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "JPA", SUBJECT_TYPE_MANDATORY));
        subjectStore.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "MySQL",SUBJECT_TYPE_MANDATORY));
        subjectStore.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "디자인 패턴", SUBJECT_TYPE_CHOICE));
        subjectStore.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "Spring Security",SUBJECT_TYPE_CHOICE));
        subjectStore.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "Redis", SUBJECT_TYPE_CHOICE));
        subjectStore.add(new Subject(sequence(INDEX_TYPE_SUBJECT), "MongoDB", SUBJECT_TYPE_CHOICE));

        // 초기 학생 생성
    }

    // index 자동 증가
    public  String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return INDEX_TYPE_SUBJECT + subjectIndex;
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;
            }
        }
    }
}