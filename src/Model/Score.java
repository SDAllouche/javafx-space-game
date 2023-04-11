package Model;

import java.sql.Timestamp;
import java.util.Date;

public class Score {

    private long id;
    private String name;
    private int score;
    private Timestamp date;

    public Score() {
    }

    public Score(String name, int score, Timestamp date) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
