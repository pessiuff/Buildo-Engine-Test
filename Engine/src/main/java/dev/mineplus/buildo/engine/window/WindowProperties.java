package dev.mineplus.buildo.engine.window;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class WindowProperties {
    private int width, height;
    private String title;

    public WindowProperties(final int width, final int height, final String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }
}
