package org.bootcamp.isolationgame.core.strategies;

import java.awt.Point;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.bootcamp.isolationgame.core.models.GameState;
import org.bootcamp.isolationgame.core.utils.Config;

public class RandomAiMoveStrategy implements AiMoveStrategy {
    private final RuleStrategy ruleStrategy;

    public RandomAiMoveStrategy(RuleStrategy ruleStrategy) {
        this.ruleStrategy = ruleStrategy;
    }

    @Override
    public Point getMove(Point currentPosition, GameState state) {
        List<List<Integer>> board = state.getState();
        List<Point> avaliableMoves = ruleStrategy.getAvaliableMoves(currentPosition.x, currentPosition.y, board);
        int randomNum = ThreadLocalRandom.current().nextInt(0, avaliableMoves.size());
        return avaliableMoves.get(randomNum);
    }

    @Override
    public Point getMove(GameState state) {
        List<List<Integer>> board = state.getState();
        Point point = new Point(ThreadLocalRandom.current()
                .nextInt(0, Config.getxTiles() - 1),
                    ThreadLocalRandom.current()
                    .nextInt(1, Config.getyTiles() - 1));
        if (board.get(point.y).get(point.x) == -1) {
            return point;
        }
        point = new Point(state.getLastMove().getX(), state.getLastMove().getY());
        return getMove(point, state);
    }
}
