package dev.mineplus.buildo.engine.scene;

public interface Scene {
    void initialize();

    void update(final double deltaTime);

    void cleanUp();
}
