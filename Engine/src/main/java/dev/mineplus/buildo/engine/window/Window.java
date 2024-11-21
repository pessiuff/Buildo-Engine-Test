package dev.mineplus.buildo.engine.window;

import dev.mineplus.buildo.engine.input.KeyListener;
import dev.mineplus.buildo.engine.input.MouseListener;
import dev.mineplus.buildo.engine.scene.SceneManager;
import dev.mineplus.buildo.engine.utils.TimeUtils;
import lombok.Getter;
import lombok.Setter;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glGetString;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    private static Window instance = null;

    @Getter @Setter
    private WindowProperties windowProperties;

    @Getter
    private final SceneManager sceneManager;

    private long windowHandle;

    private Window() {
        windowProperties = new WindowProperties(1920, 1080, "Unnamed Window");
        sceneManager = new SceneManager();

        initialize();
    }

    public static Window get() {
        if (instance == null)
            instance = new Window();

        return instance;
    }

    public void run() {
        // Application loop
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(windowHandle);
        glfwDestroyWindow(windowHandle);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        Objects.requireNonNull(
            glfwSetErrorCallback(null)
        ).free();
    }

    private void initialize() {
        // Print LWJGL version
        System.out.println("Using LWJGL version: " + Version.getVersion());

        // Setup error callback
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW
        if (!glfwInit())
            throw new IllegalStateException("There was a problem while initializing GLFW.");

        // Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        // Create window
        windowHandle = glfwCreateWindow(
            windowProperties.getWidth(),
            windowProperties.getHeight(),
            windowProperties.getTitle(),
            NULL,
            NULL
        );

        if (windowHandle == NULL)
            throw new IllegalStateException("There was a problem while creating the window.");

        // Mouse callbacks
        glfwSetCursorPosCallback(windowHandle, MouseListener::cursorPositionCallback);
        glfwSetMouseButtonCallback(windowHandle, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(windowHandle, MouseListener::mouseScrollCallback);

        // Keyboard callbacks
        glfwSetKeyCallback(windowHandle, KeyListener::keyCallback);

        // Finalizing the initialiation
        glfwMakeContextCurrent(windowHandle);
        glfwSwapInterval(1);
        glfwShowWindow(windowHandle);

        // Create capabilites to use OpenGL functions
        GL.createCapabilities();

        System.out.println("Using OpenGL version: " + glGetString(GL_VERSION));
    }

    private void loop() {
        double frameStartTime;
        double deltaTime = 0.f;

        while (!glfwWindowShouldClose(windowHandle)) {
            // Set frame start time
            frameStartTime = TimeUtils.getTime();

            // Poll events
            glfwPollEvents();

            // Draw scene
            if (deltaTime >= 0)
                sceneManager.getCurrentScene().update(deltaTime);

            // Swap buffers (aka. swap last frame with the current one we're drawing)
            glfwSwapBuffers(windowHandle);

            // Set delta time
            deltaTime = TimeUtils.getTime() - frameStartTime;
        }

        sceneManager.getCurrentScene().cleanUp();
    }
}
