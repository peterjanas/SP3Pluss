package org.example;

import java.sql.*;
import java.util.*;


import java.util.ArrayList;
import java.util.List;

public class DBIO {
    //mysql:mysql-connector-java:RELEASE

    static final String DB_URL = "jdbc:mysql://localhost/mystreaming";
    static final String USER = "root";
    static final String PASS = "14Tempusedaxrerum";
    protected final ArrayList<Movie> movies;
    protected final ArrayList<Series> series;
    private HashMap<String, String> users;
    Setup setup;

    public DBIO(Setup setup) {
        this.setup = setup;
        this.users = setup.getUsers();
        this.movies = setup.getMovies();
        this.series = setup.getSeries();

    }


    public void readDataMovies() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT * FROM movie";
            stmt = conn.prepareStatement((sql));

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                int year = rs.getInt("year");
                String[] genres = rs.getString("genre").strip().split(",");
                ArrayList<String> genre = new ArrayList<>(List.of(genres));
                double rating = rs.getDouble("rating");
                int movieID = rs.getInt("movieID");
                Movie m = new Movie(name, year, genre, rating, movieID);
                movies.add(m);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        }
    }

    public void readDataSeries() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);


            String sql = "SELECT * FROM series";
            stmt = conn.prepareStatement((sql));

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String runtime = rs.getString("runtime");
                String[] genres = rs.getString("genre").strip().split(",");
                ArrayList<String> genre = new ArrayList<>(List.of(genres));
                double rating = rs.getDouble("rating");
                String seasonAndEpisode = rs.getString("seasonAndEpisode");
                int seriesID = rs.getInt("seriesID");
                Series s = new Series(name, runtime, genre, rating, seasonAndEpisode, seriesID);
                series.add(s);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        }
    }

    public void loadUser() {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT * FROM user";
            stmt = conn.prepareStatement((sql));

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                String username = rs.getString("username");
                String password = rs.getString("password");
                users.put(username, password);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        }
    }

    public void saveUser(String username, String password) {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "INSERT INTO user (username,password) VALUES (?,?)";
            stmt = conn.prepareStatement((sql));
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();


            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
        }
    }

    public HashMap<String, String> getUsers() {
        return users;
    }
}
