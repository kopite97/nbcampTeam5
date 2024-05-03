package camp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    private int account;
    private String name;
    private List<Subject> selectedSubjects;
    private Map<String, List<Score>> scores;

    public Student(int account, String name) {
        this.account = account;
        this.name = name;
        this.selectedSubjects = new ArrayList<Subject>();
        this.scores = new HashMap<String, List<Score>>();// 아직 이 맵 안에 List는 인스턴스가 없는 상태
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getAccount() {
        return account;
    }
    public void setAccount(int account) {
        this.account = account;
    }



    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {

    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {

    }

}
