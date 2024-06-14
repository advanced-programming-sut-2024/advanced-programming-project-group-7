package controller;

import javafx.scene.control.Alert;
import model.User;


public class LoginMenuController {
    private static String additionalInformation;


public static Alert userLogin( String username,String password) {
        if (!isUsernameDuplicate(username)){Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("you haven't registered");return alert;}
        else {
            User user=User.getUserByUsername(username);
            System.out.println(user.getUsername());
            if (!isPasswordCorrect(user,password)) {Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("wrong password");return alert;}
            else {
                User.setLoggedInUser(user);
               return null;
            }
        }
    }
    public static Alert userRegister( String username,String password,String passwordConfirm,String nickname,String email){
//        if(username.isEmpty()){Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("empty username");return alert;}
        if(isUsernameDuplicate(username)) { Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("this username is taken");return alert;}
        else if(! isUsernameValid(username)){ Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("invalid username");return alert;}
        else if(! isEmailAddressValid(email)){ Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("invalid email");return alert;}
        else if(! isPasswordValid(password)){ Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("invalid password");return alert;}
        else if(! isNicknameValid(nickname)){ Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("invalid nickname");return alert;}
        else if(isPasswordWeak(password)) { Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText(additionalInformation);return alert;}
        else if(! LoginMenuController.isPasswordConfirmed(password,passwordConfirm)){ Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("password is not confirmed correctly");return alert;}
        else{
           User user=new User(username,password,nickname,email);
           User.setLoggedInUser(user);
            return null;
        }
    }

    public static boolean isNicknameValid(String nickname) {
    return nickname.matches(".+");//todo regex+
    }

    public static boolean isUsernameDuplicate(String username){
        for(User user:User.getUsers()){
            if(user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
    public static boolean isUsernameValid(String username){
        return username.matches(".+");//todo regex+
    }
    public static boolean isEmailAddressValid(String email){
        return email.matches(".+");//todo regex+
    }

    public static boolean isPasswordValid(String password){
        return password.matches("\\S+");//
    }
    public static boolean isPasswordWeak(String password){
        if(!password.matches("\\S{8,}")) {
            additionalInformation="too short";
            return true;
        }
        else if(!password.matches(".*[a-z]+.*")) {
            additionalInformation="no small word";
            return true;
        }
        else if(!password.matches(".*[A-Z].*")) {
            additionalInformation="no big word";
            return true;
        }
        else if(!password.matches(".*\\d+.*")) {
            additionalInformation="no number";
            return true;
        }
        else if(!password.matches(".*[!@#$%^&*()\\-+=]+.*")){
            additionalInformation="no special character";
            return true;
        }
         return false;
    }
    public static boolean isPasswordConfirmed(String password,String passwordConfirm){
        return password.equals(passwordConfirm);
    }
    private static boolean isPasswordCorrect(User user ,String password ) {
        return user.getPassword().equals(password);
    }

    public static boolean IsSecurityQuestionAnswered(String username,String answer){
        User user=User.getUserByUsername(username);
        if(answer.equals(user.getAnswerOfSecurityQuestion()))return true;
        return false;
    }
    public static Alert setNewPassword(String password, String confirm){
        if(! password.equals(confirm)){Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("answer one question at least");return alert;}
        if(isPasswordWeak(password)) { Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText(additionalInformation);return alert;}
        User.getLoggedInUser().setPassword(password);
        return null;
    }

    public static Alert setSecurityQ(String username, String text1, String text2, String text3) {
        User user=User.getUserByUsername(username);
        int i=0;
        if (! text1.isEmpty())i++;
        if (! text2.isEmpty())i++;
        if (! text3.isEmpty())i++;
        if(i==0){Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("answer one question at least");return alert;}
        if (i>=2){Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("answer only one question");return alert;}
        else {
            if (! text1.isEmpty()){user.setSecurityQuestionNumber(1);user.setAnswerOfSecurityQuestion(text1);}
            if (! text2.isEmpty()){user.setSecurityQuestionNumber(2);user.setAnswerOfSecurityQuestion(text1);}
            if (! text3.isEmpty()){user.setSecurityQuestionNumber(3);user.setAnswerOfSecurityQuestion(text1);}
            return null;
        }
    }

    public static Alert hasAnsweredCorrectly(String username, String answer) {
        if(! isUsernameDuplicate(username)){Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("this username doesn't exist");return alert;}
        User user=User.getUserByUsername(username);
        if(! answer.equals(user.getAnswerOfSecurityQuestion())){Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("wrong answer");return alert;}
        User.setLoggedInUser(user);
        return null;
    }

    public static String generatePassword() {
        return null; // todo strong pass as String
    }
}
