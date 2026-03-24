package com.martin;

import com.martin.juniafps.Map;
import com.martin.juniafps.Renderer;

public class Main {
    public static void main(String[] args) throws Exception {

        Map map = new Map(10, 10);
        Renderer renderer = new Renderer();

        System.out.println(renderer.render(null, 0, map));
    }
}