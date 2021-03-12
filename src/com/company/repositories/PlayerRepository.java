package com.company.repositories;

import com.company.database.interfaces.IDB;
import com.company.enteties.Player;
import com.company.enteties.Question;
import com.company.repositories.interfaces.IPlayerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Darkhan
 */

public class PlayerRepository implements IPlayerRepository {
    private final IDB db;

    public PlayerRepository(IDB db) {
        this.db = db;
    }
    /**
     Here, we fill in the players in the database with the help of the try and catch
     */
    @Override
    public boolean registerPlayer(Player player) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO solo(username, score) VALUES (?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, player.getUsername());
            st.setInt(2, player.getScore());

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

    @Override
    public Player getPlayer(int id) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id,username,score FROM solo WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Player player = new Player(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getInt("score"));

                return player;
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
     This code we add points to the player for correct answers via ID
     */
    public boolean addPoint(int id, int score) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "UPDATE solo SET score = ? WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, score);
            st.setInt(2, id);

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

    public int getId() {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT MAX(id) FROM solo";
            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int id = (rs.getInt("max"));

                return id;
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
        return 0;
    }
    /**
     This code output the scores of players with name, id
     */
    @Override
    public List<Player> getScoreboard() {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id,username,score FROM solo ORDER BY score DESC";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Player> players = new ArrayList<>();
            while (rs.next()) {
                Player player = new Player(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getInt("score"));
                players.add(player);
            }

            return players;
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
