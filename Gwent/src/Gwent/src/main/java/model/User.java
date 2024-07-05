package model;


//import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import controller.Client;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class User {
    private static final ArrayList<User> users=new ArrayList<>();
    ObjectMapper objectMapper=new ObjectMapper();
//    public Client client;
    public String currentOponentName;
    private ArrayList<String> requests = new ArrayList<>();
    private ArrayList<String> friends = new ArrayList<>();

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

    public User(String username, String password, String nickname, String emailAddress)  {
        this.username=username;
        this.password=password;
        this.nickname=nickname;
        this.emailAddress=emailAddress;
        addUser(this);
//        addToFile(this);
    }
    public User(){

    }

    private void addToFile(User user) throws IOException {
        File file = new File("/Users/apple/Desktop/data/dir");
        if (file.exists() && file.length() != 0) {
            // Read existing students into a list
            List<User> existingStudents = objectMapper.readValue(file, new TypeReference<List<User>>() {});
            existingStudents.add(user); // Add new user
            objectMapper.writeValue(file, existingStudents); // Write updated list
        } else {
            // If file doesn't exist or is empty, create a new list with this user
            List<User> newStudentList = new ArrayList<>();
            newStudentList.add(user);
            objectMapper.writeValue(file, newStudentList);
        }
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

//    public Client getClient() {
//        return client;
//    }
//
//    public void setClient(Client client) {
//        this.client = client;
//    }

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
        User userTest= null;
//        try {
            userTest = new User("","","Ebim","s.mohammad.e.1383@gmail.com");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        userTest.setSecurityQuestionNumber(1);
    userTest.setAnswerOfSecurityQuestion("red");
        userTest.battleLog.add(new BattleInfo("ebil", LocalDate.now(),new int[][]{{20,10},{10,20},{20,10}}, new int[]{50, 40},userTest));

        User userTest1= null;
        try {
            userTest1 = new User("a","a","mr a","amir2023@gmail.com");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        userTest1.setSecurityQuestionNumber(1);
        userTest1.setAnswerOfSecurityQuestion("red");
        userTest1.battleLog.add(new BattleInfo("ebil", LocalDate.now(),new int[][]{{20,10},{10,20},{20,10}}, new int[]{50, 40},userTest));

        User userTest2= null;
//        try {
            userTest2 = new User("b","b","mr b","amir2023@gmail.com");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        userTest2.setSecurityQuestionNumber(1);
        userTest2.setAnswerOfSecurityQuestion("red");
        userTest2.battleLog.add(new BattleInfo("ebil", LocalDate.now(),new int[][]{{20,10},{10,20},{20,10}}, new int[]{50, 40},userTest));
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
        System.out.println(reqPart);
    }

    public ArrayList<String > getFriends() {
        return friends;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String getCurrentOponentName() {
        return currentOponentName;
    }

    public void setCurrentOponentName(String currentOponentName) {
        this.currentOponentName = currentOponentName;
    }

    public void setRequests(ArrayList<String> requests) {
        this.requests = requests;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public void setBattleLog(ArrayList<BattleInfo> battleLog) {
        this.battleLog = battleLog;
    }

    public Leader getCurrentLeader() {
        return currentLeader;
    }

    public void setCurrentLeader(Leader currentLeader) {
        this.currentLeader = currentLeader;
    }

    public Faction getCurrentFaction() {
        return currentFaction;
    }

    public void setCurrentFaction(Faction currentFaction) {
        this.currentFaction = currentFaction;
    }

    public String getUserCurrentMenu() {
        return userCurrentMenu;
    }

    public void setUserCurrentMenu(String userCurrentMenu) {
        this.userCurrentMenu = userCurrentMenu;
    }
}
