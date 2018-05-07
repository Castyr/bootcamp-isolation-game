package org.bootcamp.isolationgame.core.services;

import java.util.*;
import java.util.stream.Collectors;

import org.bootcamp.isolationgame.core.models.GameState;
import org.bootcamp.isolationgame.core.models.Move;
import org.bootcamp.isolationgame.core.strategies.RuleStrategy;
import org.bootcamp.isolationgame.core.utils.Config;

public class IsoGameManager implements GameManager {
    private GameState state;
    private int[][] board;
    private StateObservable stateObservable;
    private RuleStrategy ruleStrategy;
    private static IsoGameManager instance = null;

    protected IsoGameManager() {
        stateObservable = new StateObservable();
        reset();
    }

    public static IsoGameManager getInstance() {
        if (instance == null) {
            instance = new IsoGameManager();
        }
        return instance;
    }

    public void reset() {
        board = new int[Config.getxTiles()][Config.getyTiles()];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = -1;
            }
        }
        state = new GameState(board, 0);

        stateObservable.notifyStateChanged(state);
    }

    public void addObserver(Observer observer) {
        stateObservable.addObserver(observer);
    }

    public RuleStrategy getRuleStrategy() {
        return ruleStrategy;
    }

    public void setRuleStrategy(RuleStrategy ruleStrategy) {
        this.ruleStrategy = ruleStrategy;
    }

    public void update(Move move) {
        Move lastMove = state.getLastMove();
        board[move.getX()][move.getY()] = move.getPlayer();
        if (lastMove != null
                && ruleStrategy.getAvaliableMoves(lastMove.getX(), lastMove.getY(), board).size() == 0) {
            Stack<Move> moves = new Stack<>();
            moves.addAll(Arrays.stream(state.getMoves()).collect(Collectors.toCollection(ArrayList<Move>::new)));
            moves.push(move);
            state = new GameState(board, state.getCurrentPlayer(), state.getCurrentPlayer(), moves);
        } else {
            Stack<Move> moves = new Stack<>();
            moves.addAll(Arrays.stream(state.getMoves()).collect(Collectors.toCollection(ArrayList<Move>::new)));
            moves.push(move);
            state = new GameState(board, state.getCurrentPlayer() ^ 1, moves);
        }

        stateObservable.notifyStateChanged(state);
    }

    public GameState getState() {
        return state;
    }

    private class StateObservable extends Observable {
        public void notifyStateChanged(GameState state) {
            setChanged();
            notifyObservers(state);
        }
    }
}
