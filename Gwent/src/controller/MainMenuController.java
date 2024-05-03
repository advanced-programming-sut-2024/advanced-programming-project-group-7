package controller;
import java.util.regex.Matcher;

public class MainMenuController{

   // public void handleCommand(String input) {
//        for (MainMenuCommands command : MainMenuCommands.values()) {
//            Matcher matcher = command.getMatcher(input);
//            if (matcher != null) {
//                switch (command) {
//                    case EnterProfileMenu:
//                        enterProfileMenu();
//                        break;
//                    case EnterGameMenu:
//                        enterGameMenu();
//                        break;
//                    case Logout:
//                        logout();
//                        break;
//                }
//            }
//        }
//    }

    public static void enterProfileMenu(Matcher matcher) {
        //todo
        System.out.println("Entering Profile Menu");
    }

        public static void enterGameMenu(Matcher matcher) {
        //todo
        System.out.println("Entering Game Menu");
    }

        public static void logout(Matcher matcher) {
        //todo
        System.out.println("Logging out");
    }
}
