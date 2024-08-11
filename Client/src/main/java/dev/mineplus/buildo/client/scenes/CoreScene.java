package dev.mineplus.buildo.client.scenes;

import dev.mineplus.buildo.engine.input.KeyListener;
import dev.mineplus.buildo.engine.scene.Scene;
import dev.mineplus.buildo.engine.utils.GLUtils;
import dev.mineplus.buildo.engine.window.Window;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_F1;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_F2;

public class CoreScene extends Scene {
    @Override
    public void update(double deltaTime) {
        GLUtils.clearWindow(1.f, 1.f, 1.f, 1.f);

        if (KeyListener.isKeyUp(GLFW_KEY_F1)) {
            Window.get().getSceneManager().setCurrentScene(new LevelEditorScene());
        } else if (KeyListener.isKeyUp(GLFW_KEY_F2)) {
            Window.get().getSceneManager().setCurrentScene(new LevelScene());
        }
    }
}
