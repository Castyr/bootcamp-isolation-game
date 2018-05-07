package org.bootcamp.isolationgame.core.commands;

import java.util.logging.Logger;

import org.bootcamp.isolationgame.core.services.GameManager;

public class ResetCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ResetCommand.class.getName());
    private final GameManager gameManager;

    public ResetCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void execute() {
        LOGGER.warning("Resetting game!");
        gameManager.reset();
    }
}
