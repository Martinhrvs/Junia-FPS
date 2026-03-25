package com.martin.juniafps;

import java.awt.*;


public class Renderer {
    private final Screen screen = new Screen(RenderUtils.SCREEN_HEIGHT, RenderUtils.SCREEN_WIDTH);

    public String render(final Point origin, final int direction, final Map map) {

        final double[] heightsPerceived = this.getHeightsPerceived(origin, direction, map);
        int numColumns = 0;
        for (final double heightPerceived : heightsPerceived) {
            this.renderColumn(numColumns++, heightPerceived);
        }
        return this.screen.toStr();
    }


    private void renderColumn(final int numCol, final double heightPerceived) {
        this.screen.verticalLine(numCol, 0, (int) (RenderUtils.EYE_HEIGHT - heightPerceived / 2), RenderUtils.GROUND_PIXEL);
        this.screen.verticalLine(numCol, (int) (RenderUtils.EYE_HEIGHT + heightPerceived / 2), RenderUtils.SCREEN_HEIGHT, RenderUtils.SKY_PIXEL);
        this.screen.verticalLine(numCol, (int) (RenderUtils.EYE_HEIGHT - heightPerceived / 2), (int) (RenderUtils.EYE_HEIGHT + heightPerceived / 2), RenderUtils.WALL_PIXEL);
    }

    private double[] getHeightsPerceived(final Point origin, final int direction, final Map map) {
        final double[] heightsPerceived = new double[RenderUtils.SCREEN_WIDTH];
        double angle = direction - RenderUtils.VIEWING_ANGLE / 2;
        double angleStep = RenderUtils.VIEWING_ANGLE / RenderUtils.SCREEN_WIDTH;

        for (int col = 0; col < RenderUtils.SCREEN_WIDTH; col++, angle += angleStep) {
            heightsPerceived[col] = this.getHeightPerceived(origin, angle, map);
        }
        return heightsPerceived;

    }

    private double getHeightPerceived(final Point origin, final double angle, final Map map) {
        final double radianAngle = RenderUtils.degreesToRadians(angle);
        final Point end = new Point((int) (origin.x - RenderUtils.VIEWING_DISTANCE * Math.cos(radianAngle)), (int) (origin.y - RenderUtils.VIEWING_DISTANCE * Math.sin(radianAngle)));
        final Point obstacle = this.getFromTo(map, origin, end);
        if (obstacle != null) {
            final double realDistance = Math.sqrt(Math.pow(origin.x - obstacle.x, 2) + Math.pow(origin.y - obstacle.y, 2));
            return RenderUtils.SCREEN_DISTANCE * RenderUtils.WALL_HEIGHT / realDistance;
        }
        return 0.0;
    }

    private Point getFromTo(Map map, Point origin, Point end) {
        if (map.get(origin.x, origin.y) != 0) return null;

        final int deltaX = Math.abs(end.x - origin.x);
        final int deltaY = Math.abs(end.y - origin.y);
        final int stepX = Integer.compare(end.x, origin.x);
        final int stepY = Integer.compare(end.y, origin.y);
        int error = deltaX - deltaY;
        int x = origin.x;
        int y = origin.y;
        while (x != end.x || y != end.y) {
            if (map.get(x, y) != 0) {
                return new Point(x, y);
            }
            final int error2 = error * 2;
            if (error2 > -deltaY) {
                error -= deltaY;
                x += stepX;
            }
            if (error2 < deltaX) {
                error += deltaX;
                y += stepY;
            }
        }
        return null;
    }
}