package com.JagaEngine.fragments;

import static android.opengl.GLES20.*;
import com.JagaEngine.R;

import android.content.Context;

public class ColorShaderProgram extends ShaderProgram
{
    // Uniform locations
    private final int uMatrixLocation;
    public static final int matrixUniformIndex = 0;

    // Attribute locations
    private final int aPositionLocation;
    public static final int positionAttributeIndex = 1;

    private final int aColorLocation;
    public static final int colorAttributeIndex = 2;

    private final int uColorLocation;
    public static final int colorUniformIndex = 3;

    public static final int COLOR_SHADER_TYPE = 1;

    public ColorShaderProgram(Context context)
    {
        super(context, R.raw.simple_vertex_shader, R.raw.simple_fragment_shader);

        // Retrieve uniform locations for the shader program.
        uMatrixLocation = glGetUniformLocation(program, U_MATRIX);

        // Retrieve attribute locations for the shader program.
        aPositionLocation = glGetAttribLocation(program, A_POSITION);
        aColorLocation = glGetAttribLocation(program, A_COLOR);

        uColorLocation = glGetUniformLocation(program, U_COLOR);

        this.shaderType = COLOR_SHADER_TYPE;
    }

    public void setUniforms(float[] matrix, float r, float g, float b)
    {
        // pass the matrix into the shader program.
        glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);
        glUniform4f(uColorLocation, r, g, b, 1f);
    }

    public int getPositionAttributeLocation()
    {
        return aPositionLocation;
    }

    public int getColorAttributeLocation()
    {
        return aColorLocation;
    }
}
