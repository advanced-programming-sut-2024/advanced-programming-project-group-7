package controller;

import model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileMenuControllerTest {

    @Test
    void changeUsername() {
        assertEquals(1,ProfileMenuController.changeUsername(""));
        assertEquals(2,ProfileMenuController.changeUsername(User.getUsers().get(0).getUsername()));
        ProfileMenuController.currentUser=new User("cc","cc","cc","cc");
        assertEquals(0,ProfileMenuController.changeUsername("rgregreegv"));
        User.getUsers().remove(User.getUserByUsername("cc"));
    }

    @Test
    void changeNickname() {
        ProfileMenuController.currentUser=new User("cc","cc","cc","cc");
        assertEquals(1,ProfileMenuController.changeNickname(""));
        assertEquals(0,ProfileMenuController.changeNickname("eff"));
        User.getUsers().remove(User.getUserByUsername("cc"));


    }

    @Test
    void changeEmail() {
        ProfileMenuController.currentUser=new User("cc","cc","cc","cc");
        assertEquals(1,ProfileMenuController.changeEmail(""));
        assertEquals(0,ProfileMenuController.changeEmail("eff@gmail.com"));
        User.getUsers().remove(User.getUserByUsername("cc"));
    }

    @Test
    void changePassword() {
        ProfileMenuController.currentUser=new User("cc","cc","cc","cc");
        assertEquals(1,ProfileMenuController.changePassword("",""));
        assertEquals(2,ProfileMenuController.changePassword("dd",""));
        assertEquals(3,ProfileMenuController.changePassword("cc",""));
        assertEquals(4,ProfileMenuController.changePassword("cc","d"));
        assertEquals(0,ProfileMenuController.changePassword("cc","aaaaAA123!!"));
        User.getUsers().remove(User.getUserByUsername("cc"));
    }

}