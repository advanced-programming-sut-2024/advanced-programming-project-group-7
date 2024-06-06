package controller;
import model.User;

import java.util.regex.Matcher;

public class MainMenuController{

    public static void enterProfileMenu() {}

    public static void enterGameMenu() {}

    public static void logout() {
        User.setLoggedInUser(null);
    }
}
