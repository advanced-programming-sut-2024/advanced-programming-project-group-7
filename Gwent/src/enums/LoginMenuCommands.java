package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands {
    RegisterCommand("register -u (?<username>\\S+) -p (?<password>\\S+) (?<passwordConfirm>\\S+) -n (?<nickname>\\S+) -e (?<email>\\S+)"),
    PickQuestionCommand("pick question -q (?<questionNumber>\\d+) -a (?<answer>\\.*) -c (?<answerConfirm>\\.*)"),
    LoginCommand("login -u (?<username>\\S+) -p (?<password>\\S+) –stay-logged-in"),
    ForgotPasswordCommand("forget password -u <username>"),
    AnswerQuestionForForgotPasswordCommand("answer -q (?<questionNumber>\\d+) -a (?<answer>\\.*)"),
    ShowCurrentMenu("show menu"),
    EnterRegisterMenu(""),
    MenuExit("menu exit"),
    SetNewPasswordCommand("set password -p (?<password>\\S+)"),
    GoToMainMenu("")
        ;
    public final String regex;
    LoginMenuCommands(String regex) {
        this.regex = regex;
    }
    public Matcher getMatcher(String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        matcher.matches();
        return matcher;
    }
}
