package camp.model;

import camp.Controller.InitializeManager;
import camp.Controller.StudentManager;
import camp.View.DisplayManager;
import java.util.*;

public class Student {
    private String account;
    private String name;

    private Map<String, List<Score>> scores;

    public Student(String account, String name) {
        this.account = account;
        this.name = name;
        this.scores = new HashMap<String, List<Score>>();// 아직 이 맵 안에 List는 인스턴스가 없는 상태
    }

    public String getAccount() {
        return account;
    }
    public void setAccount(String wantAccount) {
        this.account = wantAccount;

    }

    public String getName(){
        return name;
    }

    public boolean setName(String name){
        if (name != null && !name.isEmpty()) {
            this.name = name;
            return true;
        }
        return false;
    }

    public List<String> getSelectSubjects(){
        return scores.keySet().stream().toList();
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

        int roundInput = 1;
        if (!isValidToChangeScore(subjectInput)) {
            System.out.println("등록된 회차가 없습니다");
            return false;
        }

        System.out.println("점수를 입력해주세요");
        int inputScore = DisplayManager.getInstance().inputScanner(Integer.class);

        if(inputScore < 0 || inputScore > 100) {
            System.out.println("올바르지 않은 점수입니다. (0 ~ 100 범위)");
            return false;
        }

        // 선택한 과목의 해당 회차 점수 수정
        if(!scores.get(subjectInput).isEmpty()){
            Score selectedScore = scores.get(subjectInput).get(roundInput-1); // 인덱스가 없을수도 있음
            selectedScore.setScore(inputScore, selectSubject.getSubjectType());
        }
        else{
            String scoreAccount = InitializeManager.getInstance().sequence(InitializeManager.getInstance().INDEX_TYPE_SCORE);
            Score newScore = new Score(scoreAccount,account,roundInput-1,inputScore,selectSubject.getSubjectType());
        }

        System.out.println("점수가 성공적으로 수정되었습니다.");
        return true;
    }

    // 수강생의 특정 과목 회차별 등급 조회
    public boolean inquireRoundGradeBySubject() {
        printSelectSubjects();
        getWantSubject("조회할 과목을 입력해주세요");
        return true;
    }

    // 점수 등록
    public boolean registScore(){
        printSelectSubjects();

        //    원하는 과목 입력 받기
        String subjectInput = getWantSubject("등록할 과목을 입력해주세요");
        Subject selectSubject = StudentManager.getInstance().getSubjectStore(subjectInput);

        // 회차 계산
        int nextRound = scores.get(subjectInput).size()+1;

        // 점수 입력 받기
        System.out.println(nextRound+"회차 점수를 입력해주세요: ");

        int score = DisplayManager.getInstance().inputScanner(Integer.class);
        if(score < 0 || score > 100) {
            System.out.println("올바르지 않은 점수입니다. (0 ~ 100 범위)");
            return false;
        }

        // 점수 등록
        String scoreAccount = InitializeManager.getInstance().sequence(InitializeManager.getInstance().INDEX_TYPE_SCORE);
        Score newScore = new Score(scoreAccount,account,nextRound,score,selectSubject.getSubjectType());
        scores.get(subjectInput).add(newScore);

        System.out.println("점수가 성공적으로 등록되었습니다.");
        return true;
    }

    private void printSelectSubjects(){
        // 학생이 선택한 과목 리스트 출력
        System.out.println("\n========== 선택한 과목 목록 ===========\n");
        int index = 1;
        for(var key : scores.keySet()){
            System.out.println(index + ". " + key);
            index++;
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
        // 선택한 과목
        System.out.println("선택된 과목명: " + subjectInput);

        int index =1;
        for(var value : scores.get(subjectInput)) {
            System.out.println(index + ". 회차 등급 : "+ value.getScore());
            index++;
        }

        return subjectInput;
    }

    private boolean isValidToChangeScore(String subjectName){
        return !scores.get(subjectName).isEmpty();
    }
}