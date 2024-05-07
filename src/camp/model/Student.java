package camp.model;

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
        String subjectName = selectedSubjects.get(subjectInput - 1).getSubjectName();
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

        System.out.println("점수가 성공적으로 수정되었습니다.");
        return true;
    }

    // 수강생의 특정 과목 회차별 등급 조회
    public void inquireRoundGradeBySubject() {

    }

    public void registScore(){

    }

    public void changeScore(){

    }

}