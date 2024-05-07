package camp.model;

public class Score {
    private String account;
    private int studentAccount;
    private int round;
    private int score;
    private ScoreRank scoreRank;

    public Score(String account, int studentAccount, int round, int score, ScoreRank scoreRank) {
        this.account = account;
        this.studentAccount = studentAccount;
        this.round = round;
        this.score = score;
        this.scoreRank = scoreRank;
    }

    // Getter
    public String account() {
        return account;
    }

    public int studentAccount() {
        return studentAccount;
    }

    public int round() {
        return round;
    }

    public int score() {
        return score;
    }

    public ScoreRank scoreRank() {
        return scoreRank;
    }
}
