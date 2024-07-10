package controller;
import javafx.scene.control.ScrollPane;
import model.User;

public class MainMenuController {


    public static void logout() {
        User.setLoggedInUser(null);
    }
}
