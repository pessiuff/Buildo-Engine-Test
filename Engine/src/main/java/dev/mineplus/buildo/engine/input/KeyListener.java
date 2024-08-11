package dev.mineplus.buildo.engine.input;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyListener {
    private static KeyListener instance;

    private final boolean[] keysPressed;
    private final boolean[] keysReleased;

    private KeyListener() {
        keysPressed = new boolean[350];
        keysReleased = new boolean[350];
    }

    public static KeyListener get() {
        if (instance == null)
            instance = new KeyListener();

        return instance;
    }

    public static void keyCallback(final long windowHandle, final int key, final int scanCode, final int action, final int modifiers) {
        if (key + 1 > get().keysPressed.length) {
            System.out.println("Tried to use unhandled key: " + key);
            return;
        }

        if (action == GLFW_PRESS) {
            get().keysPressed[key] = true;
            get().keysReleased[key] = false;
        }
        else if (action == GLFW_RELEASE) {
            get().keysPressed[key] = false;
            get().keysReleased[key] = true;
        }
    }

    public static boolean isKeyDown(final int key) {
        if (key + 1 > get().keysPressed.length) {
            System.out.println("Tried using isKeyDown() for unhandled key: " + key);
            return false;
        }

        return get().keysPressed[key];
    }

    public static boolean isKeyUp(final int key) {
        if (key + 1 > get().keysReleased.length) {
            System.out.println("Tried using isKeyUp() for unhandled key: " + key);
            return false;
        }

        final boolean result = get().keysReleased[key];
        get().keysReleased[key] = false;

        return result;
    }
}
