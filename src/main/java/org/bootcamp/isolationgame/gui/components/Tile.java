package org.bootcamp.isolationgame.gui.components;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.bootcamp.isolationgame.core.CommandFacade;
import org.bootcamp.isolationgame.core.models.GameState;
import org.bootcamp.isolationgame.core.utils.Config;

public class Tile extends StackPane implements Observer {
    private int x;
    private int y;
    private int player;

    private Rectangle border = new Rectangle(Config.getTileSize() - 2, Config.getTileSize() - 2);
    private Text text = new Text();
    private CommandFacade commandFacade;

    public Tile(int x, int y, CommandFacade commandFacade) {
        this.x = x;
        this.y = y;
        this.player = -1;

        border.setStroke(Color.LIGHTGRAY);

        text.setFont(Font.font(18));
        text.setText("");
        text.setVisible(false);

        getChildren().addAll(border, text);

        setTranslateX(x * Config.getTileSize());
        setTranslateY(y * Config.getTileSize());

        setOnMouseClicked(e -> move());

        this.commandFacade = commandFacade;
    }

    public void move() {
        try {
            commandFacade.move(new Point(x, y), 0);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        GameState state = (GameState) arg;
        if (state == null || state.getLastMove() == null
                || state.getLastMove().getX() != x
                || state.getLastMove().getY() != y) {
            if (state.getLastMove() != null && state.getLastMove().getPlayer() == player) {
                text.setStroke(Color.GRAY);
            } else if (state.getLastMove() == null) {
                text.setText("");
                text.setVisible(false);
                border.setFill(Color.BLACK);
            }
            return;
        }
        player = state.getLastMove().getPlayer();
        text.setText(player == 1 ? "o" : "x");
        if (state.getLastMove().getPlayer() == 1) {
            text.setStroke(Color.BLUE);
        } else {
            text.setStroke(Color.RED);
        }

        text.setVisible(true);
        border.setFill(null);
    }
}
