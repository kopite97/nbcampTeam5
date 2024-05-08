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

    public void addSelectSubject(String wantSubject){
        //selectedSubjects.add(wantSubject);
        if(scores.containsKey(wantSubject))
            return;

        scores.put(wantSubject,new ArrayList<>());
    }



    // 수강생의 과목별 회차 점수 수정
    public boolean updateRoundScoreBySubject() {
        // 학생이 선택한 과목 리스트 출력
        System.out.println("\n========== 선택한 과목 목록 ===========\n");
        int index = 1;

        for(var key : scores.keySet()){
            System.out.println(index + ". " + key);
            index++;
        }

        //    원하는 과목 입력 받기
        System.out.println("수정할 과목을 입력해주세요");
        String subjectInput = DisplayManager.getInstance().inputScanner(String.class);

        if(scores.containsKey(subjectInput)) {
            System.out.println("올바르지 않은 과목 번호입니다.");
            return false;
        }
        Subject selectSubject = StudentManager.getInstance().getSubjectStore(subjectInput);

        // 선택한 과목명
        System.out.println("선택된 과목명: " + subjectInput);

        // 선택한 과목의 회차 목록 출력
        index = 1;

        for(var value : scores.get(subjectInput)) {
            System.out.println(index + ". 회차 점수 : "+value);
            index++;
        }
        System.out.println("수정할 회차의 번호 입력해주세요");
        int roundInput = DisplayManager.getInstance().inputScanner(Integer.class);

        if(roundInput < 1 || roundInput > scores.get(subjectInput).size()) {
            System.out.println("올바르지 않은 회차 번호입니다.");
            return false;
        }

        System.out.println("수정할 점수를 입력해주세요");
        int newScore = DisplayManager.getInstance().inputScanner(Integer.class);

        if(newScore < 0 || newScore > 100) {
            System.out.println("올바르지 않은 점수입니다. (0 ~ 100 범위)");
            return false;
        }

        // 선택한 과목의 해당 회차 점수 수정
        Score selectedScore = scores.get(subjectInput).get(roundInput-1);
        selectedScore.setScore(newScore, selectSubject.getSubjectType());
        //selectedScore.setScoreRank(calculateRank(newScore,subject.getSubjectType())); <- 얘는 Score에서 하는게 좋아보입니다

        System.out.println("점수가 성공적으로 수정되었습니다.");
        return true;
    }

    // 수강생의 특정 과목 회차별 등급 조회
    public boolean inquireRoundGradeBySubject() {
        // 학생이 선택한 과목 리스트 출력
        System.out.println("\n========== 선택한 과목 목록 ===========\n");
        int index = 1;

        for(var key : scores.keySet()){
            System.out.println(index + ". " + key);
            index++;
        }

        //    원하는 과목 입력 받기
        System.out.println("수정할 과목을 입력해주세요");
        String subjectInput = DisplayManager.getInstance().inputScanner(String.class);

        if(scores.containsKey(subjectInput)) {
            System.out.println("올바르지 않은 과목 번호입니다.");
            return false;
        }

        // 선택한 과목명
        System.out.println("선택된 과목명: " + subjectInput);

        // 선택한 과목의 회차 점수,등급 출력
        index = 1;
        for(var value : scores.get(subjectInput)) {
            System.out.println(index + ". 회차 등급 : "+ value);
            index++;
        }
        return true;
    }

    // 점수 등록
    public boolean registScore(){
        // 학생이 선택한 과목 리스트 출력
        System.out.println("\n========== 선택한 과목 목록 ===========\n");
        int index = 1;

        for(var key : scores.keySet()){
            System.out.println(index + ". " + key);
            index++;
        }

        //    원하는 과목 입력 받기
        System.out.println("수정할 과목을 입력해주세요");
        String subjectInput = DisplayManager.getInstance().inputScanner(String.class);

        if(scores.containsKey(subjectInput)) {
            System.out.println("올바르지 않은 과목 번호입니다.");
            return false;
        }
        Subject selectSubject = StudentManager.getInstance().getSubjectStore(subjectInput);

        // 선택한 과목
        System.out.println("선택된 과목명: " + subjectInput);

        // 선택한 과목의 회차 목록 출력
        index =1;
        for(var value : scores.get(subjectInput)) {
            System.out.println(index + ". 회차 등급 : "+ value);
            index++;
        }


        // 회차 계산
        int nextRound = scores.get(subjectInput).size();

        // 점수 입력 받기
        System.out.println(nextRound+"회차 점수를 입력해주세요: ");

        int score = DisplayManager.getInstance().inputScanner(Integer.class);
        if(score < 0 || score > 100) {
            System.out.println("올바르지 않은 점수입니다. (0 ~ 100 범위)");
            return false;
        }

        // 점수 등록
        String scoreAccount = InitializeManager.getInstance().sequence(InitializeManager.getInstance().INDEX_TYPE_SCORE);
        Score newScore = new Score(scoreAccount,score,nextRound,score,selectSubject.getSubjectType());
        scores.get(subjectInput).add(newScore);

        System.out.println("점수가 성공적으로 등록되었습니다.");
        return true;
    }
}