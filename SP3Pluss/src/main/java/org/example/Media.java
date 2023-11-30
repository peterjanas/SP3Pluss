package org.example;

import java.util.ArrayList;

abstract class Media {

    private String title;
    private ArrayList<String> genre;
    private double rating;

    public Media(String title, ArrayList<String> genre, double rating) {

        this.title = title;
        this.genre = genre;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public double getRating() {
        return rating;
    }

    public abstract String toString();

    public abstract boolean equals(Object obj);

}
