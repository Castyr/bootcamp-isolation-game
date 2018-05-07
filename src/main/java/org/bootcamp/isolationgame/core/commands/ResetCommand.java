package org.bootcamp.isolationgame.core.commands;

import org.bootcamp.isolationgame.core.services.GameManager;

public class ResetCommand implements Command {
    private GameManager gameManager;

    public ResetCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void execute() {
        gameManager.reset();
    }
}
