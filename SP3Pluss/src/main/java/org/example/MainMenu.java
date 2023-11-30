package org.example;


import java.util.*;

public class MainMenu {
    TextUI ui = new TextUI();
    Setup setup;
    Login login;
    private HashMap<String, String> users;
    Scanner scan = new Scanner(System.in);
    FileIO io = new FileIO();
    DBIO dbio;
    ArrayList<Media> mediaList = new ArrayList<>();

    public MainMenu(Setup setup) {
        this.setup = setup;
    }
    public void setDBIO(DBIO dbio){
        this.dbio = dbio;

    }
    public void setLogin(Login login) {
        this.login = login;
        this.users = login.getUsers();
    }

    public void welcome(User currentUser) {
        ArrayList<Movie> movies = setup.getMovies();
        ArrayList<Series> series = setup.getSeries();
        mediaList.addAll(movies);
        mediaList.addAll(series);

        ui.displayMessage("Welcome " + currentUser.getUsername() + " These are your options, type the number assigned to the option" +
                "\n1: Search media.\n2: Search movie genre.\n3: Search series genre \n4: Check your viewed movies & series." +
                "\n5: Check your saved movie & series.\n6: Save and exit.");
        String input = scan.nextLine();
        switch (input) {
            case "1":
                searchMedia(currentUser);
                break;
            case "2":
                searchMovieGenre(currentUser);
                break;
            case "3":
                searchSerieGenre(currentUser);
                break;

            case "4":
                checkViewedMedia(currentUser);
                break;
            case "5":
                checkSavedMedia(currentUser);
                break;
            case "6":
                saveAndExit(currentUser);
                break;
            default:
                ui.displayMessage("error try again");
                welcome(currentUser);
        }
    }

    public void searchMedia(User currentUser) {
        ui.displayMessage("Type in what you want to watch.");
        String searchForMedia = scan.nextLine().toLowerCase();

        boolean found = false;

        for (Media media : mediaList) {
            if (media.getTitle().toLowerCase().equals(searchForMedia)) {
                displayOptions(media, currentUser);
                ui.displayMessage("Do you want to watch something else, go to the menu, or exit?\n" +
                        "Please press 'y' to watch more or press any other key to go back to the main menu");
                String choice = scan.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    found = true;
                    break;
                } else {
                    welcome(currentUser);
                    return;
                }
            }
        }
        if (!found) {
            ui.displayMessage("The media you searched for wasn't found. Please search again or browse movie genres.");
        }
        searchMedia(currentUser);
    }

    public void searchMovieGenre(User currentUser) {
        setup.searchMovieGenre();
        ui.displayMessage("Type in the movie you want to watch");
        while (true) {
            String searchForMedia = scan.nextLine().toLowerCase();

            boolean found = false;

            for (Media media : mediaList) {
                if (media.getTitle().toLowerCase().equals(searchForMedia)) {
                    displayOptions(media, currentUser);
                    found = true;
                    ui.displayMessage("Do you want to watch something else, go to the menu, or exit?\n" +
                            "Please press 'y' to watch more or press any other key to exit and go back to the main menu");
                    String choice = scan.nextLine();
                    if (choice.equalsIgnoreCase("y")) {
                        searchMedia(currentUser);
                        break;
                    } else {
                        welcome(currentUser);
                        return;
                    }
                }
            }
            if (!found) {
                ui.displayMessage("The media you searched for wasn't found. Please search again or browse movie genres.");
            }
            searchMedia(currentUser);
        }
    }

    public void searchSerieGenre(User currentUser) {
        setup.searchSeriesGenre();
        ui.displayMessage("Type in the series you want to watch");

        while (true) {
            String searchForMedia = scan.nextLine().toLowerCase();

            boolean found = false;

            for (Media media : mediaList) {
                if (media.getTitle().toLowerCase().equals(searchForMedia)) {
                    found = true;
                    displayOptions(media, currentUser);
                    ui.displayMessage("Do you want to watch something else, go to the menu, or exit?\n" +
                            "Please press 'y' to watch more or press 'e' to exit and go back to the main menu");
                    String choice = scan.nextLine();
                    if (choice.equalsIgnoreCase("y")) {
                        searchMedia(currentUser);
                        break;
                    } else {
                        welcome(currentUser);
                        return;
                    }
                }
            }
            if (!found) {
                ui.displayMessage("The media you searched for wasn't found. Please search again or browse movie genres.");
            }
            searchMedia(currentUser);
        }
    }

    public void checkViewedMedia(User currentUser) {
        ui.displayMessage("here is your viewed media\ntype in the media you want to watch");
        String display = "";
        for(Media media: currentUser.getWatchedMedia()){
            display = display.concat(media.toString());
        }
        ui.displayMessage(display);
        searchMedia(currentUser);
    }

    public void checkSavedMedia(User currentUser) {
        ui.displayMessage("here is your viewed media\ntype in the media you want to watch");
        String display = "";
        for(Media media: currentUser.getSavedMedia()){
            display = display.concat(media.toString());
        }
        ui.displayMessage(display);
        searchMedia(currentUser);
    }

    public void saveAndExit(User currentUser) {
        ui.displayMessage("saving and exiting");
        //io.saveUserData("Textdata/User.txt", users);
        //io.createUserFolder(currentUser);
        dbio.saveUser(currentUser.getUsername(),currentUser.getPassword());
        System.exit(0);
    }

    private void displayOptions(Media media, User currentUser) {
        ui.displayMessage("Found " + media.getTitle() + "\nOptions:" +
                "\n1: Watch " + media.getTitle() + "\n2: Save " + media.getTitle()
                + "\n3: Remove from list\n4: Search for another media\n5: Return to Main menu");

        String input = scan.nextLine();
        switch (input) {
            case "1":
                ui.displayMessage(media.getTitle() + " is now playing. " + media.getTitle() + " has been added to watched media.");
                currentUser.addToWatchedMedia(media);
                break;

            case "2":
                currentUser.addToSavedMedia(media);
                ui.displayMessage(media.getTitle() + " has been added to saved media\n");
                welcome(currentUser);
                break;

            case "3":
                currentUser.removeFromSavedMedia(media);
                ui.displayMessage(media.getTitle() + " has been removed from list of saved media\n");
                break;

            case "4":
                searchMedia(currentUser);
                break;
            case "5":
                welcome(currentUser);
                break;
            default:
                ui.displayMessage("Invalid option, Please try again.");
                displayOptions(media, currentUser);
        }
    }
}
