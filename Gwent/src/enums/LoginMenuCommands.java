package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands {
    registerCommand("register -u (?<username>\\S+) -p (?<password>\\S+) (?<passwordConfirm>\\S+) -n (?<nickname>\\S+) -e (?<email>\\S+)"),
    pickQuestionCommand("pick question -q (?<questionNumber>\\d+) -a (?<answer>\\.*) -c (?<answerConfirm>\\.*)"),
    loginCommand("login -u (?<username>\\S+) -p (?<password>\\S+) –stay-logged-in"),
    forgotPasswordCommand("forget password -u <username>"),
    answerQuestionForForgotPasswordCommand("answer -q (?<questionNumber>\\d+) -a (?<answer>\\.*)"),
    setNewPasswordCommand("set password -p (?<password>\\S+)"),
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
