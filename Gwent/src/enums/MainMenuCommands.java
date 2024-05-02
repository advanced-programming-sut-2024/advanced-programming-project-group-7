package enums;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands {

        EnterProfileMenu("enter profile menu"),
        EnterGameMenu("enter game menu"),
        Logout("user logout");

        private final String pattern;

        MainMenuCommands(String pattern) {
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
