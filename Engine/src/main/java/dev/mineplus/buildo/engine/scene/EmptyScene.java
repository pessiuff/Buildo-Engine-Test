package dev.mineplus.buildo.engine.scene;

import static org.lwjgl.opengl.GL11.*;

public class EmptyScene implements Scene {
    @Override
    public void initialize() {

    }

    @Override
    public void update(final double deltaTime) {
        glClearColor(1.f, 1.f, 1.f, 1.f);
        glClear(GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void cleanUp() {

    }
}
