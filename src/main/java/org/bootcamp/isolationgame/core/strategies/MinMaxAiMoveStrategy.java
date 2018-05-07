package org.bootcamp.isolationgame.core.strategies;

import java.awt.Point;
import java.util.*;

import org.bootcamp.isolationgame.core.models.GameState;
import org.bootcamp.isolationgame.core.utils.Config;

public class MinMaxAiMoveStrategy implements AiMoveStrategy {
    private GameState state;
    private final int aiPlayer = 1;
    private final int humanPlayer = 0;
    private final RuleStrategy ruleStrategy;

    public MinMaxAiMoveStrategy(RuleStrategy ruleStrategy) {
        this.ruleStrategy = ruleStrategy;
    }

    @Override
    public Point getMove(Point currentPosition, GameState state) {
        this.state = state;
        List<List<Integer>> boardList = state.getState();
        int[][] board = new int[boardList.size()][boardList.get(0).size()];
        for (int i = 0; i < boardList.size(); i++) {
            for (int j = 0; j < boardList.get(0).size(); j++) {
                board[i][j] = boardList.get(i).get(j);
            }
        }
        int[] bestMove = minimax(10, board, 1, currentPosition,
                new Point(state.getLastMove().getX(), state.getLastMove().getY()));
        return new Point(bestMove[1], bestMove[2]);
    }

    @Override
    public Point getMove(GameState state) {
        List<List<Integer>> board = state.getState();
        Point point = new Point((Config.getxTiles() / 2) + 1, Config.getyTiles() / 2);
        if (board.get(point.y).get(point.x) == -1) {
            return point;
        }
        point = new Point(state.getLastMove().getX(), state.getLastMove().getY());
        return getMove(point, state);
    }

    /** Recursive minimax at level of depth for either maximizing or minimizing player.
     Return int[3] of {score, row, col}  */
    private int[] minimax(int depth, int[][] board, int player, Point currentPosition, Point opponentMove) {
        // Generate possible next moves in a List of int[2] of {row, col}.
        List<Point> nextMoves;
        if (player == aiPlayer) {
            nextMoves = ruleStrategy.getAvaliableMoves(currentPosition.x, currentPosition.y, board);
        } else {
            nextMoves = ruleStrategy.getAvaliableMoves(opponentMove.x, opponentMove.y, board);
        }


        // mySeed is maximizing; while oppSeed is minimizing
        int bestScore = (player == humanPlayer) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        int currentScore;
        int bestRow = -1;
        int bestCol = -1;

        if (nextMoves.isEmpty() || depth == 0) {
            // Gameover or depth reached, evaluate score
            if (player == aiPlayer) {
                bestScore = evaluate(board, currentPosition, opponentMove);
            } else {
                bestScore = evaluate(board, opponentMove, currentPosition);
            }
        } else {
            for (Point move : nextMoves) {
                int x = move.x;
                int y = move.y;
                // Try this move for the current "player"
                board[x][y] = player;
                if (player == aiPlayer) {  // mySeed (computer) is maximizing player
                    currentScore = minimax(depth - 1, board,  humanPlayer, move, opponentMove)[0];
                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestRow = x;
                        bestCol = y;
                    }
                } else {  // oppSeed is minimizing player
                    currentScore = minimax(depth - 1, board, aiPlayer, currentPosition, move)[0];
                    if (currentScore < bestScore) {
                        bestScore = currentScore;
                        bestRow = x;
                        bestCol = y;
                    }
                }
                // Undo move
                board[x][y] = -1;
            }
        }
        return new int[] { bestScore, bestRow, bestCol };
    }

    private int evaluate(int[][] board, Point currentPosition, Point opponentPosition) {
        List<Point> avaliableMoves = ruleStrategy.getAvaliableMoves(currentPosition.x, currentPosition.y, board);
        List<Point> opponentMoves = ruleStrategy.getAvaliableMoves(opponentPosition.x, opponentPosition.y, board);
        return avaliableMoves.size() - opponentMoves.size();
    }
}
