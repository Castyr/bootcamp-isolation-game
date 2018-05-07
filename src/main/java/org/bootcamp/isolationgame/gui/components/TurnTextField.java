package org.bootcamp.isolationgame.gui.components;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.bootcamp.isolationgame.core.models.GameState;

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
        javafx.application.Platform.runLater(() -> {
            GameState state = (GameState) arg;
            if (state != null && player != state.getCurrentPlayer()) {
                player = state.getCurrentPlayer();
                text.setText(String.format("Player %ss turn", player));
                text.setVisible(true);
            }
        });
    }
}
