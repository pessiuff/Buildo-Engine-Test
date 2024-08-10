package dev.mineplus.buildo.client;

import dev.mineplus.buildo.engine.Window;
import dev.mineplus.buildo.engine.WindowProperties;

public class BuildoClient {
    public static void main(final String[] args) {
        final Window window = Window.get();
        window.setWindowProperties(
            new WindowProperties(1920, 1080, "Buildo [Client]")
        );

        window.run();
    }
}