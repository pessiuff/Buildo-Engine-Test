package dev.mineplus.buildo.engine.input;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {
    private static MouseListener instance = null;

    private double xPosition, yPosition;
    private double lastX, lastY;
    private final boolean[] mouseButtonsPressed;
    private double scrollX, scrollY;
    private boolean isDragging;

    private MouseListener() {
        scrollX = 0;
        scrollY = 0;

        xPosition = 0;
        yPosition = 0;

        lastX = 0;
        lastY = 0;

        mouseButtonsPressed = new boolean[5];

        isDragging = false;
    }

    public static MouseListener get() {
        if (instance == null)
            instance = new MouseListener();

        return instance;
    }

    public static void cursorPositionCallback(final long windowHandle, final double xPosition, final double yPosition) {
        get().lastX = get().xPosition;
        get().lastY = get().yPosition;

        get().xPosition = xPosition;
        get().yPosition = yPosition;

        int pressedButtonCount = 0;
        for (final boolean isButtonPressed : get().mouseButtonsPressed) {
            if (isButtonPressed) pressedButtonCount++;
        }
        get().isDragging = pressedButtonCount > 0;
    }

    public static void mouseButtonCallback(final long windowHandle, final int button, final int action, final int modifiers) {
        if (button + 1 > get().mouseButtonsPressed.length) {
            System.out.println("Tried to use unhandled mouse button: " + button);
            return;
        }

        if (action == GLFW_PRESS)
            get().mouseButtonsPressed[button] = true;
        else if (action == GLFW_RELEASE)
            get().mouseButtonsPressed[button] = false;
    }

    public static void mouseScrollCallback(final long windowHandle, final double xOffset, final double yOffset) {
        get().scrollX = xOffset;
        get().scrollY = yOffset;
    }

    public static void endFrame() {
        get().lastX = get().xPosition;
        get().lastY = get().yPosition;

        get().scrollX = 0;
        get().scrollY = 0;
    }

    public static double getXPosition() {
        return get().xPosition;
    }

    public static double getYPosition() {
        return get().yPosition;
    }

    public static double getDeltaX() {
        return get().lastX - get().xPosition;
    }

    public static double getDeltaY() {
        return get().lastY - get().yPosition;
    }

    public static double getScrollX() {
        return get().scrollX;
    }

    public static double getScrollY() {
        return get().scrollY;
    }

    public static boolean isMouseButtonDown(final int button) {
        if (button + 1 > get().mouseButtonsPressed.length) {
            System.out.println("Tried using isMouseButtonDown() for unhandled mouse button: " + button);
            return false;
        }

        return get().mouseButtonsPressed[button];
    }

    public static boolean isDragging() {
        return get().isDragging;
    }
}
