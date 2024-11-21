package dev.mineplus.buildo.engine.rendering;

import java.util.HashMap;

import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {
    private int programHandle;
    private final HashMap<Integer, Integer> shaders;

    private ShaderProgram(final int programHandle) {
        this.programHandle = programHandle;
        shaders = new HashMap<>();
    }

    public static ShaderProgram create() {
        int programHandle = glCreateProgram();

        if (programHandle == 0) {
            System.out.println("There was a problem while creating a shader program.");
            return null;
        }

        return new ShaderProgram(programHandle);
    }

    public void addShader(final int type, final String source) {
        if (programHandle == 0) {
            System.out.println("Tried using a deleted shader program.");
            return;
        }

        final int shaderHandle = glCreateShader(type);
        glShaderSource(shaderHandle, source);

        glCompileShader(shaderHandle);
        final int compileStatus = glGetShaderi(shaderHandle, GL_COMPILE_STATUS);
        if (compileStatus == GL_FALSE) {
            System.out.println("There was a problem while compiling a shader of type " + type + ":");
            System.out.print(glGetShaderInfoLog(shaderHandle));
            return;
        }

        shaders.put(shaderHandle, type);
        System.out.println("Compiled shader of type " + type + ".");
    }

    public void linkProgram() {
        if (programHandle == 0) {
            System.out.println("Tried using a deleted shader program.");
            return;
        }

        for (final int shaderHandle : shaders.keySet()) {
            glAttachShader(programHandle, shaderHandle);
        }
        glLinkProgram(programHandle);
        final int linkStatus = glGetProgrami(programHandle, GL_LINK_STATUS);
        if (linkStatus == GL_FALSE) {
            System.out.println("There was a problem while linking a program:");
            System.out.println(glGetProgramInfoLog(programHandle));
            return;
        }

        for (final int shaderHandle : shaders.keySet()) {
            glDetachShader(programHandle, shaderHandle);
        }

        System.out.println("Linked shader program.");
    }

    public void useProgram() {
        if (programHandle == 0) {
            System.out.println("Tried using a deleted shader program.");
            return;
        }

        glUseProgram(programHandle);
    }

    public void destroy() {
        if (programHandle == 0) {
            System.out.println("Tried using a deleted shader program.");
            return;
        }

        for (final int shaderHandle : shaders.keySet()) {
            glDeleteShader(shaderHandle);
            shaders.remove(shaderHandle);
        }

        glDeleteProgram(programHandle);
        programHandle = 0;
    }
}
