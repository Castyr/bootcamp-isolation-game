package org.bootcamp.isolationgame.core.models;

public class Move {
    private final int x;
    private final int y;
    private final int player;

    public Move(int x, int y, int player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPlayer() {
        return player;
    }
}
