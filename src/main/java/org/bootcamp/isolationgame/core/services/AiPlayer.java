package org.bootcamp.isolationgame.core.services;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import org.bootcamp.isolationgame.core.CommandFacade;
import org.bootcamp.isolationgame.core.models.GameState;
import org.bootcamp.isolationgame.core.strategies.AiMoveStrategy;

public class AiPlayer implements Observer {
    private int playerNum = 1;
    private AiMoveStrategy moveStrategy;
    private Point position = null;
    private CommandFacade commandFacade;

    public AiPlayer(AiMoveStrategy moveStrategy, CommandFacade commandFacade) {
        this.commandFacade = commandFacade;
        this.moveStrategy = moveStrategy;
    }

    @Override
    public void update(Observable o, Object arg) {
        GameState state = (GameState) arg;
        if (state == null) {
            return;
        }
        if (playerNum == state.getCurrentPlayer()) {
            if (position == null) {
                position = moveStrategy.getMove(state);
            } else {
                position = moveStrategy.getMove(position, state);
            }
            commandFacade.move(position, playerNum);
        }
    }
}
