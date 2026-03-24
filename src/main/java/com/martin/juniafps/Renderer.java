package com.martin.juniafps;

import java.awt.*;

public class Renderer {
    final Screen screen = new Screen(RenderUtils.SCREEN_HEIGHT,
            RenderUtils.SCREEN_WIDTH);

    public String render(final Point origin, int direction, final Map map) {

        final double[] heightsPerceived = this.getHeightsPerceived(origin, direction, map);
        int numColumn = 0;
        for (final double heightPerceived : heightsPerceived) {
            this.renderColumn(numColumn++, heightPerceived);
        }
        return screen.toStr();
    }

    private void renderColumn(final int numColumn, final double heightPerceived) {

        this.screen.verticalLine(numColumn,
                0, RenderUtils.EYE_HEIGHT,
                RenderUtils.GROUND_PIXEL);
        this.screen.verticalLine(numColumn,
                0, RenderUtils.EYE_HEIGHT,
                RenderUtils.SKY_PIXEL);
    }

    private double[] getHeightsPerceived(final Point origin, int direction, Map map) {
        return new double[RenderUtils.SCREEN_WIDTH];
    }

}
