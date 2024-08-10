package dev.mineplus.buildo.engine.utils;

public class TimeUtils {
    private static final float startTime = System.nanoTime();

    public static double getTimeSinceStart() {
        return (System.nanoTime() - startTime) * 1E-9;
    }
}
