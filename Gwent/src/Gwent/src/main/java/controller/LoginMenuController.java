package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Alert;
import model.User;

import java.io.IOException;
import java.util.Random;


public class LoginMenuController {
    private static StringBuilder additionalInformation=new StringBuilder();


    public static int userLogin( String username,String password) {
        if (!isUsernameDuplicate(username))return 1;
        else {
            User user=User.getUserByUsername(username);
            System.out.println(user.getUsername());
            if (!isPasswordCorrect(user,password)) {return 2;}
            else {
                User.setLoggedInUser(user);
                return 0;
            }
        }
    }

    public static int userRegister(String username, String password, String passwordConfirm, String nickname, String email) throws IOException {
        if(isUsernameDuplicate(username)) return 1;
        else if(! isUsernameValid(username)){ return 2;}
        else if(! isEmailAddressValid(email))return 3;
        else if(! isPasswordValid(password)) return 4;
        else if(! isNicknameValid(nickname)) return 5;
        else if(isPasswordWeak(password))  return 6;
        else if(! LoginMenuController.isPasswordConfirmed(password,passwordConfirm)) return 7;
        else{
            User user=new User(username,password,nickname,email);
            User.setLoggedInUser(user);
            return 0;
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

    public static boolean isPasswordCorrect(User user ,String password ) {
        return user.getPassword().equals(password);
    }

//    public static boolean IsSecurityQuestionAnswered(String username,String answer){
//        User user=User.getUserByUsername(username);
//        if(answer.equals(user.getAnswerOfSecurityQuestion()))return true;
//        return false;
//    }

    public static int setNewPassword(String username, String pass,String confirmPass){
        User user=User.getUserByUsername(username);
        if(!isPasswordValid(pass))return 1;
        if(isPasswordWeak(pass)) return 2;
        if(!pass.equals(confirmPass))return 3;
        user.setPassword(pass);
        User.setLoggedInUser(user);
        return 0;
    }

    public static int setSecurityQ(String username, String text1, String text2, String text3){
        User user=User.getUserByUsername(username);
        int i=0;
        if (! text1.isEmpty())i++;
        if (! text2.isEmpty())i++;
        if (! text3.isEmpty())i++;
        if(i==0)return 1;
        if (i>=2)return 2;
        else {
            if (! text1.isEmpty()){user.setSecurityQuestionNumber(1);user.setAnswerOfSecurityQuestion(text1);}
            if (! text2.isEmpty()){user.setSecurityQuestionNumber(2);user.setAnswerOfSecurityQuestion(text1);}
            if (! text3.isEmpty()){user.setSecurityQuestionNumber(3);user.setAnswerOfSecurityQuestion(text1);}
            return 0;
        }
    }

    public static int hasAnsweredCorrectly(String username, String answer) {
        if(! isUsernameDuplicate(username))return 1;
        User user=User.getUserByUsername(username);
        if(! answer.equals(user.getAnswerOfSecurityQuestion()))return 2;
        User.setLoggedInUser(user);
        return 0;
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
    public static String generateRandomNumber(){
        Random random=new Random();
        StringBuilder twoFACode=new StringBuilder();
        for(int i=0;i<6;i++) twoFACode.append(random.nextInt(0,9));
        return twoFACode.toString();
    }
    public int add(int a,int b){
        return a+b;
    }
}
