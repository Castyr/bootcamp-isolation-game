package org.bootcamp.isolationgame.gui.components;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.bootcamp.isolationgame.core.models.GameState;

import static javafx.application.Platform.runLater;

public class GameOverHeading extends StackPane implements Observer {
    private Text text = new Text();

    public GameOverHeading() {
        text.setFont(Font.font(24));
        text.setStroke(Color.RED);
        text.setText("");
        text.setVisible(false);
        setTranslateX(10);
        setTranslateY(10);
        getChildren().addAll(text);
    }

    @Override
    public void update(Observable o, Object arg) {
        runLater(() -> {
            GameState state = (GameState) arg;
            if (state == null || !state.isGameOver()) {
                text.setText("");
                text.setVisible(false);
                return;
            }
            text.setText(String.format("Game Over, Player %s won!", state.getWinner()));
            text.setVisible(true);
        });
    }
}
