package controller;

import javafx.scene.control.Alert;
import model.User;

import java.util.Random;


public class LoginMenuController {
    private static StringBuilder additionalInformation=new StringBuilder();


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
        else if(isPasswordWeak(password)) { Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText(additionalInformation.toString());return alert;}
        else if(! LoginMenuController.isPasswordConfirmed(password,passwordConfirm)){ Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("password is not confirmed correctly");return alert;}
        else{
           User user=new User(username,password,nickname,email);
           User.setLoggedInUser(user);
            return null;
        }
    }

    public static boolean isNicknameValid(String nickname) {
    return nickname.matches("\\S+");
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
        return username.matches("[a-zA-Z\\d\\-]+");
    }

    public static boolean isEmailAddressValid(String email){
        return email.matches("\\S+@\\S+.com");
    }

    public static boolean isPasswordValid(String password){
        return password.matches("\\S+");
    }

    public static boolean isPasswordWeak(String password){
        additionalInformation.delete(0,additionalInformation.toString().length());
        boolean isPasswordWeak=false;
        if(!password.matches("\\S{8,}")) {
            additionalInformation.append("too short\n");
            isPasswordWeak=true;
        }
        if(!password.matches(".*[a-z]+.*")) {
            additionalInformation.append("no small word\n");
            isPasswordWeak=true;
        }
        if(!password.matches(".*[A-Z].*")) {
            additionalInformation.append("no big word\n");
            isPasswordWeak=true;
        }
        if(!password.matches(".*\\d+.*")) {
            additionalInformation.append("no number\n");
            isPasswordWeak=true;
        }
        if(!password.matches(".*[!@#$%^&*()\\-+=]+.*")){
            additionalInformation.append("no special character\n");
            isPasswordWeak=true;
        }
        return isPasswordWeak;
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

    public static void setNewPassword(String username, String text){
        User user=User.getUserByUsername(username);
        String pass=" ";
        user.setPassword(pass);
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
        Random random=new Random();
        StringBuilder password=new StringBuilder();
        int passwordLength=random.nextInt(8,16);
        String validLetters="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-+=";
        password.append(validLetters.charAt(random.nextInt(0,23)));
        password.append(validLetters.charAt(random.nextInt(23,46)));
        password.append(validLetters.charAt(random.nextInt(46,56)));
        password.append(validLetters.charAt(random.nextInt(56,69)));
        for(int i=0;i<passwordLength-4;i++)password.append(validLetters.charAt(random.nextInt(0,validLetters.length())));
        return password.toString();
    }
}
