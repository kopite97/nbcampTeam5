package camp.model;

import camp.Controller.InitializeManager;

import java.util.*;


public class Student {
    private String account;
    private String name;
    private List<Subject> selectedSubjects;
    private Map<String, List<Score>> scores;
    Scanner sc;

    public Student(String account, String name) {
        this.account = account;
        this.name = name;
        this.selectedSubjects = new ArrayList<Subject>();
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

    public List<Subject> getSelectSubjects(){
        return selectedSubjects;
    }
    public void addSelectSubject(Subject wantSubject){
        selectedSubjects.add(wantSubject);
    }



    // 수강생의 과목별 회차 점수 수정
    public boolean updateRoundScoreBySubject() {
        // 학생이 선택한 과목 리스트 출력
        System.out.println("\n========== 선택한 과목 목록 ===========\n");
        int index = 1;
        for(var subject : selectedSubjects) {
            System.out.println(index + ". " + subject.getSubjectName());
            index++;
        }
        //    원하는 과목 입력 받기
        System.out.println("수정할 과목의 번호를 입력해주세요");
        int subjectInput = sc.nextInt();
        if(subjectInput < 1 || subjectInput > selectedSubjects.size()) {
            System.out.println("올바르지 않은 과목 번호입니다.");
            return false;
        }

        // 선택한 과목명
        Subject subject = selectedSubjects.get(subjectInput - 1);
        String subjectName = subject.getSubjectName();
        System.out.println("선택된 과목명: " + subjectName);

        // 선택한 과목의 회차 목록 출력
        List<Score> scoreList = scores.get(subjectName);
        index = 1;

        for (Score score : scoreList) {
            System.out.println(index + ". 회차 점수 : "+score.getScore());
            index++;
        }
        System.out.println("수정할 회차의 번호 입력해주세요");
        int roundInput = sc.nextInt();

        if(roundInput < 1 || roundInput > scoreList.size()) {
            System.out.println("올바르지 않은 회차 번호입니다.");
            return false;
        }

        System.out.println("수정할 점수를 입력해주세요");
        int newScore = sc.nextInt();

        if(newScore < 0 || newScore > 100) {
            System.out.println("올바르지 않은 점수입니다. (0 ~ 100 범위)");
            return false;
        }

        // 선택한 과목의 해당 회차 점수 수정
        Score selectedScore = scoreList.get(roundInput - 1);
        selectedScore.setScore(newScore);
        selectedScore.setScoreRank(calculateRank(newScore,subject.getSubjectType()));


        System.out.println("점수가 성공적으로 수정되었습니다.");
        return true;
    }

    // 수강생의 특정 과목 회차별 등급 조회
    public boolean inquireRoundGradeBySubject() {
        // 학생이 선택한 과목 리스트 출력
        System.out.println("\n========== 선택한 과목 목록 ===========\n");
        int index = 1;
        for(var subject : selectedSubjects) {
            System.out.println(index + ". " + subject.getSubjectName());
            index++;
        }
        //    원하는 과목 입력 받기
        System.out.println("등급을 확인할 과목의 번호를 입력해주세요");
        int subjectInput = sc.nextInt();
        if(subjectInput < 1 || subjectInput > selectedSubjects.size()) {
            System.out.println("올바르지 않은 과목 번호입니다.");
            return false;
        }

        // 선택한 과목명
        Subject subject = selectedSubjects.get(subjectInput - 1);
        String subjectName = subject.getSubjectName();
        System.out.println("선택된 과목명: " + subjectName);

        // 선택한 과목의 회차 점수,등급 출력
        List<Score> scoreList = scores.get(subjectName);
        index = 1;

        for (Score score : scoreList) {
            System.out.println(index + ". 회차 등급 : "+score.getRank());
            index++;
        }
        return true;
    }

    // 점수 등록
    public boolean registScore(){
        // 학생이 선택한 과목 리스트 출력
        System.out.println("\n========== 선택한 과목 목록 ===========\n");
        int index = 1;
        for(var subject : selectedSubjects) {
            System.out.println(index + ". " + subject.getSubjectName());
            index++;
        }
        //    원하는 과목 입력 받기
        System.out.println("점수를 등록할 과목의 번호를 입력해주세요");
        int subjectInput = sc.nextInt();
        if(subjectInput < 1 || subjectInput > selectedSubjects.size()) {
            System.out.println("올바르지 않은 과목 번호입니다.");
            return false;
        }

        // 선택한 과목
        Subject subject = selectedSubjects.get(subjectInput - 1);
        String subjectName = subject.getSubjectName();
        System.out.println("선택된 과목명: " + subjectName);

        // 선택한 과목의 회차 목록 출력
        List<Score> scoreList = scores.get(subjectName);

        // 회차 계산
        int nextRound = scoreList.size() + 1;

        // 점수 입력 받기
        System.out.println(nextRound+"회차 점수를 입력해주세요: ");

        int score = sc.nextInt();
        if(score < 0 || score > 100) {
            System.out.println("올바르지 않은 점수입니다. (0 ~ 100 범위)");
            return false;
        }

        // 점수 등록
        String scoreAccount = InitializeManager.getInstance().sequence(InitializeManager.getInstance().INDEX_TYPE_SCORE);
        Score newScore = new Score(scoreAccount, this.account, nextRound, score,calculateRank(score,subject.getSubjectType()));
        scoreList.add(newScore);

        System.out.println("점수가 성공적으로 등록되었습니다.");
        return true;
    }

    private ScoreRank calculateRank(int score, String subjectType){
        if (subjectType.equals(SubjectType.Mandatory)) {
            // 필수 과목의 등급 기준에 따라 랭크 계산
            if (score >= 95) {
                return ScoreRank.A;
            } else if (score >= 90) {
                return ScoreRank.B;
            } else if (score >= 80) {
                return ScoreRank.C;
            } else if (score >= 70) {
                return ScoreRank.D;
            } else if (score >= 60) {
                return ScoreRank.F;
            } else {
                return ScoreRank.N;
            }
        } else {
            // 선택 과목의 등급 기준에 따라 랭크 계산
            if (score >= 90) {
                return ScoreRank.A;
            } else if (score >= 80) {
                return ScoreRank.B;
            } else if (score >= 70) {
                return ScoreRank.C;
            } else if (score >= 60) {
                return ScoreRank.D;
            } else if (score >= 50) {
                return ScoreRank.F;
            } else {
                return ScoreRank.N;
            }
        }
    }


}