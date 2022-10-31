package com.JagaEngine.geometry;

import com.JagaEngine.*;
import com.JagaEngine.data.VertexArray;
import com.JagaEngine.fragments.ColorShaderProgram;

import static android.opengl.GLES20.GL_TRIANGLE_FAN;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.Matrix.multiplyMM;
import static com.JagaEngine.util.Constants.*;

public class Quad extends Primitive

{
    protected final int POSITION_COMPONENT_COUNT = 3;

    protected float[] vertexData =
            {
                 0.0f,  0.0f, 0.0f, 0.5f, 0.5f,
                -0.5f, -0.5f, 0.0f, 0.0f, 0.1f,
                 0.5f, -0.5f, 0.0f, 1.0f, 0.1f,
                 0.5f,  0.5f, 0.0f, 1.0f, 0.9f,
                -0.5f,  0.5f, 0.0f, 0.0f, 0.9f,
                -0.5f, -0.5f, 0.0f, 0.0f, 0.1f
            };

    /**
     * Utilizing the vector 3 object to organize the Red Green Blue values of the
     * color of the quad.
     */
    protected Vector color;

    public Vector getColor() { return color; }
    public void setColor(Vector newColor) { color = new Vector(newColor); }

    public Quad()
    {
        vertexArrayData = new VertexArray(vertexData);

        color = new Vector(1.0f, 0.0f, 0.0f);
    }

    public void setColor(float red, float green, float blue)
    {
        color.setX(red);
        color.setY(green);
        color.setZ(blue);
    }

    @Override
    public void Render(JagaCamera camera)
    {
        if (null != camera)
        {
            // position the quad
            float[] modelViewProjectionMatrix = new float[16];
            multiplyMM(modelViewProjectionMatrix, 0, camera.getViewProjectionMatrix(), 0, this.world, 0);

            if (null != shader)
            {
                if (shader instanceof ColorShaderProgram)
                {
                    ColorShaderProgram colorShader = (ColorShaderProgram) shader;
                    colorShader.useProgram();
                    colorShader.setUniforms(modelViewProjectionMatrix, color.x, color.y, color.z);

                    vertexArrayData.setVertexAttribPointer(
                            0,
                            colorShader.getPositionAttributeLocation(),
                            POSITION_COMPONENT_COUNT,
                            20);

                }
            }

            glDrawArrays(GL_TRIANGLE_FAN, 0, 6);
        }
    }
}
