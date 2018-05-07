package org.bootcamp.isolationgame.core.commands;

import java.awt.Point;
import java.util.List;
import java.util.logging.Logger;
import org.bootcamp.isolationgame.core.models.GameState;
import org.bootcamp.isolationgame.core.models.Move;
import org.bootcamp.isolationgame.core.services.GameManager;
import org.bootcamp.isolationgame.core.utils.Config;

public class MoveCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(MoveCommand.class.getName());
    private final int player;
    private final int x;
    private final int y;
    private final GameManager gameManager;

    public MoveCommand(int player, int x, int y, GameManager gameManager) {
        this.player = player;
        this.x = x;
        this.y = y;
        this.gameManager = gameManager;
    }

    @Override
    public void execute() {
        try {
            LOGGER.info(String.format("Move: (%s, %s, %s)", player, x, y));
            GameState currentState = gameManager.getState();
            if (currentState.isGameOver()) {
                throw new Exception("Cannot move when the game is over!");
            }
            if (player != currentState.getCurrentPlayer()) {
                throw new Exception("Cannot move when it is not your turn!");
            }
            if (x < 0 || y < 1 || y >= Config.getyTiles() || x >= Config.getxTiles()) {
                throw new Exception("Move is out of board bounds!");
            }
            if (currentState.getPosition(x, y) != -1) {
                throw new Exception("Cannot move to space already filled.");
            }
            Move lastMove = currentState.getPlayersLastMove(player);
            if (lastMove != null) {
                List<Point> legalMoves = gameManager.getRuleStrategy()
                        .getAvaliableMoves(lastMove.getX(), lastMove.getY(),
                                currentState.getState());
                boolean moveInLegalMoves = false;
                for (Point point : legalMoves) {
                    if (point.x == x && point.y == y) {
                        moveInLegalMoves = true;
                    }
                }
                if (!moveInLegalMoves) {
                    throw new Exception("Illegal move!");
                }
            }
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
            return;
        }
        gameManager.update(new Move(x, y, player));
    }
}
