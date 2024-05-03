package model;

import java.util.ArrayList;
import java.util.HashMap;

public class User {

    private static final ArrayList<User> users=new ArrayList<>();

    private static User loggedInUser;

    private final String username;

    private final String password;

    private final String emailAddress;

    private final int securityQuestionNumber;

    private final int securityQuestionAnswer;

    private final static HashMap<Integer,String> securityQuestions=new HashMap<>();
    static{
        securityQuestions.put(0, "");
    }

    public User(String username, String password, String emailAddress, int securityQuestionNumber, int securityQuestionAnswer) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.securityQuestionNumber = securityQuestionNumber;
        this.securityQuestionAnswer = securityQuestionAnswer;
    }
}
