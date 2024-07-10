package model;

import java.util.ArrayList;

public class FinishedGame {
    public final String p1;
    public final String p2;
    public ArrayList<String> moves = new ArrayList<>();

    public FinishedGame(String p1, String p2, ArrayList<String> moves) {
        this.p1 = p1;
        this.p2 = p2;
        this.moves = moves;
    }
}
