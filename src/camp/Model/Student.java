package camp.Model;

import camp.Controller.InitializeManager;
import camp.Controller.StudentManager;
import camp.View.DisplayManager;
import java.util.*;

public class Student {
    private String account;
    private String name;
    private State state;

    private Map<String, List<Score>> scores;

    public Student(String account, String name) {
        this.account = account;
        this.name = name;
        this.scores = new HashMap<String, List<Score>>();// 아직 이 맵 안에 List는 인스턴스가 없는 상태
        state = State.Green;
    }

    public String getAccount() {
        return account;
    }

    public String getName(){
        return name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean setName(String name){
        if (name != null && !name.isEmpty()) {
            this.name = name;
            return true;
        }
        return false;
    }

    public Set<String> getSelectSubjects(){
        return scores.keySet();
    }

    public void addSubject(SubjectType wantSubject){
        if(scores.containsKey(wantSubject.getSubjectName()))
            return;

        scores.put(wantSubject.getSubjectName(), new ArrayList<>());
    }



    // 수강생의 과목별 회차 점수 수정
    public boolean updateRoundScoreBySubject() {
        printSelectSubjects();

        String subjectInput = getWantSubject("수정할 과목을 입력해주세요");
        Subject selectSubject = StudentManager.getInstance().getSubjectStore(subjectInput);
        if(!isExistSubject(subjectInput))
            return false;

        int roundInput = 1;
        if (!isValidToChangeScore(subjectInput)) {
            System.out.println("등록된 회차가 없습니다");
            return false;
        }
        int inputRound =0;
        System.out.println("\n수정할 회차를 입력해주세요.");
        try{
            inputRound = DisplayManager.getInstance().inputScanner(Integer.class);
        }
        catch (Exception e){
            return false;
        }
        if(inputRound<1 || inputRound>scores.get(subjectInput).size()){
            System.out.println("해당 회차는 등록되지 않았습니다.");
            return false;
        }

        System.out.println("점수를 입력해주세요");
        int inputScore = 0;

        try {
            inputScore = DisplayManager.getInstance().inputScanner(Integer.class);
        }
        catch (Exception e) {
            return false;
        }

        if(inputScore < 0 || inputScore > 100) {
            System.out.println("올바르지 않은 점수입니다. (0 ~ 100 범위)");
            return false;
        }

        scores.get(subjectInput).get(inputRound-1).setScore(inputScore,selectSubject.getSubjectType());

        System.out.println("점수가 성공적으로 수정되었습니다.");
        return true;
    }

    // 수강생의 특정 과목 회차별 등급 조회
    public boolean inquireRoundGradeBySubject() {
        printSelectSubjects();
        String subjectInput = getWantSubject("조회할 과목을 입력해주세요");
        if(!isExistSubject(subjectInput))
            return false;
        return true;
    }

    // 점수 조회
    public List<Score> getScoreList(String wantSubject) {
        return scores.get(wantSubject);
    }

    // 점수 등록
    public boolean registScore(){
        printSelectSubjects();

        //    원하는 과목 입력 받기
        String subjectInput = getWantSubject("등록할 과목을 입력해주세요");
        Subject selectSubject = StudentManager.getInstance().getSubjectStore(subjectInput);
        if(!isExistSubject(subjectInput))
            return false;

        // 회차 계산
        int nextRound = scores.get(subjectInput).size()+1;

        if(nextRound>10){
            System.out.println("10회차를 모두 등록하셨습니다.");
            return false;
        }

        // 점수 입력 받기
        System.out.println(nextRound+"회차 점수를 입력해주세요: ");

        int score =0;
        try{
            score = DisplayManager.getInstance().inputScanner(Integer.class);
        }
        catch (Exception e){
            return false;
        }

        if(score < 0 || score > 100) {
            System.out.println("올바르지 않은 점수입니다. (0 ~ 100 범위)");
            return false;
        }

        // 점수 등록
        String scoreAccount = selectSubject.getSubjectId();
        Score newScore = new Score(scoreAccount,account,nextRound,score,selectSubject.getSubjectType());
        scores.get(subjectInput).add(newScore);

        System.out.println("점수가 성공적으로 등록되었습니다.");
        return true;
    }

    private void printSelectSubjects(){
        // 학생이 선택한 과목 리스트 출력
        System.out.println("\n========== 선택한 과목 목록 ===========\n");
        for(var key : scores.keySet()){
            System.out.println(key);
        }
    }

    private String getWantSubject(String wantText){
        //    원하는 과목 입력 받기
        System.out.println(wantText);
        String subjectInput = DisplayManager.getInstance().inputScanner(String.class);

        if(!scores.containsKey(subjectInput)) {
            System.out.println("올바르지 않은 과목 번호입니다.");
            return "";
        }
        printScoreByRound(subjectInput);
        return subjectInput;
    }

    private void printScoreByRound(String wantSubject){
        // 선택한 과목
        int index =1;
        if (scores.get(wantSubject).isEmpty()) {
            System.out.println("등록된 점수가 없습니다");
            return;
        }
        
        for(var value : scores.get(wantSubject)) {
            System.out.println(index + "회차 점수 : "+ value.getScore() +" 등급 : "+value.getScoreRank());
            index++;
        }
    }

    private boolean isValidToChangeScore(String subjectName){
        return !scores.get(subjectName).isEmpty();
    }
    private boolean isExistSubject(String subjectName){
        return scores.containsKey(subjectName);
    }
}