package camp.Controller;

import camp.Model.Subject;
import camp.Model.SubjectType;

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


    // 초기 데이터 생성
    public void setInitData() {
        studentIndex = 0;
        subjectIndex = 0;
        scoreIndex = 0;
        StudentManager.getInstance().Initialize();

        // 과목 생성
        ArrayList<Subject> subjectStore = StudentManager.getInstance().getSubjectStore();
        subjectStore.add(new Subject(sequence(INDEX_TYPE_SUBJECT), SubjectType.JAVA));
        subjectStore.add(new Subject(sequence(INDEX_TYPE_SUBJECT), SubjectType.OBJECT_ORIENTED));
        subjectStore.add(new Subject(sequence(INDEX_TYPE_SUBJECT), SubjectType.SPRING));
        subjectStore.add(new Subject(sequence(INDEX_TYPE_SUBJECT), SubjectType.JPA));
        subjectStore.add(new Subject(sequence(INDEX_TYPE_SUBJECT), SubjectType.MYSQL));
        subjectStore.add(new Subject(sequence(INDEX_TYPE_SUBJECT), SubjectType.DESIGN_PATTERN));
        subjectStore.add(new Subject(sequence(INDEX_TYPE_SUBJECT), SubjectType.SPRING_SECURITY));
        subjectStore.add(new Subject(sequence(INDEX_TYPE_SUBJECT), SubjectType.REDIS));
        subjectStore.add(new Subject(sequence(INDEX_TYPE_SUBJECT), SubjectType.MONGO_DB));

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
