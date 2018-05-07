package org.bootcamp.isolationgame.core.strategies;

import java.awt.Point;
import java.util.*;

public interface RuleStrategy {
    List<Point> getAvaliableMoves(int x, int y, List<List<Integer>> state);

    List<Point> getAvaliableMoves(int x, int y, int[][] state);

}
