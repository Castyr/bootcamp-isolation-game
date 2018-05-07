package org.bootcamp.isolationgame.core.utils;


public class Config {
    private static final int TILE_SIZE = Integer.valueOf(Props.getInstance().getProperty("tile_size"));
    private static final int W = Integer.valueOf(Props.getInstance().getProperty("width"));
    private static final int H = Integer.valueOf(Props.getInstance().getProperty("height"));

    private static final int X_TILES = W / TILE_SIZE;
    private static final int Y_TILES = H / TILE_SIZE;

    public static int getTileSize() {
        return TILE_SIZE;
    }

    public static int getW() {
        return W;
    }

    public static int getH() {
        return H;
    }

    public static int getxTiles() {
        return X_TILES;
    }

    public static int getyTiles() {
        return Y_TILES;
    }
}
