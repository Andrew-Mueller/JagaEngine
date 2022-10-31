package com.JagaEngine.geometry;

import com.JagaEngine.JagaCamera;

import org.junit.Test;
import static org.junit.Assert.*;

public class Primitive_Test
{
    public class TestPri extends Primitive
    {
        public void Render(JagaCamera camera)
        {

        }
    }

    @Test
    public void positionTest()
    {
        TestPri pri = new TestPri();

        // TODO: opengl native library is not linked for junit
        pri.setX(3.14f);

        float x = pri.getX();


        assertEquals(3.14f, x, 0.1f);
    }
}
