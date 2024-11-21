package dev.mineplus.buildo.client.scenes;

import dev.mineplus.buildo.client.BuildoClient;
import dev.mineplus.buildo.engine.rendering.ShaderProgram;
import dev.mineplus.buildo.engine.rendering.Texture2D;
import dev.mineplus.buildo.engine.scene.Scene;
import dev.mineplus.buildo.engine.utils.GLUtils;
import io.herrera.kevin.resource.Resource;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

public class CoreScene implements Scene {
    Resource resource;
    Texture2D texture;
    ShaderProgram shaderProgram;

    @Override
    public void initialize() {
        resource = new Resource(BuildoClient.class.getClassLoader());

        try (final MemoryStack stack = MemoryStack.stackPush()) {
            final IntBuffer imageWidth = stack.mallocInt(1);
            final IntBuffer imageHeight = stack.mallocInt(1);
            final IntBuffer imageChannels = stack.mallocInt(1);
            final File file = resource.getAsPath("assets/textures/test.jpeg").toFile();
            final String filePath = file.getAbsolutePath();

            final ByteBuffer imageData = STBImage.stbi_load(filePath, imageWidth, imageHeight, imageChannels, 4);

            texture = Texture2D.create(imageWidth.get(), imageHeight.get(), imageData);
        }

        shaderProgram = ShaderProgram.create();
        if (shaderProgram == null) {
            return;
        }
        shaderProgram.addShader(GL_VERTEX_SHADER, resource.getAsString("assets/shaders/test.vert", "utf-8"));
        shaderProgram.addShader(GL_FRAGMENT_SHADER, resource.getAsString("assets/shaders/test.frag", "utf-8"));
        shaderProgram.linkProgram();
    }

    @Override
    public void update(double deltaTime) {
        GLUtils.clearWindow(1.f, 1.f, 1.f, 1.f);

        shaderProgram.useProgram();
    }

    @Override
    public void cleanUp() {
        shaderProgram.destroy();
    }
}
