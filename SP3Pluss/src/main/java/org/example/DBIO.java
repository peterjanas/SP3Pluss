package org.example;

import java.io.File;
import java.sql.*;
import java.util.*;


import java.util.ArrayList;
import java.util.List;

public class DBIO {
    //mysql:mysql-connector-java:RELEASE

    static final String DB_URL = "jdbc:mysql://localhost/mystreaming";
    static final String USER = "root";
    static final String PASS = "14Tempusedaxrerum";
    protected final ArrayList<Movie> movieMedia = new ArrayList<>();
    protected final ArrayList<Series> seriesMedia = new ArrayList<>();


    public void readDataMovies() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("\nConnecting do database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);


            System.out.println("\nCreating statement...");
            String sql = "SELECT * FROM movie";
            stmt = conn.prepareStatement((sql));

            ResultSet rs = stmt.executeQuery();

            System.out.println("\nPrinting result:");
            while (rs.next()) {

                String name = rs.getString("name");
                int year = rs.getInt("year");
                String[] genres = rs.getString("genre").strip().split(",");
                ArrayList<String> genre = new ArrayList<>(List.of(genres));
                double rating = rs.getDouble("rating");
                int movieID = rs.getInt("movieID");
                Movie m = new Movie(name, year, genre, rating, movieID);
                movieMedia.add(m);

                //System.out.println("\nTitle: " + name + "\nYear: " + year + "\nGenre: " + genre + "\nRating: " + rating + "\nMovieID: " + movieID + "\n");
            }

            //System.out.println(movieMedia);
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

            System.out.println("\nConnecting do database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);


            System.out.println("\nCreating statement...");
            String sql = "SELECT * FROM series";
            stmt = conn.prepareStatement((sql));

            ResultSet rs = stmt.executeQuery();

            System.out.println("\nPrinting result:");
            while (rs.next()) {

                String name = rs.getString("name");
                String runtime = rs.getString("runtime");
                String[] genres = rs.getString("genre").strip().split(",");
                ArrayList<String> genre = new ArrayList<>(List.of(genres));
                double rating = rs.getDouble("rating");
                String seasonAndEpisode = rs.getString("seasonAndEpisode");
                int seriesID = rs.getInt("seriesID");
                Series s = new Series(name, runtime, genre, rating, seasonAndEpisode, seriesID);
                seriesMedia.add(s);

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


    public void saveUser() {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("\nConnecting do database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);


            System.out.println("\nCreating statement...");
            String sql = "INSERT INTO user VALUES ()";
            stmt = conn.prepareStatement((sql));

            ResultSet rs = stmt.executeQuery();

            System.out.println("\nPrinting result:");
            while (rs.next()) {

                String name = rs.getString("name");
                String runtime = rs.getString("runtime");
                String[] genres = rs.getString("genre").strip().split(",");
                ArrayList<String> genre = new ArrayList<>(List.of(genres));
                double rating = rs.getDouble("rating");
                String seasonAndEpisode = rs.getString("seasonAndEpisode");
                int seriesID = rs.getInt("seriesID");
                Series s = new Series(name, runtime, genre, rating, seasonAndEpisode, seriesID);
                seriesMedia.add(s);

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
}
