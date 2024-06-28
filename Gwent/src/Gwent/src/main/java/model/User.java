package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private static final ArrayList<User> users=new ArrayList<>();

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public void setDecks(ArrayList<Deck> decks) {
        this.decks = decks;
    }

    public Deck getCurrentDeck() {
        return currentDeck;
    }

    public void setCurrentDeck(Deck currentDeck) {
        this.currentDeck = currentDeck;
    }

    private ArrayList<Deck> decks=new ArrayList<>();
    private ArrayList<Game> games=new ArrayList<>();
    private ArrayList<BattleInfo> battleLog =new ArrayList<>();
    static {

    }
    private Leader currentLeader;
    private static User loggedInUser;
    private String username;
    private String password;
    private String nickname;
    private String emailAddress;
    private int highestScore=0;
    private int rank=1000;//todo init
    private int totalGame=0;
    private int drawnGame=0;
    private int wonGame=0;
    private int lostGame=0;
    private Faction currentFaction;
    private  int securityQuestionNumber;
    private String answerOfSecurityQuestion;
    //private final String securityQuestionAnswer;
    private final static HashMap<Integer,String> securityQuestions=new HashMap<>();
    private Deck currentDeck;
    static{
        securityQuestions.put(1, "What is your favorite color?");
        securityQuestions.put(2, "What is your pet's name?");
        securityQuestions.put(3,  "Where were you born?");
    }

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


    private String userCurrentMenu;

    public User(String username, String password, String nickname, String emailAddress) {
        this.username=username;
        this.password=password;
        this.nickname=nickname;
        this.emailAddress=emailAddress;
        addUser(this);
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

    static {
    User userTest=new User("","","Ebim","amir2023@gmail.com");
    userTest.setSecurityQuestionNumber(1);
    userTest.setAnswerOfSecurityQuestion("red");
        userTest.battleLog.add(new BattleInfo("ebi", LocalDate.now(),new int[][]{{20,10},{10,20},{20,10}}, new int[]{50, 40},userTest));
    }

    public ArrayList<BattleInfo> getBattleLog() {
        return battleLog;
    }
}
