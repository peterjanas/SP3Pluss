package org.example;

import java.util.ArrayList;

public class Movie extends Media {


    private int release;

    private int mediaID;
    ArrayList<String> genre;

    String name;

    int year;

    double rating;





    public Movie(String title, int release, ArrayList<String> genre, double rating) {

        super(title, genre, rating);
        this.release = release;
    }

    public Movie(String title, int release, ArrayList<String> genre, double rating, int mediaID) {
        super(title, genre, rating);
        this.mediaID = mediaID;
        this.release = release;


    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movie otherMovie = (Movie) obj;
        return getTitle().equals(otherMovie.getTitle());
    }

    public int getRelease() {

        return this.release;
    }

    @Override
    public String toString() {
        return "Title: " + getTitle() + "\n" + "Genre: " + getGenre() + "\n" + "Release year: " + getRelease() +
                "\n" + "Rating: " + getRating()+"\n";
    }
}