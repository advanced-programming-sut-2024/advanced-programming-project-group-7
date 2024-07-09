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

//    @Test
//    public void testUserLogin_UnregisteredUsername() {
//        // Attempt to log in with an unregistered username
//        Alert alert = LoginMenuController.userLogin("unregisteredUser", "password");
//
//        // Check if the alert is not null and has the correct header text
//        assertNotNull(alert);
//        assertEquals(Alert.AlertType.WARNING, alert.getAlertType());
//        assertEquals("you haven't registered", alert.getHeaderText());
//    }

//    @Test
//    public void testUserLogin_WrongPassword() {
//        // Register a user
//        User user = new User("registeredUser", "correctPassword", "nickname", "email@example.com");
//        User.addUser(user);
//
//        // Attempt to log in with the wrong password
//        Alert alert = LoginMenuController.userLogin("registeredUser", "wrongPassword");
//
//        // Check if the alert is not null and has the correct header text
//        assertNotNull(alert);
//        assertEquals(Alert.AlertType.WARNING, alert.getAlertType());
//        assertEquals("wrong password", alert.getHeaderText());
//    }
//

//    @Test
//    public void testUserLogin_WrongPassword() {
//
//        Alert alert=new Alert(Alert.AlertType.WARNING);
//        alert.setHeaderText("wrong password");
//        assertNotNull(alert);
//    }

//    @Test
//    public void testUserLogin_WrongPassword() {
//        Platform.runLater(() -> {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setHeaderText("wrong password");
//            assertNotNull(alert);
//        });
//    }


//    @Test
//    public void testUserLogin_WrongPassword() {
//        final CountDownLatch latch = new CountDownLatch(1);
//        final String[] headerText = new String[1];
//
//        Platform.runLater(() -> {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setHeaderText("wrong password");
//            headerText[0] = alert.getHeaderText();
//            latch.countDown();
//        });
//
//        try {
//            latch.await();
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//
//        assertEquals("wrong password", headerText[0]);
//    }

    @Test
    public void testUserLogin_Success() {
        // Register a user
        User user = new User("registeredUser", "correctPassword", "nickname", "email@example.com");
        User.addUser(user);

        // Attempt to log in with the correct username and password
        Alert alert = LoginMenuController.userLogin("registeredUser", "correctPassword");

        // Check if the alert is null (indicating a successful login)
        assertNull(alert);
    }
}

