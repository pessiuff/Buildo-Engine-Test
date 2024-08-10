package dev.mineplus.buildo.engine.scene;

import static org.lwjgl.opengl.GL11.*;

public class EmptyScene extends Scene {
    @Override
    public void update(final double deltaTime) {
        glClearColor(1.f, 1.f, 1.f, 1.f);
        glClear(GL_COLOR_BUFFER_BIT);
    }
}
