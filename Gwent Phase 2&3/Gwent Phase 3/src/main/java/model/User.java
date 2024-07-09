package model;


import com.google.gson.annotations.SerializedName;
import controller.Client;
import javafx.scene.control.Button;
import view.CupMenu;
import view.GameLauncher;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class User {
    private static final ArrayList<User> users=new ArrayList<>();
    public transient Client client;
    public transient String currentOponentName;
    public transient boolean isInCup = false;
    @SerializedName("isOnline")
    public  Boolean isOnline = false;
    public transient CupMenu cupMenu;
    private transient ArrayList<String> requests = new ArrayList<>();
    private transient ArrayList<String> friends = new ArrayList<>();
    private transient ArrayList<Deck> decks=new ArrayList<>();
    private transient ArrayList<Game> games=new ArrayList<>();
    private transient ArrayList<BattleInfo> battleLog =new ArrayList<>();
    private transient Leader currentLeader;
    private static User loggedInUser;
    @SerializedName("username")
    public String username;
    @SerializedName("password")
    private String password;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("emailAddress")
    private String emailAddress;
    private transient int highestScore=0;
    private transient int rank=1000;//todo init
    private transient int totalGame=0;
    private transient int drawnGame=0;
    private int wonGame=0;
    private transient int lostGame=0;
    private transient   int securityQuestionNumber;
    private transient String answerOfSecurityQuestion;
    private transient final static HashMap<Integer,String> securityQuestions=new HashMap<>();
    private transient Deck currentDeck;
    public User(String username, String password, String nickname, String emailAddress) {
        this.username=username;
        this.password=password;
        this.nickname=nickname;
        this.emailAddress=emailAddress;
    }
//    static{
//        securityQuestions.put(1, "What is your favorite color?");
//        securityQuestions.put(2, "What is your pet's name?");
//        securityQuestions.put(3,  "Where were you born?");
//    }

    public int getSecurityQuestionNumber() {
        return securityQuestionNumber;
    }

    public void setSecurityQuestionNumber(int securityQuestionNumber) {
        this.securityQuestionNumber = securityQuestionNumber;
    }

    public void setAnswerOfSecurityQuestion(String answerOfSecurityQuestion) {
        this.answerOfSecurityQuestion = answerOfSecurityQuestion;
    }

    public String getAnswerOfSecurityQuestion() {
        return answerOfSecurityQuestion;
    }

    public static void addUser(User user){
        users.add(user);
    }
    public void addCardToDeck(Card card, int count){
        for(int i=0;i<count;i++)
            this.getDeck().add(card);
    }
    public static User getUserByUsername(String username){
        for(User user:users) {
            if(user.getUsername().equals(username)) return user;
        }
        return null;
    }

    public ArrayList<Card> getDeck() {
    return null;
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
        return this.password;
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

    public static ArrayList<User> getUsers() {
        return users;
    }

    public String getSecurityQuestion() {
        return securityQuestions.get(this.securityQuestionNumber);
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public ArrayList<BattleInfo> getBattleLog() {
        return battleLog;
    }

    public ArrayList<String> getRequests() {
        return requests;
    }
    public void addReq (String req){
        requests.add(req);
    }

    public void addFriend(String reqPart) {
        friends.add(reqPart);
    }

    public ArrayList<String > getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }
}
