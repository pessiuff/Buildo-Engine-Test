package dev.mineplus.buildo.client;

import dev.mineplus.buildo.client.scenes.CoreScene;
import dev.mineplus.buildo.engine.window.Window;
import dev.mineplus.buildo.engine.window.WindowProperties;

public class BuildoClient {
    public static void main(final String[] args) {
        final Window window = Window.get();

        window.setWindowProperties(
            new WindowProperties(1920, 1080, "Buildo [Client]")
        );

        window.getSceneManager().setCurrentScene(new CoreScene());

        window.run();
    }
}