package dev.mineplus.buildo.engine.utils;

import static org.lwjgl.opengl.GL11.*;

public class GLUtils {
    public static void clearWindow(final float red, final float green, final float blue, final float alpha) {
        glClearColor(red, green, blue, alpha);
        glClear(GL_COLOR_BUFFER_BIT);
    }
}
