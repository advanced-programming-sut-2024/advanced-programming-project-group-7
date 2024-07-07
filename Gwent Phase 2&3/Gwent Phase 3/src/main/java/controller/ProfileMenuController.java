package controller;

import javafx.scene.control.Alert;
import model.User;
import view.LoginMenu;

import java.util.regex.Matcher;

public class ProfileMenuController {
    static User currentUser=User.getLoggedInUser();

    public static Alert changeUsername(String username) {
        if(! LoginMenuController.isUsernameValid(username)){ Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("this username is not valid");return alert;}
        if(LoginMenuController.isUsernameDuplicate(username)){ Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("this username was taken");return alert;}
        currentUser.setUsername(username);
        return null;
    }

    public static Alert changeNickname( String nickname) {
          if(! LoginMenuController.isNicknameValid(nickname)){ Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("invalid nickname");return alert;}
        currentUser.setNickname(nickname);
        return null;
    }
    public static Alert changeEmail(String email) {
        if(! LoginMenuController.isEmailAddressValid(email)){ Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("invalid username");return alert;}
        currentUser.setEmailAddress(email);
        return null;
    }
    public static Alert changePassword(String old,String password) {
        if(old.isEmpty()){ Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("enter previous password");return alert;}
        if(! currentUser.getPassword().equals(old)){ Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("previous password is wrong");return alert;}
        if(! LoginMenuController.isPasswordValid(password)){ Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("this password is not valid");return alert;}//todo weak pass later
        if(! LoginMenuController.isPasswordValid(password)){ Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("this password is not valid");return alert;}
        if(LoginMenuController.isPasswordWeak(password)){ Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("this password is weak");return alert;}
        currentUser.setPassword(password);
        return null;
    }
    public static void userInfo() {
    }
    public static void gameHistory() {
    }
}