package dev.mineplus.buildo.engine.utils;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class TimeUtils {
    public static double getTime() {
        return glfwGetTime();
    }
}
