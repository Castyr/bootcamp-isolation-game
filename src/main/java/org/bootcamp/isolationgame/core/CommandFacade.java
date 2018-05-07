package org.bootcamp.isolationgame.core;

import java.awt.*;

import org.bootcamp.isolationgame.core.commands.Invoker;
import org.bootcamp.isolationgame.core.commands.MoveCommand;
import org.bootcamp.isolationgame.core.services.GameManager;

public class CommandFacade {
    private final Invoker invoker = new Invoker();
    private final GameManager gameManager;

    public CommandFacade(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void move(Point movePosition, int player) {
        invoker.setCommand(new MoveCommand(player, movePosition.x, movePosition.y, gameManager));
        invoker.execute();
    }
}
