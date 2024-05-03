package camp.model;

public class Student {
    private String studentId;
    private String studentName;

    public Student(String seq, String studentName) {
        this.studentId = seq;
        this.studentName = studentName;
    }

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {

    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {

    }

}
