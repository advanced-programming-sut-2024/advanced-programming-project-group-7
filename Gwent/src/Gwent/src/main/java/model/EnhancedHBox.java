package model;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.awt.*;

public class EnhancedHBox extends HBox {
    public boolean isDoubled = false;
    public int moralBoostLevel = 0;
    public boolean badCondition = false;
    public Label powerSum = new Label();
    public HBox hornBox = new HBox();
}
