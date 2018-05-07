package org.bootcamp.isolationgame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bootcamp.isolationgame.core.CommandFacade;
import org.bootcamp.isolationgame.core.services.AiPlayer;
import org.bootcamp.isolationgame.core.services.GameManager;
import org.bootcamp.isolationgame.core.services.IsoGameManager;
import org.bootcamp.isolationgame.core.strategies.IsolationRuleStrategy;
import org.bootcamp.isolationgame.core.strategies.MinMaxAiMoveStrategy;
import org.bootcamp.isolationgame.core.strategies.RuleStrategy;
import org.bootcamp.isolationgame.gui.factory.LayoutBuilder;

public class IsolationGameApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // setup game manager and AI
        GameManager gameManager = IsoGameManager.getInstance();
        RuleStrategy ruleStrategy = new IsolationRuleStrategy();
        gameManager.setRuleStrategy(ruleStrategy);
        CommandFacade commandFacade = new CommandFacade(gameManager);
        AiPlayer aiPlayer = new AiPlayer(new MinMaxAiMoveStrategy(ruleStrategy), commandFacade);
        gameManager.addObserver(aiPlayer);

        // setup UI
        Scene scene = new Scene(new LayoutBuilder(gameManager, commandFacade).createLayout());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
