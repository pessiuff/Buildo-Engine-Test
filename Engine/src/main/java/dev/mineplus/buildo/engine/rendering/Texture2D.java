package dev.mineplus.buildo.engine.rendering;

import org.lwjgl.stb.STBImage;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

public class Texture2D {
    private int textureHandle;

    private Texture2D(final int textureHandle) {
        this.textureHandle = textureHandle;
    }

    public static Texture2D create(final int imageWidth, final int imageHeight, final ByteBuffer imageData) {
        int textureHandle;

        textureHandle = glGenTextures();
        if (textureHandle == 0) {
            System.out.println("There was a problem while creating a 2d texture.");
            return null;
        }

        glBindTexture(GL_TEXTURE_2D, textureHandle);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, imageWidth, imageHeight, 0, GL_RGB, GL_UNSIGNED_BYTE, imageData);
        glGenerateMipmap(GL_TEXTURE_2D);

        STBImage.stbi_image_free(imageData);
        glBindTexture(GL_TEXTURE_2D, 0);

        return new Texture2D(textureHandle);
    }

    public void bind() {
        if (textureHandle == 0) {
            System.out.println("Tried using a deleted 2d texture.");
            return;
        }
        glBindTexture(GL_TEXTURE_2D, textureHandle);
    }

    public void destroy() {
        if (textureHandle == 0) {
            System.out.println("Tried using a deleted 2d texture.");
            return;
        }

        glDeleteTextures(textureHandle);
        textureHandle = 0;
    }
}
