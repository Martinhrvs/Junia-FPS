package com.martin.juniafps;

public class Map {

    private final int[][] maze;
    private final int height;
    private final int width;

    public Map(int height, int width) {
        this.height = height;
        this.width = width;
        this.maze = new int[height][width];

        for (int row = 0; row < this.height; row++) {
            for (int column = 0; column < this.width; column++) {
                this.maze[row][column] = 0;
            }
        }
    }
}
