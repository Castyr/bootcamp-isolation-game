package org.bootcamp.isolationgame.core.services;

import java.util.Observer;

import org.bootcamp.isolationgame.core.models.GameState;
import org.bootcamp.isolationgame.core.models.Move;
import org.bootcamp.isolationgame.core.strategies.RuleStrategy;

public interface GameManager {
    void reset();

    void update(Move move);

    GameState getState();

    void addObserver(Observer observer);

    void setRuleStrategy(RuleStrategy ruleStrategy);

    RuleStrategy getRuleStrategy();

}
