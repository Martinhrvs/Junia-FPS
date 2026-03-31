package com.martin;

import com.martin.juniafps.Map;
import com.martin.juniafps.Renderer;

import java.awt.*;


public class Main {
    public static void main(String[] args) {
        Map map = new Map("map.bmp");
        Renderer renderer = new Renderer();
        System.out.println(renderer.render(new Point(582, 233), 45, map));


    }
}