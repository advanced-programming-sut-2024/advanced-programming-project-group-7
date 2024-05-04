package model;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private static final ArrayList<User> users=new ArrayList<>();
    private ArrayList<Card> deck=new ArrayList<>();
    private static User loggedInUser;
    private String username;
    private String password;
    private String nickname;
    private String emailAddress;
    private int highestScore;
    private int rank;
    private int totalGame;
    private int drawnGame;
    private int wonGame;
    private int lostGame;
    private Faction currentFaction;
    private final int securityQuestionNumber;
    private final String securityQuestionAnswer;
    private final static HashMap<Integer,String> securityQuestions=new HashMap<>();
    static{
        securityQuestions.put(0, "");
    }

    public User(String username,String password,String nickname,String emailAddress,int securityQuestionNumber, String securityQuestionAnswer) {
        this.username=username;
        this.password=password;
        this.nickname=nickname;
        this.currentFaction=new Faction();
        this.emailAddress=emailAddress;
        this.securityQuestionNumber = securityQuestionNumber;
        this.securityQuestionAnswer = securityQuestionAnswer;
    }

    public static void addUser(User user){
        users.add(user);
    }
    public void addCardToDeck(Card card, int count){
        for(int i=0;i<count;i++)
            this.getDeck().add(card);
    }
    public static User getUserByUsername(String username){
        for(User user:users)
            if(user.getUsername().equals(username))
                return user;
        return null;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        User.loggedInUser = loggedInUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(int highestScore) {
        this.highestScore = highestScore;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getTotalGame() {
        return totalGame;
    }

    public void setTotalGame(int totalGame) {
        this.totalGame = totalGame;
    }

    public int getDrawnGame() {
        return drawnGame;
    }

    public void setDrawnGame(int drawnGame) {
        this.drawnGame = drawnGame;
    }

    public int getWonGame() {
        return wonGame;
    }

    public void setWonGame(int wonGame) {
        this.wonGame = wonGame;
    }

    public int getLostGame() {
        return lostGame;
    }

    public void setLostGame(int lostGame) {
        this.lostGame = lostGame;
    }
}
