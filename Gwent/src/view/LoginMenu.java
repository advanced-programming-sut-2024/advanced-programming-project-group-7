package view;
import controller.LoginMenuController;
import enums.LoginMenuCommands;
import java.util.Scanner;
import java.util.regex.Matcher;
public class LoginMenu {
    public static void run(Scanner scanner){
        String inputCommand=scanner.nextLine();
        if(LoginMenuCommands.registerCommand.getMatcher(inputCommand).matches()){
            Matcher registerMatcher=LoginMenuCommands.registerCommand.getMatcher(inputCommand);
            if(LoginMenuController.isUsernameDuplicate(registerMatcher)) System.out.println("");
            else if(!LoginMenuController.isUsernameValid(registerMatcher)) System.out.println("");
        }
    }
}
