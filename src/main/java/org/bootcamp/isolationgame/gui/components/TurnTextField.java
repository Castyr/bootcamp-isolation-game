package org.bootcamp.isolationgame.gui.components;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.bootcamp.isolationgame.core.models.GameState;

import java.util.Observable;
import java.util.Observer;

import static javafx.application.Platform.runLater;

public class TurnTextField extends StackPane implements Observer {
    private Text text = new Text();
    private int player = -1;
    public TurnTextField() {
        text.setFont(Font.font(18));
        text.setStroke(Color.BLACK);
        text.setText("");
        text.setVisible(false);
        setTranslateX(400);
        setTranslateY(10);
        getChildren().addAll(text);
    }

    @Override
    public void update(Observable o, Object arg) {
        runLater(() -> {
            GameState state = (GameState) arg;
            if (state != null && state.getCurrentPlayer() != player) {
                player = state.getCurrentPlayer();
                text.setText(String.format("Players %s turn", player));
                text.setVisible(true);
            }
        });
    }
}
