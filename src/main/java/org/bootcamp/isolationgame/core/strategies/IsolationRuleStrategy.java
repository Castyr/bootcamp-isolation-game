package org.bootcamp.isolationgame.core.strategies;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.bootcamp.isolationgame.core.utils.Config;

public class IsolationRuleStrategy implements RuleStrategy {
    public List<Point> getAvaliableMoves(int x, int y, List<List<Integer>> state) {
        final int numxTiles = Config.getxTiles();
        final int numyTiles = Config.getyTiles();
        List<Point> avaliablePositions = new ArrayList<>();
        if (x > 0 && state.get(x - 1).get(y) == -1) {
            avaliablePositions.add(new Point(x - 1, y));
        }
        if (y > 1 && state.get(x).get(y - 1) == -1) {
            avaliablePositions.add(new Point(x, y - 1));
        }
        if (y < numyTiles - 1 && state.get(x).get(y + 1) == -1) {
            avaliablePositions.add(new Point(x, y + 1));
        }
        if (x < numxTiles - 1 && state.get(x + 1).get(y) == -1) {
            avaliablePositions.add(new Point(x + 1, y));
        }
        // diagonal moves
        if (x > 0 && y > 1 && state.get(x - 1).get(y - 1) == -1) {
            avaliablePositions.add(new Point(x - 1, y - 1));
        }
        if (x > 0 && y < numyTiles - 1 && state.get(x - 1).get(y + 1) == -1) {
            avaliablePositions.add(new Point(x - 1, y + 1));
        }
        if (x < numxTiles - 1 && y < numyTiles - 1 && state.get(x + 1).get(y + 1) == -1) {
            avaliablePositions.add(new Point(x + 1, y + 1));
        }
        if (x < numxTiles - 1 && y > 1 && state.get(x + 1).get(y - 1) == -1) {
            avaliablePositions.add(new Point(x + 1, y - 1));
        }
        return avaliablePositions;
    }

    public List<Point> getAvaliableMoves(int x, int y, int[][] state) {
        final int numxTiles = Config.getxTiles();
        final int numyTiles = Config.getyTiles();
        List<Point> avaliablePositions = new ArrayList<>();
        if (x > 0 && state[x - 1][y] == -1) {
            avaliablePositions.add(new Point(x - 1, y));
        }
        if (y > 1 && state[x][y - 1] == -1) {
            avaliablePositions.add(new Point(x, y - 1));
        }
        if (y < numyTiles - 1 && state[x][y + 1] == -1) {
            avaliablePositions.add(new Point(x, y + 1));
        }
        if (x < numxTiles - 1 && state[x + 1][y] == -1) {
            avaliablePositions.add(new Point(x + 1, y));
        }
        // diagonal moves
        if (x > 0 && y > 1 && state[x - 1][y - 1] == -1) {
            avaliablePositions.add(new Point(x - 1, y - 1));
        }
        if (x > 0 && y < numyTiles - 1 && state[x - 1][y + 1] == -1) {
            avaliablePositions.add(new Point(x - 1, y + 1));
        }
        if (x < numxTiles - 1 && y < numyTiles - 1 && state[x + 1][y + 1] == -1) {
            avaliablePositions.add(new Point(x + 1, y + 1));
        }
        if (x < numxTiles - 1 && y > 1 && state[x + 1][y - 1] == -1) {
            avaliablePositions.add(new Point(x + 1, y - 1));
        }
        return avaliablePositions;
    }
}
