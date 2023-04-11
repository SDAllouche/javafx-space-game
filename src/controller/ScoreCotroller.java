package controller;

import Model.Score;
import com.sun.deploy.util.StringUtils;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScoreCotroller {

    private static Connection connection;

    static {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/game","root","");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public ScoreCotroller() {
    }

    public Score save(Score s) throws SQLException {

        PreparedStatement statement=connection.prepareStatement("insert into score(name,score,date) values (?,?,?)");
        statement.setString(1,s.getName());
        statement.setInt(2,s.getScore());
        statement.setTimestamp(3, (Timestamp) s.getDate());
        statement.executeUpdate();

        return s;
    }

    public List<Score> findAll() throws SQLException {
        PreparedStatement statement=connection.prepareStatement("select * from score order by score DESC");
        ResultSet resultSet=statement.executeQuery();
        List<Score> scores=new ArrayList<>();
        while (resultSet.next()){

            Score score=new Score();
            score.setId(resultSet.getLong("id"));
            score.setName(resultSet.getString("name"));
            score.setScore(resultSet.getInt("score"));
            score.setDate(resultSet.getTimestamp("date"));
            scores.add(score);
        }

        if (scores.size()>10) return scores.subList(0,10);
        else  return scores;
    }

    public static void main(String[] args) throws SQLException {
        ScoreCotroller scoreCotroller=new ScoreCotroller();
    }

}
