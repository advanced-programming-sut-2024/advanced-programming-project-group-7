package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    VetoCard("veto card (?<card_number>\\d+"),
    HandInfo("in hand deck(?<card_number>-option \\d+)?"),
    RemainingCardsCount("remaining cards to play"),
    ShowGraveyard("show graveyard"),
    ShowRow("cards in row (?<row_number>\\d)"),
    ShowSpells("spells in play"),
    PlaceCard("place card (?<card_number>\\d+) in row (?<row_number>\\d+)"),
    ShowCommander(" show commander"),
    PlayCommander(" commander power play"),
    ShowPlayersInfo("show players info"),
    ShowPlayerLives("show players lives"),
    ShowNumberOfCardsInHand("show number of cards in hand"),
    TurnInfo("show turn info"),
    ShowTotalScore("show total score"),
    ShowTotalRowScore("show total score of row (?<row_number>\\d)"),
    PassRound("pass round"),
    ShowCurrentMenu("show menu"),
    Back("back");
    private final String pattern;

    GameMenuCommands(String pattern) {
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
