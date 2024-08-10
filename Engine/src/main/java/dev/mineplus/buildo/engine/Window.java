package dev.mineplus.buildo.engine;

import lombok.Getter;
import lombok.Setter;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    private static Window instance = null;

    @Getter @Setter
    private WindowProperties windowProperties;

    private long windowHandle;

    private Window() {
        windowProperties = new WindowProperties(1920, 1080, "Unnamed Window");
    }

    public static Window get() {
        if (instance == null) {
            instance = new Window();
        }

        return instance;
    }

    public void run() {
        System.out.println("Using LWJGL version: " + Version.getVersion());

        initialize();
        loop();
    }

    private void initialize() {
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

        // Finalizing the initialiation
        glfwMakeContextCurrent(windowHandle);
        glfwSwapInterval(1);
        glfwShowWindow(windowHandle);

        GL.createCapabilities();
    }

    private void loop() {
        while (!glfwWindowShouldClose(windowHandle)) {
            // Poll events
            glfwPollEvents();

            // Clear window screen
            glClearColor(1.f, 1.f, 1.f, 1.f);
            glClear(GL_COLOR_BUFFER_BIT);

            // Swap buffers (aka. swap last frame with the current one we're drawing)
            glfwSwapBuffers(windowHandle);
        }
    }
}
