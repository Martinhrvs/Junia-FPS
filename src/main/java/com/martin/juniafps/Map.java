package com.martin.juniafps;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Map {
    private final int[][] maze;
    private final int width;
    private final int height;

    public Map(final String imageName) {
        final URL url = Map.class.getClassLoader().getResource(imageName);
        final BufferedImage image;
        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.height = image.getHeight();
        this.width = image.getWidth();
        this.maze = new int[height][width];

        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                switch (image.getRGB(col, row)) {
                    case RenderUtils.BLACK_COLOR_IMAGE -> this.maze[row][col] = 1;
                    default -> this.maze[row][col] = 0;
                }

            }
        }
    }

    public int get(final int x, final int y) {
        return this.maze[y][x];
    }
}