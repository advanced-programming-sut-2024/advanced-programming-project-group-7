package view;
import controller.LoginMenuController;
import enums.LoginMenuCommands;
import java.util.Scanner;
import java.util.regex.Matcher;
public class LoginMenu {
    public static void run(Scanner scanner){
        String inputCommand=scanner.nextLine();
        if(LoginMenuCommands.RegisterCommand.getMatcher(inputCommand).matches()){
            Matcher registerMatcher=LoginMenuCommands.RegisterCommand.getMatcher(inputCommand);
            if(LoginMenuController.isUsernameDuplicate(registerMatcher)) System.out.println("");
            else if(! LoginMenuController.isUsernameValid(registerMatcher)) System.out.println("");
            else if(! LoginMenuController.isEmailAddressValid(registerMatcher)) System.out.println("");
            else if(! LoginMenuController.isPasswordValid(registerMatcher)) System.out.println("");
            else if(LoginMenuController.isPasswordWeak(registerMatcher)) System.out.println(" ");
            else if(! LoginMenuController.isPasswordConfirmed(registerMatcher)) System.out.println(" ");
            else{
                //todo show questions
//                LoginMenuController.pickSecurityQuestion(registerMatcher);//todo
            }
        }
        if(LoginMenuCommands.LoginCommand.getMatcher(inputCommand).matches()) {
            Matcher loginMatcher = LoginMenuCommands.LoginCommand.getMatcher(inputCommand);
            if(! LoginMenuController.isUsernameDuplicate(loginMatcher)) System.out.println("");
            else if(! LoginMenuController.isPasswordCorrect(loginMatcher)){
                System.out.println("");

            }
            else {

                LoginMenuController.login(loginMatcher);
            }
        }


    }
}
