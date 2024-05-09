package camp.Model;

public class Score {
    private String account;
    private String studentAccount;
    private int round;
    private int score;
    private ScoreRank scoreRank;

    public Score(String account, String studentAccount, int round, int score,SubjectType subjectType) {
        this.account = account;
        this.studentAccount = studentAccount;
        this.round = round;
        this.score = score;
        setScore(score,subjectType);
    }

    public String getAccount() {
        return account;
    }

    public String getStudentAccount() {
        return studentAccount;
    }

    public int getRound() {
        return round;
    }

    public int getScore() {
        return score;
    }

    public static ScoreRank calculateScoreRank(int score, SubjectType subjectType){
        ScoreRank rank = null;
        if (subjectType.getSubjectType().equals("MANDATORY")) {
            // 필수 과목의 등급 기준에 따라 랭크 계산
            if (score >= 95) {
                rank = ScoreRank.A;
            } else if (score >= 90) {
                rank = ScoreRank.B;
            } else if (score >= 80) {
                rank = ScoreRank.C;
            } else if (score >= 70) {
                rank = ScoreRank.D;
            } else if (score >= 60) {
                rank = ScoreRank.E;
            } else {
                rank = ScoreRank.F;
            }
        } else if(subjectType.getSubjectType().equals("CHOICE")){
            // 선택 과목의 등급 기준에 따라 랭크 계산
            if (score >= 90) {
                rank = ScoreRank.A;
            } else if (score >= 80) {
                rank = ScoreRank.B;
            } else if (score >= 70) {
                rank = ScoreRank.C;
            } else if (score >= 60) {
                rank = ScoreRank.D;
            } else if (score >= 50) {
                rank = ScoreRank.E;
            } else {
                rank = ScoreRank.F;
            }
        }
        return rank;
    }

    public void setScore(int score, SubjectType subjectType) {
        this.scoreRank = calculateScoreRank(score,subjectType);
        this.score = score;
    }

    public ScoreRank getScoreRank() {
        return scoreRank;
    }
}
