package org.example;

import java.util.*;

public class User {
    private String username;
    private String password;
    private ArrayList<Media> watchedMedia;
    private ArrayList<Media> savedMedia;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.watchedMedia = new ArrayList<>();
        this.savedMedia = new ArrayList<>();

    }

    public void addToWatchedMedia(Media media) {
        watchedMedia.add(media);
    }

    public void addToSavedMedia(Media media) {
        savedMedia.add(media);
    }

    public void removeFromSavedMedia(Media media) {
        savedMedia.remove(media);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Media> getWatchedMedia() {
        return watchedMedia;
    }

    public ArrayList<Media> getSavedMedia() {
        return savedMedia;
    }

}
