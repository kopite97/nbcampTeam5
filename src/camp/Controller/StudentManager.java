package camp.Controller;

import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class StudentManager {
    private static StudentManager instance;

    // 데이터 저장소
    private ArrayList<Student> studentStore;
    private ArrayList<Subject> subjectStore;
    Scanner sc;

    public static StudentManager getInstance() {
        if (instance == null) {
            instance = new StudentManager();
            return instance;
        } else {
            return instance;
        }
    }
    public void Initialize() {
        studentStore = new ArrayList<Student>();
        subjectStore = new ArrayList<Subject>();
        sc = new Scanner(System.in);
    }

    // StudentManager가 가지고 있는 학생 리스트 반환
    public ArrayList<Student> getStudentStore() {
        return studentStore;
    }
    // StudentManager가 가지고 있는 과목 리스트 반환
    public ArrayList<Subject> getSubjectStore() {
        return subjectStore;
    }

    // 수강생 등록
    public void createStudent() {
        System.out.println("\n======새로운 수강생 등록=======");

        System.out.print("이름 : ");
        String name = sc.nextLine();

        String account =InitializeManager.getInstance().sequence(InitializeManager.getInstance().INDEX_TYPE_STUDENT);
        Student student = new Student(account,name);

        System.out.println("\n추가 완료 : "+name);

    }

    // 수강생 목록 조회 : 아이디 까지 같이 띄움
    public void inquireStudent() {
        printAllStudnts();
    }

    // 학생의 점수를 등록하기 위한 리스트 띄우기
    public void allStudentsListForRegistScore(){
        printAllStudnts();
        String account = sc.nextLine();
        Optional<Student> selectedStudent = studentStore.stream().filter(student -> student.getAccount().equals(account)).findFirst();
        selectedStudent.ifPresent(Student::updateRoundScoreBySubject); // 임시로 변경할때와 같은 메서드
    }

    // 학생의 점수를 변경하기 위한 리스트 띄우기
    public void allStudentsListForChangeScore() {
        printAllStudnts();

        String account = sc.nextLine();
        Optional<Student> selectedStudent = studentStore.stream().filter(student -> student.getAccount().equals(account)).findFirst();
        selectedStudent.ifPresent(Student::updateRoundScoreBySubject);
    }

    // 수강생의 특정과목 회차별 등급조회를 위한 메서드
    public void allStudentsListForInquireRoundGradeBySubject(){
        printAllStudnts();

        String account = sc.nextLine();
        Optional<Student> selectedStudent = studentStore.stream().filter(student -> student.getAccount().equals(account)).findFirst();
        selectedStudent.ifPresent(Student:: inquireRoundGradeBySubject );
    }

    private void printAllStudnts()
    {
        System.out.println("\n=======학생의 리스트=========\n\n");

        for (var student : studentStore) {
            System.out.println(student.getName()+" : "+student.getAccount());
        }

    }

}
