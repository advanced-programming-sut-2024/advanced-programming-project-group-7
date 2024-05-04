package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands {
    ChangeUsername("change username -u <username>"),//todo regex for enums
    ChangeNickname("change nickname -u <nickname>"),
    ChangeEmail("change email -e <email>"),
    ChangePassword("change password -p <new_password> -o <old_password>"),
    UserInfo("menu enter user info"),
    ShowCurrentMenu("show menu"),
    MenuExit("menu exit"),
    GameHistory("game history -n <n>");

    private final String pattern;

    ProfileMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);

        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}