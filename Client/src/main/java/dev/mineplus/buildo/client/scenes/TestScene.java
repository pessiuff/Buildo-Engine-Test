package dev.mineplus.buildo.client.scenes;

import dev.mineplus.buildo.engine.scene.Scene;
import dev.mineplus.buildo.engine.utils.GLUtils;

public class TestScene extends Scene {
    @Override
    public void update(double deltaTime) {
        GLUtils.clearWindow(1.f, 1.f, 1.f, 1.f);
        System.out.println(deltaTime);
    }
}
