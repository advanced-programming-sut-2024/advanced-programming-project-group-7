package enums;

import model.Card;

public enum CardConstructingInfo {
    geralt(""),
    Yennifer (""),
    vesemir(""),
    clearWeather(""),
    ciri("");
    private final String pattern;
    CardConstructingInfo(String pattern) {
        this.pattern = pattern;
    }
    public void newCardConstructor(String info){}
}
