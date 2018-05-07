package org.bootcamp.isolationgame.gui.builders;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import org.bootcamp.isolationgame.core.CommandFacade;
import org.bootcamp.isolationgame.core.services.GameManager;
import org.bootcamp.isolationgame.core.utils.Config;
import org.bootcamp.isolationgame.gui.components.GameOverHeading;
import org.bootcamp.isolationgame.gui.components.RestartButton;
import org.bootcamp.isolationgame.gui.components.Tile;
import org.bootcamp.isolationgame.gui.components.TurnTextField;

public class LayoutBuilder {
    private Tile[][] grid;
    private final GameManager gameManager;
    private final CommandFacade commandFacade;

    public LayoutBuilder(GameManager gameManager, CommandFacade commandFacade) {
        this.gameManager = gameManager;
        this.commandFacade = commandFacade;
        this.grid = new Tile[Config.getxTiles()][Config.getyTiles()];
    }

    public Parent createLayout() {
        Pane root = new Pane();
        root.setPrefSize(Config.getW(), Config.getH());
        GameOverHeading gameOverHeading = new GameOverHeading();
        root.getChildren().add(gameOverHeading);
        RestartButton button = new RestartButton(commandFacade);
        root.getChildren().add(button);
        TurnTextField text = new TurnTextField();
        root.getChildren().add(text);
        gameManager.addObserver(gameOverHeading);
        gameManager.addObserver(text);
        for (int y = 1; y < Config.getyTiles(); y++) {
            for (int x = 0; x < Config.getxTiles(); x++) {
                Tile tile = new Tile(x, y, commandFacade);

                grid[x][y] = tile;
                root.getChildren().add(tile);
                gameManager.addObserver(tile);
            }
        }
        return root;
    }
}
