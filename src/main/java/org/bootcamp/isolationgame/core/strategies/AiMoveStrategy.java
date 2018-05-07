package org.bootcamp.isolationgame.core.strategies;

import java.awt.Point;

import org.bootcamp.isolationgame.core.models.GameState;

public interface AiMoveStrategy {
    Point getMove(Point currentPosition, GameState state);

    Point getMove(GameState state);
}
