package controller;

import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import model.Faction;
import model.User;


public class LoginMenuController {
    private static String additionalInformation;


public static Alert userLogin( String username,String password) {
        if (!isUsernameDuplicate(username)){Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("you haven't registered");return alert;}
        else {
            User user=User.getUserByUsername(username);
            System.out.println(user.getUsername());
            //todo forgot pass doesn't care here
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
            //todo answer security Q and set answer
            int secNum=1;
            String answer= "ok";
           User user=new User(username,password,nickname,email,secNum,answer);
           User.setLoggedInUser(user);
            return null;
        }
    }

    public static boolean isNicknameValid(String nickname) {
    return nickname.matches(".+");
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

    public static void makeRandomPassword(MouseEvent mouseEvent){
        //todo make random password with the required regex
    };
    public static boolean isPasswordValid(String password){
        return password.matches("\\S+");//
    }
    public static boolean isPasswordWeak(String password){
//        if(!password.matches("\\S{8,}")) {
//            additionalInformation="too short";
//            return true;
//        }
//        else if(!password.matches("(?=\\S*[a-z])")) {
//            additionalInformation="no small word";
//            return true;
//        }
//        else if(!password.matches("(?=\\S*[A-Z])")) {
//            additionalInformation="no big word";
//            return true;
//        }
//        else if(!password.matches("(?=\\S*\\d)")) {
//            additionalInformation="no number";
//            return true;
//        }
//        else if(!password.matches("(?=\\S*[!@#$%^&*()\\-+=])")){
//            additionalInformation="no special character";
//            return true;
//        }
         return false;//todo regexes should change
    }
    public static boolean isPasswordConfirmed(String password,String passwordConfirm){
        return password.equals(passwordConfirm);
    }
    private static boolean isPasswordCorrect(User user ,String password ) {
        return user.getPassword().equals(password);
    }
    public static void pickSecurityQuestion(){
       //todo a menu to sout all Q's and a field for answer and a botton for number of Q
    }
    public static Alert forgotPassword(String username) {
        String answer=" ";//todo
        if(IsSecurityQuestionAnswered(username,answer)){
            setNewPassword(username);
            return null;
        }else{ Alert alert=new Alert(Alert.AlertType.WARNING);alert.setHeaderText("wrong answer");return alert;}
    }
    public static boolean IsSecurityQuestionAnswered(String username,String answer){
        User user=User.getUserByUsername(username);
        if(answer.equals(user.getAnswerOfSecurityQuestion()))return true;
        return false;
    }
    public static void setNewPassword(String username){
        User user=User.getUserByUsername(username);
        String pass=" ";
        user.setPassword(pass);//todo
    }

    public static void handleForgottenPassword(String text, String text1, String text2, String text3) {
    }
}
