package controller;

import javafx.scene.control.Alert;
import model.User;
import view.LoginMenu;

import java.util.regex.Matcher;

public class ProfileMenuController {
    static User currentUser=User.getLoggedInUser();

    public static int changeUsername(String username) {
        if(! LoginMenuController.isUsernameValid(username))return 1;
        if(LoginMenuController.isUsernameDuplicate(username))return 2;
        currentUser.setUsername(username);
        return 0;
    }

    public static int changeNickname( String nickname) {
          if(! LoginMenuController.isNicknameValid(nickname))return 1;
        currentUser.setNickname(nickname);
        return 0;
    }
    public static int changeEmail(String email) {
        if(! LoginMenuController.isEmailAddressValid(email))return 1;
        currentUser.setEmailAddress(email);
        return 0;
    }
    public static int changePassword(String old,String password) {
        if(old.isEmpty())return 1;
        if(! currentUser.getPassword().equals(old))return 2;
        if(! LoginMenuController.isPasswordValid(password))return 3;
//        if(! LoginMenuController.isPasswordValid(password)){ Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("this password is not valid");return alert;}
        if(LoginMenuController.isPasswordWeak(password))return 4;
        currentUser.setPassword(password);
        return 0;
    }
//    public static void userInfo() {
//    }
//    public static void gameHistory() {
//    }
}