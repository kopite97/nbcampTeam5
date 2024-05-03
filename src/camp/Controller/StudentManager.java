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
    }

    // StudentManager가 가지고 있는 학생 리스트 반환
    public ArrayList<Student> getStudentStore() {
        return studentStore;
    }
    // StudentManager가 가지고 있는 과목 리스트 반환
    public ArrayList<Subject> getSubjectStore() {
        return subjectStore;
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    public void createScore() {
        System.out.println("Creating score...");

    }

    // 수강생 목록 조회
    public void inquireStudent() {

    }

    // 학생의 점수를 등록하기 위한 리스트 띄우기
    public void allStudentsListForRegistScore(){
        System.out.println("\n=========학생의 리스트===========\n\n");
        int i =1;
        for(var student : studentStore) {
            System.out.println(i+". "+student.getName());
        }
    }

    public void allStudentsListForChangeScore() {

    }



}
