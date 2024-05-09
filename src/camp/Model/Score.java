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

    public void setScore(int score, SubjectType subjectType) {
        if (subjectType.getSubjectType().equals("MANDATORY")) {
            // 필수 과목의 등급 기준에 따라 랭크 계산
            if (score >= 95) {
                scoreRank = ScoreRank.A;
            } else if (score >= 90) {
                scoreRank = ScoreRank.B;
            } else if (score >= 80) {
                scoreRank = ScoreRank.C;
            } else if (score >= 70) {
                scoreRank = ScoreRank.D;
            } else if (score >= 60) {
                scoreRank = ScoreRank.E;
            } else {
                scoreRank = ScoreRank.F;
            }
        } else if(subjectType.getSubjectType().equals("CHOICE")){
            // 선택 과목의 등급 기준에 따라 랭크 계산
            if (score >= 90) {
                scoreRank = ScoreRank.A;
            } else if (score >= 80) {
                scoreRank = ScoreRank.B;
            } else if (score >= 70) {
                scoreRank = ScoreRank.C;
            } else if (score >= 60) {
                scoreRank = ScoreRank.D;
            } else if (score >= 50) {
                scoreRank = ScoreRank.E;
            } else {
                scoreRank = ScoreRank.F;
            }
        }
        this.score = score;
    }

    public ScoreRank getScoreRank() {
        return scoreRank;
    }
}
