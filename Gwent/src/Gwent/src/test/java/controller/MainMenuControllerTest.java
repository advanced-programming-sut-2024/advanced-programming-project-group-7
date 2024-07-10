package controller;

import model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuControllerTest {

    @Test
    void logoutTest() {
        User user=User.getUsers().get(0);
        User.setLoggedInUser(user);
        MainMenuController.logout();
        assertNull(User.getLoggedInUser());
    }
}