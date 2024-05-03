package camp.Controller;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private static StudentManager instance;

    // 데이터 저장소
    private ArrayList<Student> studentStore;
    private ArrayList<Subject> subjectStore;
    private ArrayList<Score> scoreStore;

    public static StudentManager getInstance() {
        if (instance == null) {
            instance = new StudentManager();
            return instance;
        } else {
            return instance;
        }
    }
    public void InitializeModelList() {
        studentStore = new ArrayList<Student>();
        subjectStore = new ArrayList<Subject>();
        scoreStore = new ArrayList<Score>();
    }

    public ArrayList<Student> getStudentStore() {
        return studentStore;
    }
    public ArrayList<Subject> getSubjectStore() {
        return subjectStore;
    }
    public ArrayList<Score> getScoreStore() {
        return scoreStore;
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    public void createScore() {
        System.out.println("Creating score...");
    }

    // 수강생 목록 조회
    public void inquireStudent() {

    }



}
