package org.example;

import java.util.HashMap;
import java.util.Scanner;

public class Login {
    private TextUI ui = new TextUI();
    private Scanner scan = new Scanner(System.in);
    Setup setup;
    private HashMap<String, String> users;
    MainMenu menu;

    public Login(Setup setup) {
        this.setup = setup;
        this.users = setup.getUsers();

    }

    public void setMenu(MainMenu menu) {
        this.menu = menu;
    }

    public HashMap<String, String> getUsers() {
        return users;
    }


    public void loginOrCreate() {
        ui.displayMessage("Welcome to the streaming website Chill," +
                "\ntype login or register to continue, press q to return");
        String response = scan.nextLine();

        if (response.equalsIgnoreCase("Login")) {
            ui.displayMessage("type Username");
            login();
        } else if (response.equalsIgnoreCase("register")) {
            ui.displayMessage("Create a Username");
            register();
        } else {
            ui.displayMessage("invalid input");
            loginOrCreate();
        }
    }

    public void login() {
        String userName = scan.nextLine();

        if (users.containsKey(userName)) {
            ui.displayMessage("type in your password");
            String passWord = scan.nextLine();


            if (passWord.equals(users.get(userName))) {
                ui.displayMessage("logging you in");
                User currentUser = new User(userName, passWord);
                menu.welcome(currentUser);
            } else if (passWord.equalsIgnoreCase("Q")) {
                loginOrCreate();
            } else {
                ui.displayMessage("Error try again or q to go back");
                login();
            }
        } else if (userName.equalsIgnoreCase("Q")) {
            loginOrCreate();
        } else {
            ui.displayMessage("Username or Password is wrong try again");
            login();
        }
    }

    public void register() {
        String userName = scan.nextLine();
        if (users.containsKey(userName)) {
            ui.displayMessage("username already taken try again");
            register();
        } else if (userName.equalsIgnoreCase("Q")) {
            loginOrCreate();
        } else {
            ui.displayMessage("Choose a Password");
            if (userName.equalsIgnoreCase("Q")) {
                loginOrCreate();
            }
            String passWord = scan.next();
            User currentUser = new User(userName, passWord);
            users.put(currentUser.getUsername(), currentUser.getPassword());
            ui.displayMessage("registration successful, logging you in");
            menu.welcome(currentUser);
        }
    }
}

