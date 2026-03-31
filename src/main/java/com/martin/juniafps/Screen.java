package com.martin.juniafps;

public class Screen {
    private final int height;
    private final int width;
    private final char[][] pixels;

    public Screen(int height, int width) {
        this.height = height;
        this.width = width;
        this.pixels = new char[this.height][this.width];
    }

    public String toStr() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                stringBuilder.append(this.pixels[row][col]);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public void verticalLine(final int numCol, final int rowStart, final int rowEnd, final char pixel) {
        for (int row = rowStart; row < rowEnd; row++) {
            this.plot(this.height - row - 1, numCol, pixel);
        }
    }

    private void plot(final int row, final int column, final char pixel) {
        if (!this.isOutBounds(column, row)) {
            this.pixels[row][column] = pixel;
        }
    }

    private boolean isOutBounds(final int column, final int row) {
        return (column < 0) || (row < 0) || (column >= this.width) || (row >= this.height);
    }
}