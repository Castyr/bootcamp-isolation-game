package org.bootcamp.isolationgame.core.models;

import java.util.*;
import java.util.stream.Collectors;

public class GameState {
    private final int[][] state;
    private final boolean gameOver;
    private final int currentPlayer;
    private final int winner;
    private final Stack<Move> moves;

    public GameState(int[][] state, int currentPlayer) {
        this.state = state;
        this.gameOver = false;
        this.currentPlayer = currentPlayer;
        this.winner = -1;
        this.moves = new Stack<>();
    }

    public GameState(int[][] state, int currentPlayer, Stack<Move> moves) {
        this.state = state;
        this.currentPlayer = currentPlayer;
        this.moves = moves;
        this.gameOver = false;
        this.winner = -1;
    }

    public GameState(int[][] state, int currentPlayer, int winner, Stack<Move> moves) {
        this.state = state;
        this.gameOver = true;
        this.currentPlayer = currentPlayer;
        this.winner = winner;
        this.moves = moves;
    }

    public List<List<Integer>> getState() {
        return Arrays.stream(state)
                .map(arr -> Arrays.stream(arr).boxed().collect(Collectors.toCollection(ArrayList::new)))
                .collect(Collectors.toCollection(ArrayList<List<Integer>>::new));
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int getWinner() {
        return winner;
    }

    public Move getLastMove() {
        if (moves.size() > 0) {
            return moves.peek();
        }
        return null;
    }

    public Move[] getMoves() {
        Move[] moveArr = new Move[moves.size()];
        moves.copyInto(moveArr);
        return moveArr;
    }

    public int getPosition(int x, int y) {
        return state[x][y];
    }

    public Move getPlayersLastMove(int player) {
        for (int i = moves.size() - 1; i >= 0; i--) {
            if (player == moves.get(i).getPlayer()) {
                return moves.get(i);
            }
        }
        return null;
    }
}
