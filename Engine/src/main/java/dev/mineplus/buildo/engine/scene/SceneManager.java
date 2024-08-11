package dev.mineplus.buildo.engine.scene;

import lombok.NonNull;

public class SceneManager {
    private Scene currentScene;

    public SceneManager() {
        currentScene = new EmptyScene();
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(@NonNull final Scene scene) {
        currentScene = scene;

        System.out.println("Loading scene: " + scene.getClass().getSimpleName());
        scene.initialize();
        System.out.println("Loaded scene: " + scene.getClass().getSimpleName());
    }

    public void clearCurrentScene() {
        currentScene = new EmptyScene();
    }
}
