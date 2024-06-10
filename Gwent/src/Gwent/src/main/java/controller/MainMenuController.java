package controller;
import model.User;

public class MainMenuController {

    public static void enterProfileMenu() {}//todo delete, right?

    public static void enterGameMenu() {}//todo delete, right?

    public static void logout() {
        User.setLoggedInUser(null);
    }
}
