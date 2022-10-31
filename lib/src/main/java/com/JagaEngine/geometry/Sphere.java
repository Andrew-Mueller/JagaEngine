package com.JagaEngine.geometry;

import com.JagaEngine.JagaCamera;
import com.JagaEngine.data.VertexArray;
import com.JagaEngine.fragments.ColorShaderProgram;

import static android.opengl.GLES10.GL_LINE_LOOP;
import static android.opengl.GLES20.GL_TRIANGLE_FAN;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.Matrix.multiplyMM;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Sphere extends Primitive
{
    protected final int POSITION_COMPONENT_COUNT = 3;

    public final Point center;
    public final float radius;

    protected float [] vertexData;

    /**
     * Utilizing the vector 3 object to organize the Red Green Blue values of the
     * color of the quad.
     */
    protected Vector color;

    public Sphere(Point center, float radius)
    {
        this.center = center;
        this.radius = radius;

        int vertexCount = 30;

        // Create a buffer for vertex data
        float buffer[] = new float[vertexCount * 2]; // (x,y) for each vertex

        int idx = 0;

        // Center vertex for triangle fan
        buffer[idx++] = this.center.x;
        buffer[idx++] = this.center.y;

        // Outer vertices of the circle
        int outerVertexCount = vertexCount - 1;

        for (int i = 0; i < outerVertexCount; ++i)
        {
            float percent = (i / (float) (outerVertexCount-1));
            float rad = (float)(percent * 2 * Math.PI);

            //Vertex position
            float outer_x = (float)(this.center.x + radius * cos(rad));
            float outer_y = (float)(this.center.y + radius * sin(rad));

            buffer[idx++] = outer_x;
            buffer[idx++] = outer_y;
        }

        //Create VBO from buffer with glBufferData()
        vertexArrayData = new VertexArray(buffer);

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

            glDrawArrays(GL_LINE_LOOP, 0, 6);
        }
    }
}
