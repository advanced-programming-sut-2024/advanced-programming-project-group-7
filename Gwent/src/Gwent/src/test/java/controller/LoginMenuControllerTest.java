package controller;
//        User.getUsers().add(existingUser); // Assuming you have a UserDatabase class to manage users
import controller.LoginMenuController;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Alert;
import model.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class LoginMenuControllerTest {

//    @BeforeEach
//    public void setUp() {
//        // Clear users before each test
//        User.getUsers().clear();
//    }

//    @BeforeClass
//    public static void initJfxRuntime() {
//        Platform.startup(() -> {
//        });
//    }

    @Test
    public void isSumOk(){
        LoginMenuController loginMenuController=new LoginMenuController();

       assertEquals(8,loginMenuController.add(3,5));
       assertTrue(LoginMenuController.isPasswordValid("dfegFGTh454!"));
       assertFalse(LoginMenuController.isPasswordWeak("dfegFGTh454!"));
       assertTrue(LoginMenuController.isPasswordWeak("dgefg"));
       assertTrue(LoginMenuController.isPasswordWeak("FERGREG"));
        assertTrue(LoginMenuController.isPasswordWeak("dgefgFDGFREG"));
        assertTrue(LoginMenuController.isPasswordValid("fd"));
        assertTrue(LoginMenuController.isNicknameValid("frgg"));
        assertTrue(LoginMenuController.isEmailAddressValid("dgefg@gmail.com"));
         String num1= LoginMenuController.generateRandomNumber();
        String num2= LoginMenuController.generateRandomNumber();
        String num3= LoginMenuController.generateRandomNumber();
        String num4= LoginMenuController.generateRandomNumber();
        assertTrue(!num1.equals(num2));
        assertTrue(!num1.equals(num3));
        assertTrue(!num1.equals(num4));
        String pass1= LoginMenuController.generatePassword();
        String pass2= LoginMenuController.generatePassword();
        assertTrue(!pass1.equals(pass2));
        assertTrue(LoginMenuController.isUsernameValid("fgFGFG"));
        assertTrue(LoginMenuController.isPasswordConfirmed("frght","frght"));
        assertFalse(LoginMenuController.isPasswordConfirmed("frghfedft","frght"));
        User user=new User("c","c","c","c");
        user.setAnswerOfSecurityQuestion("blackDog");
        User.getUsers().add(user);
        assertFalse(LoginMenuController.isUsernameDuplicate("dffg"));
        assertTrue(LoginMenuController.isPasswordCorrect(user,"c"));

    }

    @Test
    public void testUserLogin_Success() {
        // Register a user
        User user = new User("registeredUser", "correctPassword", "nickname", "email@example.com");
        User.addUser(user);

        // Attempt to log in with the correct username and password
        int a = LoginMenuController.userLogin("registeredUser", "correctPassword");
        assertEquals(0,a);
        int b=LoginMenuController.userLogin("ff","fgrgr");
        assertEquals(1,b);
        int c=LoginMenuController.userLogin("registeredUser", "notCorrectPassword");
        assertEquals(2,c);

        // Check if the alert is null (indicating a successful login)
    }
    @Test
    public void testRegister() throws IOException {
        User user=new User("c","c","c","c");
//        User.addUser(user);
        int a=LoginMenuController.userRegister("c","c","c","c","c");
        assertEquals(1,a);
        int b=LoginMenuController.userRegister("  ","","","","");
        assertEquals(2,b);
        int c=LoginMenuController.userRegister("ali","dgf","dgreg","ggg","dferg");
        assertEquals(3,c);
        int d=LoginMenuController.userRegister("ali","","dgreg","ggg","dferg@gmail.com");
        assertEquals(4,d);
        int e=LoginMenuController.userRegister("ali","efgreg","dgreg","","dferg@gmail.com");
        assertEquals(5,e);
        int f=LoginMenuController.userRegister("ali","efgreg","dgreg","vfvv","dferg@gmail.com");
        assertEquals(6,f);
        int f1=LoginMenuController.userRegister("ali","efgregDF","dgreg","vfvv","dferg@gmail.com");
        assertEquals(6,f1);
        int f2=LoginMenuController.userRegister("ali","efgregDF5","dgreg","vfvv","dferg@gmail.com");
        assertEquals(6,f2);
        int g=LoginMenuController.userRegister("ali","efgregDF5!!","dgreg","vfvv","dferg@gmail.com");
        assertEquals(7,g);
        int h=LoginMenuController.userRegister("ali","efgregDF5!!","efgregDF5!!","vfvv","dferg@gmail.com");
        assertEquals(0,h);
       int aa=LoginMenuController.setNewPassword("ali","","");
        assertEquals(1,aa);
        int ab=LoginMenuController.setNewPassword("ali","fefgDDF3","");
        assertEquals(2,ab);
        int ac=LoginMenuController.setNewPassword("ali","fefgDDF3!","frfrf");
        assertEquals(3,ac);
        int ad=LoginMenuController.setNewPassword("ali","fefgDDF3!","fefgDDF3!");
        assertEquals(0,ad);
        assertTrue(LoginMenuController.isPasswordCorrect(User.getUserByUsername("ali"),"fefgDDF3!"));
        User.getUsers().remove(user);
    }
    @Test
    public void testSetSecurityQ(){
      assertEquals(1,LoginMenuController.setSecurityQ(User.getUsers().get(0).getUsername(),"","",""));
      assertEquals(2,LoginMenuController.setSecurityQ(User.getUsers().get(0).getUsername(),"a","a",""));
        assertEquals(2,LoginMenuController.setSecurityQ(User.getUsers().get(0).getUsername(),"a","a","b"));
        assertEquals(0,LoginMenuController.setSecurityQ(User.getUsers().get(0).getUsername(),"abi","",""));
        assertEquals(0,LoginMenuController.setSecurityQ(User.getUsers().get(0).getUsername(),"","abi",""));
        assertEquals(0,LoginMenuController.setSecurityQ(User.getUsers().get(0).getUsername(),"","","abi"));
    }
    @Test
    public void  testAnswerSecurityQ(){
        User user=new User("c","c","c","c");
        user.setAnswerOfSecurityQuestion("red");
        assertEquals(1,LoginMenuController.hasAnsweredCorrectly("ff","red"));
        assertEquals(2,LoginMenuController.hasAnsweredCorrectly("c","blue"));
        assertEquals(0,LoginMenuController.hasAnsweredCorrectly("c","red"));
        User.getUsers().remove(user);
    }
}

