package com.JagaEngine;

import com.JagaEngine.geometry.Vector;

import org.junit.Test;
import static org.junit.Assert.*;

public class JagaCamera_Test
{
    public class JagaCamera_unitTest extends JagaCamera
    {
        public int getWidth() { return this.width; }
        public int getHeight() { return this.height; }

        public float[] getView() { return this.viewMatrix; }
        public float[] getProjection() { return this.projectionMatrix; }
        public float[] getViewProjection() { return this.viewProjectionMatrix; }

        public Vector getEyePosition() { return eyePosition; }
        public Vector getLookAtPosition() { return lookAtPosition; }
    }

    //@Test
    public void ctor() throws Exception
    {
        JagaCamera_unitTest camera = new JagaCamera_unitTest();

        // verify the matrices are zeroed
        float[] view = camera.getView();
        for (int i = 0; i < 16; i++)
        {
            assertEquals(0, view[i], 0.0001f);
        }

        float[] projection = camera.getProjection();
        for (int i = 0; i < 16; i++)
        {
            assertEquals(0, projection[i], 0.0001f);
        }


        float[] viewProjection = camera.getViewProjection();
        for (int i = 0; i < 16; i++)
        {
            assertEquals(0, viewProjection[i], 0.0001f);
        }

        Vector eyePos = camera.getEyePosition();
        assertEquals(0.0f, eyePos.getX(), 0.0001f);
        assertEquals(-1.2f, eyePos.getY(), 0.0001f);
        assertEquals(2.2f, eyePos.getZ(), 0.0001f);

        Vector lookAtPos = camera.getLookAtPosition();
        assertEquals(0.0f, lookAtPos.getX(), 0.0001f);
        assertEquals(0.0f, lookAtPos.getY(), 0.0001f);
        assertEquals(0.0f, lookAtPos.getZ(), 0.0001f);

    }

    //@Test
    public void setup() throws Exception
    {
        JagaCamera_unitTest camera = new JagaCamera_unitTest();

        camera.setup(1440, 2308);

        // TODO: verify the matrices
        float[] view = camera.getView();
        assertEquals(0.999f,       view[0],  0.0001f);
        assertEquals(0.0f,         view[1],  0.0001f);
        assertEquals(0.0f,         view[2],  0.0001f);
        assertEquals(0.0f,         view[3],  0.0001f);
        assertEquals(0.0f,         view[4],  0.0001f);
        assertEquals(0.87789553f,  view[5],  0.0001f);
        assertEquals(-0.47885215f, view[6],  0.0001f);
        assertEquals(0.0f,         view[7],  0.0001f);
        assertEquals(0.0f,         view[8],  0.0001f);
        assertEquals(0.47885215f,  view[9],  0.0001f);
        assertEquals(0.8778956f,   view[10], 0.0001f);
        assertEquals(0.0f,         view[11], 0.0001f);
        assertEquals(0.0f,         view[12], 0.0001f);
        assertEquals(0.0f,         view[13], 0.0001f);
        assertEquals(-2.505993f,   view[14], 0.0001f);
        assertEquals(1.0f,         view[15], 0.0001f);


        float[] projection = camera.getProjection();
        assertEquals(3.8694477f, projection[0],  0.0001f);
        assertEquals(0.0f,       projection[1],  0.0001f);
        assertEquals(0.0f,       projection[2],  0.0001f);
        assertEquals(0.0f,       projection[3],  0.0001f);
        assertEquals(0.0f,       projection[4],  0.0001f);
        assertEquals(2.4142134f, projection[5],  0.0001f);
        assertEquals(0.0f,       projection[6],  0.0001f);
        assertEquals(0.0f,       projection[7],  0.0001f);
        assertEquals(0.0f,       projection[8],  0.0001f);
        assertEquals(0.0f,       projection[9],  0.0001f);
        assertEquals(-1.222f,    projection[10], 0.0001f);
        assertEquals(-1.0f,      projection[11], 0.0001f);
        assertEquals(0.0f,       projection[12], 0.0001f);
        assertEquals(0.0f,       projection[13], 0.0001f);
        assertEquals(-2.222f,    projection[14], 0.0001f);
        assertEquals(0.0f,       projection[15], 0.0001f);


        float[] viewProjection = camera.getViewProjection();
        assertEquals(3.8694475f,  viewProjection[0],  0.0001f);
        assertEquals(0.0f,        viewProjection[1],  0.0001f);
        assertEquals(0.0f,        viewProjection[2],  0.0001f);
        assertEquals(0.0f,        viewProjection[3],  0.0001f);
        assertEquals(0.0f,        viewProjection[4],  0.0001f);
        assertEquals(2.1194272f,  viewProjection[5],  0.0001f);
        assertEquals(0.5852637f,  viewProjection[6],  0.0001f);
        assertEquals(0.47885215f, viewProjection[7],  0.0001f);
        assertEquals(0.0f,        viewProjection[8],  0.0001f);
        assertEquals(1.1560513f,  viewProjection[9],  0.0001f);
        assertEquals(-1.0729835f, viewProjection[10], 0.0001f);
        assertEquals(-0.8778956f, viewProjection[11], 0.0001f);
        assertEquals(0.0f,        viewProjection[12], 0.0001f);
        assertEquals(0.0f,        viewProjection[13], 0.0001f);
        assertEquals(0.84065795f, viewProjection[14], 0.0001f);
        assertEquals(2.505993f,   viewProjection[15], 0.0001f);
    }
}
