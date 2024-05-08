package camp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    private String account;
    private String name;
    private List<Subject> selectedSubjects;
    private Map<String, List<Score>> scores;

    public Student(String account, String name) {
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

    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }

    // 수강생의 과목별 회차 점수 수정
    public void updateRoundScoreBySubject() {

    }

    // 수강생의 특정 과목 회차별 등급 조회
    public void inquireRoundGradeBySubject() {

    }

}
