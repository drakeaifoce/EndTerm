package com.company.repositories;

import com.company.database.interfaces.IDB;
import com.company.enteties.Question;
import com.company.repositories.interfaces.IQuestionRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kuanyshbek
 */

public class QuestionRepository implements IQuestionRepository {
    private final IDB db;

    public QuestionRepository(IDB db) {
        this.db = db;
    }
    /**
     This code intended to add the questions to the DB
     */
    @Override
    public boolean createQuestion(Question question) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO questions(question, answer, type) VALUES (?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, question.getQuestion());
            st.setString(2, question.getAnswer());
            st.setString(3, question.getType());

            boolean executed = st.execute();
            return executed;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }
    /**
     This code is for getting questions from the database
     */
    @Override
    public Question getQuestion(int id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id,question,answer,type FROM questions WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Question question = new Question(rs.getInt("id"),
                        rs.getString("question"),
                        rs.getString("answer"),
                        rs.getString("type"));

                return question;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
    /**
    This code is for getting  all questions from the database
     */
    @Override
    public List<Question> getAllQuestions() {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id,question,answer,type FROM questions";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Question> questions = new ArrayList<>();
            while (rs.next()) {
                Question question = new Question(rs.getInt("id"),
                        rs.getString("question"),
                        rs.getString("answer"),
                        rs.getString("type"));
                questions.add(question);
            }

            return questions;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
}
