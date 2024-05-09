package camp.Controller;

import camp.Model.*;
import camp.View.DisplayManager;

import java.util.*;
import java.util.stream.Stream;

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

    public void Initialize() {
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

    public Subject getSubjectStore(String wantSubject) {
        for (var index : subjectStore) {
            if (index.getSubjectType().getSubjectName().equals(wantSubject)) {
                return index;
            }
        }
        return null;
    }

    // 수강생 등록
    public void createStudent() {
        System.out.println("\n======새로운 수강생 등록=======");

        System.out.print("이름 : ");
        String name = DisplayManager.getInstance().inputScanner(String.class);

        String account = InitializeManager.getInstance().sequence(InitializeManager.getInstance().INDEX_TYPE_STUDENT);
        Student student = new Student(account, name);

        addSubjectWhenCreateNewStudent(student);
        studentStore.add(student);
        System.out.println("\n추가 완료 : " + name);
    }

    // 수강생 목록 조회 : 아이디 까지 같이 띄움
    public void inquireStudent() {
        printAllStudnts();
    }

    // 수강생 정보 조회
    public void inquireStudentInfo(){
        printAllStudnts();

        System.out.println("\n조회 학생의 고유번호를 입력하세요.");

        String account = DisplayManager.getInstance().inputScanner(String.class);
        Optional<Student> selectedStudent = studentStore.stream().filter(student -> student.getAccount().equals(account)).findFirst();
        if (selectedStudent.isEmpty()) {
            System.out.println("해당 학생이 없습니다.(고유번호를 입력해주세요)");
            return;
        }

        System.out.println("\n고유번호 : "+account);
        System.out.println("이름 : "+selectedStudent.get().getName());
        System.out.println("상태 : "+selectedStudent.get()); // getstate
        System.out.println("선택한 과목");
        for (var index : selectedStudent.get().getSelectSubjects()) {
            System.out.println(index);
        }
    }

    // 수강생의 정보 수정
    public void changeStudentInfo(){
        printAllStudnts();

        System.out.println("\n수정하려는 학생의 고유번호를 입력하세요.");

        String account = DisplayManager.getInstance().inputScanner(String.class);
        Optional<Student> selectedStudent = studentStore.stream().filter(student -> student.getAccount().equals(account)).findFirst();
        if (selectedStudent.isEmpty()) {
            System.out.println("해당 학생이 없습니다.(고유번호를 입력해주세요)");
            return;
        }
        System.out.println("\n수정할 정보");
        System.out.println("1. 이름\n2. 상태");
        int input = DisplayManager.getInstance().inputScanner(Integer.class);
        if (input == 1) {
            System.out.println("\n새로운 이름을 입력해주세요 ");
            String newName = DisplayManager.getInstance().inputScanner(String.class);
            selectedStudent.get().setName(newName);
            System.out.println("이름이 변경되었습니다 . : "+newName);
        }
        else if (input == 2) {
            System.out.println("\n새로운 상태를 입력해주세요 ");
            System.out.println("<<Green , Red, Yellow>>");
            String newState = DisplayManager.getInstance().inputScanner(String.class);
            if(!Arrays.stream(State.values()).map(Enum::name).toList().contains(newState)){
                System.out.println("잘못된 입력입니다. 정확한 상태를 입력해주세요");
                return;
            }
            State state = State.valueOf(newState);
            selectedStudent.get().setState(state);
            System.out.println("상태가 수정되었습니다. : "+state.name());
        }
    }

    // 상태별 수강생 목록 조회
    public void inquireStudentListByState(){
        System.out.println("조회할 상태를 선택하세요");
        System.out.println("Green\nRed\nYellow");

        String selectState = DisplayManager.getInstance().inputScanner(String.class);
        if(!Arrays.stream(State.values()).map(Enum::name).toList().contains(selectState)){
            System.out.println("잘못된 입력입니다. 정확한 상태를 입력해주세요");
            return;
        }
        State selectedState = State.valueOf(selectState);
        System.out.println("상태 : "+selectState);

        for(var student : studentStore){
            if(student.getState() == selectedState){
                System.out.println("이름 : "+student.getName()+" 상태 : "+student.getState().name());
            }
        }
    }

    // 수강생 정보 삭제
    public void deleteStudent(){
        printAllStudnts();
        System.out.println("정보를 삭제할 수강생을 입력해주세요");

        String account = DisplayManager.getInstance().inputScanner(String.class);
        Optional<Student> selectedStudent = studentStore.stream().filter(student -> student.getAccount().equals(account)).findFirst();
        if (selectedStudent.isEmpty()) {
            System.out.println("해당 학생이 없습니다.(고유번호를 입력해주세요)");
            return;
        }
        studentStore.remove(selectedStudent.get());
    }

    // 학생의 점수를 등록하기 위한 리스트 띄우기
    public void allStudentsListForRegistScore() {
        printAllStudnts();
        String account = DisplayManager.getInstance().inputScanner(String.class);
        Optional<Student> selectedStudent = studentStore.stream().filter(student -> student.getAccount().equals(account)).findFirst();
        selectedStudent.ifPresentOrElse(Student::registScore,() -> System.out.println("해당 학생이 없습니다.(고유번호를 입력해주세요)"));
    }

    // 학생의 점수를 변경하기 위한 리스트 띄우기
    public void allStudentsListForChangeScore() {
        printAllStudnts();

        String account = DisplayManager.getInstance().inputScanner(String.class);
        Optional<Student> selectedStudent = studentStore.stream().filter(student -> student.getAccount().equals(account)).findFirst();
        selectedStudent.ifPresentOrElse(Student::updateRoundScoreBySubject,()-> System.out.println("해당 학생이 없습니다.(고유번호를 입력해주세요)"));
    }

    // 수강생의 특정과목 회차별 등급조회를 위한 메서드
    public void allStudentsListForInquireRoundGradeBySubject() {
        printAllStudnts();

        String account = DisplayManager.getInstance().inputScanner(String.class);
        Optional<Student> selectedStudent = studentStore.stream().filter(student -> student.getAccount().equals(account)).findFirst();
        selectedStudent.ifPresentOrElse(Student::inquireRoundGradeBySubject,()-> System.out.println("해당 학생이 없습니다.(고유번호를 입력해주세요)"));
    }

    // 과목을 추가하기 위한 메서드
    public void addSubject(){
        printAllStudnts();

        String account = DisplayManager.getInstance().inputScanner(String.class);
        Optional<Student> selectedStudent = studentStore.stream().filter(student -> student.getAccount().equals(account)).findFirst();
        if(selectedStudent.isEmpty())
        {
            System.out.println("해당 학생이 없습니다.(고유번호를 입력해주세요)");
            return;
        }

        printAllSubjects();
        SubjectType newSubject = selectSubject();

        if(newSubject == null){
            System.out.println("해당 과목이 없습니다.\n\n");
            return;
        }

        if(selectedStudent.get().getSelectSubjects().contains(newSubject.getSubjectName())){
            System.out.println("이미 해당 과목을 선택하셨습니다.\n\n");
            return;
        }

        System.out.println("\n\n"+newSubject.getSubjectName()+"을(를) 선택하셨습니다.\n\n");
        selectedStudent.get().addSubject(newSubject);
    }

    // 과목별 평균 등급 조회
    public void inquireAvgScoreBySubject(){
        System.out.println("과목을 선택해주세요");
        printAllSubjects();
        SubjectType subjectType = selectSubject();
        if(subjectType == null){
            System.out.println("선택한 과목이 없습니다. 정확하게 입력해주세요");
            return;
        }

        double avg = 0;
        int count =0;
        for(var student : studentStore){
            if(student.getSelectSubjects().contains(subjectType.getSubjectName())){
                List<Score> scoreList = student.getScoreList(subjectType.getSubjectName());
                avg += scoreList.stream().mapToDouble(Score::getScore).sum();
                count += scoreList.size();
            }
        }
        if(count ==0){
            System.out.println("해당 과목을 선택한 학생이 없습니다.");
            return;
        }
        avg /= count;
        System.out.println(subjectType.getSubjectName()+"의 평균 점수 : "+avg);
        System.out.println(subjectType.getSubjectName()+"의 평균 등급 : "+Score.calculateScoreRank((int)avg,subjectType));
    }

    // 상태별 수강생들의 과목 평균등급 조회
    public void inquireAvgScoreRankBySubjectType(){
        System.out.println("조회할 상태를 선택하세요");
        System.out.println("Green\nRed\nYellow");

        String selectState = DisplayManager.getInstance().inputScanner(String.class);
        if(!Arrays.stream(State.values()).map(Enum::name).toList().contains(selectState)){
            System.out.println("잘못된 입력입니다. 정확한 상태를 입력해주세요");
            return;
        }
        State selectedState = State.valueOf(selectState);
        System.out.println("상태 : "+selectedState);

        System.out.println("열람할 과목의 타입을 선택해 주세요");
        for(var type : SubjectType.subjectTypeArr){
            System.out.println(type);
        }

        // 원하는 과목 타입 입력
        String selectChoice = DisplayManager.getInstance().inputScanner(String.class).toUpperCase();

        // SubjectType 클래스 안에 static으로 선언한 subjectTypeArr을 돌며 같은 값이 있나 확인
        // 없으면 return
        if (!Arrays.stream(SubjectType.subjectTypeArr).toList().contains(selectChoice)) {
            System.out.println("잘못된 입력입니다. 정확한 상태를 입력해주세요.");
            return;
        }

        // 선택한 타입인 과목 리스트 생성
        List<String> wantTypeSubjects = subjectStore.stream().filter(x -> x.getSubjectType().
                getSubjectType().
                equals(selectChoice)).
                map(x -> x.getSubjectType().getSubjectName()).toList();


        // 논리적으로 무조건 있기 때문에 null체크 없이 바로 get()
        Subject basicType = subjectStore.stream().filter(x-> Objects.equals(x.getSubjectType().getSubjectName(), wantTypeSubjects.get(0))).findFirst().get();

        for(var student : studentStore){
            double avg =0;
            int count =0;

            for (var subject : student.getSelectSubjects()) {
                if (wantTypeSubjects.contains(subject)) {
                    avg += student.getScoreList(subject).stream().mapToDouble(Score::getScore).sum();
                    count += student.getScoreList(subject).size();
                }
            }
            avg /= count;
            System.out.println(student.getName() + "의 " + selectChoice + "과목 평균 점수 : " + avg + " 평균 등급 : " + Score.calculateScoreRank((int) avg, basicType.getSubjectType()));
        }

    }

    private void printAllStudnts() {
        if (studentStore == null || studentStore.isEmpty()) {
            System.out.println("===========등록된 수강생이 없습니다. 수강생을 등록해주세요===========\n");
            return;
        } else {
            System.out.println("\n=======학생의 리스트=========\n\n");
            for (var student : studentStore) {
                System.out.println(student.getName() + " : " + student.getAccount());
            }
        }
    }

    private SubjectType selectSubject(){

        System.out.println("\n원하는 과목을 입력하세요");

        String selectSubject = DisplayManager.getInstance().inputScanner(String.class);
        for(var index : subjectStore) {
            if(index.getSubjectType().getSubjectName().equals(selectSubject)) {
                return index.getSubjectType();
            }
        }
        return null;
    }
    private SubjectType selectSubject(boolean isMandatory) {

        System.out.println("\n원하는 과목을 입력하세요");

        String selectSubject = DisplayManager.getInstance().inputScanner(String.class);
        for(var index : subjectStore) {
            if(index.getSubjectType().getSubjectName().equals(selectSubject)) {
                return index.getSubjectType();
            }
        }
        return null;
    }

    private void addSubjectWhenCreateNewStudent(Student student) {
        int i =0;
        while(i<3){
            printAllSubjects(true);
            SubjectType newSubject = selectSubject(true);
            if(newSubject == null){
                System.out.println("해당 과목이 없습니다.\n\n");
                continue;
            }
            if(student.getSelectSubjects().contains(newSubject.getSubjectName())){
                System.out.println("이미 해당 과목을 선택하셨습니다.\n\n");
                continue;
            }

            if(newSubject.getSubjectType().equals("CHOICE")){
                System.out.println("\n해당 과목은 선택과목이므로, 필수 과목을 입력해 주세요.");
                continue;
            }

            System.out.println("\n\n"+newSubject.getSubjectName()+"을(를) 선택하셨습니다.\n\n");
            student.addSubject(newSubject);
            i++;
        }

        i =0;
        while(i<2){
            printAllSubjects(false);
            SubjectType newSubject = selectSubject(false);
            if(newSubject == null){
                System.out.println("해당 과목이 없습니다.\n\n");
                continue;
            }
            if(student.getSelectSubjects().contains(newSubject.getSubjectName())){
                System.out.println("이미 해당 과목을 선택하셨습니다.\n\n");
                continue;
            }

            if(newSubject.getSubjectType().equals("MANDATORY")){
                System.out.println("\n해당 과목은 필수과목이므로, 선택 과목을 입력해 주세요.");
                continue;
            }

            System.out.println("\n"+newSubject.getSubjectName()+"을(를) 선택하셨습니다.\n\n");
            student.addSubject(newSubject);
            i++;
        }
    }

    private void printAllSubjects(){
        System.out.println("\n======과목 리스트======\n\n");
        for(var index : subjectStore) {
                System.out.println(index.getSubjectType().getSubjectName());
        }
    }

    private void printAllSubjects(boolean isMandatory){
        System.out.println("\n======과목 리스트======\n\n");
        String type ="";
        if(isMandatory)
            type="MANDATORY";
        else
            type="CHOICE";

        for(var index : subjectStore) {
            if(Objects.equals(index.getSubjectType().getSubjectType(), type)){
                System.out.println(index.getSubjectType().getSubjectName());
            }
        }
    }
}