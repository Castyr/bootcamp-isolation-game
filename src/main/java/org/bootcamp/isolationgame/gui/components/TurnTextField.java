package org.bootcamp.isolationgame.gui.components;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TurnTextField extends StackPane {
    private Text text = new Text();

    public TurnTextField() {
        text.setFont(Font.font(18));
        text.setStroke(Color.BLACK);
        text.setText("");
        text.setVisible(false);
        setTranslateX(400);
        setTranslateY(10);
        getChildren().addAll(text);
    }
}
