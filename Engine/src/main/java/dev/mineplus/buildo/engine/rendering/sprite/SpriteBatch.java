package dev.mineplus.buildo.engine.rendering.sprite;

import dev.mineplus.buildo.engine.rendering.BatchRenderer;

import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class SpriteBatch implements BatchRenderer {
    private int vboHandle;
    private int vaoHandle;

    private SpriteBatch(final int vboHandle, final int vaoHandle) {
        this.vboHandle = vboHandle;
        this.vaoHandle = vaoHandle;
    }

    public static SpriteBatch create() {
        final int vboHandle = glGenBuffers();
        final int vaoHandle = glGenVertexArrays();

        if (vboHandle == 0) {
            System.out.println("There was a problem while creating buffer object for a sprite batch renderer.");
            return null;
        }

        if (vaoHandle == 0) {
            System.out.println("There was a problem while creating array object for a sprite batch renderer.");
            return null;
        }

        return new SpriteBatch(vboHandle, vaoHandle);
    }

    public void add(final Sprite2D sprite) {

    }

    @Override
    public void render() {

    }

    public void destroy() {
        if (vboHandle == 0 || vaoHandle == 0) {
            System.out.println("Tried using a deleted sprite batch renderer.");
            return;
        }

        glDeleteBuffers(vboHandle);
        glDeleteVertexArrays(vaoHandle);
        vboHandle = 0;
        vaoHandle = 0;
    }
}
