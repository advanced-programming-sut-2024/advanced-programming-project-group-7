package controller;

import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import model.Faction;
import model.User;

import java.util.regex.Matcher;

public class LoginMenuController {
    private static String additionalInformation;
    public static Alert userLogin( String username,String password) {
        if (!isUsernameDuplicate(username)) System.out.println("");
        else {
            User user=User.getUserByUsername(username);
            //todo forgot pass doesn't care here
            if (!isPasswordCorrect(user,password)) System.out.println("");
            else {
                User.setLoggedInUser(user);
                System.out.println("");
                //todo  (is there anything else?)
            }
        }

    }


    public static Alert userRegister( String username,String password,String passwordConfirm,String nickname,String email){
        if(isUsernameDuplicate(username)) System.out.println("");
        else if(! isUsernameValid(username)) System.out.println("");
        else if(! isEmailAddressValid(email)) System.out.println("");
        else if(! isPasswordValid(password)) System.out.println("");
        else if(isPasswordWeak(password)) System.out.println(additionalInformation);//todo is normal way better?
        else if(! LoginMenuController.isPasswordConfirmed(password,passwordConfirm)) System.out.println(" ");
        else{
            //todo answer security Q and set answer
            int secNum=1;
            String answer= "ok";
           User user=new User(username,password,nickname,email,secNum,answer);
        }
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
        return username.matches("");//todo regex+
    }
    public static boolean isEmailAddressValid(String email){
        return email.matches("");//todo regex+
    }

    public static void makeRandomPassword(MouseEvent mouseEvent){
        //todo make random password with the required regex
    };
    public static boolean isPasswordValid(String password){
        return password.matches("");//todo regex phase1+
    }
    public static boolean isPasswordWeak(String password){
        //todo regex phase2 + additional  information;+


        if(true){
            if (true){

            }else additionalInformation="hey";
        }else additionalInformation="hi";
        return false;
    }
    public static boolean isPasswordConfirmed(String password,String passwordConfirm){
        return password.equals(passwordConfirm);
    }
    private static boolean isPasswordCorrect(User user ,String password ) {
        return user.getPassword().equals(password);
    }
    public static void pickSecurityQuestion(){
        //todo
    }
    public static Alert forgotPassword(String username) {
        if(IsSecurityQuestionAnswered(username)){
            setNewPassword(username);
            System.out.println("");
        }else System.out.println("");
    }
    public static boolean IsSecurityQuestionAnswered(String username){
        //todo
    }
    public static void setNewPassword(String username){
        //todo
    }
}
