package com.JagaEngine.animation;

import com.JagaEngine.geometry.Vector;
import com.JagaEngine.util.ITimer;
import com.JagaEngine.util.PropertyInvoker;
import com.JagaEngine.util.PrecisionTimer;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class VectorAnimation_Test
{
    public class TestObj
    {
        protected Vector position;

        public Vector getPosition() { return position; }
        public void setPosition(Vector pos) { position = new Vector(pos); }

        public TestObj() { position = new Vector(0.0f, 0.0f, 0.0f); }
    }

    public class TestTimer implements ITimer
    {

        long milliseconds;

        public TestTimer(long initialMs)
        {
            milliseconds = initialMs;
        }

        @Override
        public float getSeconds(float interval)
        {
            return 0;
        }

        @Override
        public long getMilliseconds()
        {
            return milliseconds;
        }

        @Override
        public long start()
        {
            return 0;
        }

        @Override
        public long stop()
        {
            return 0;
        }

        public void setMilliseconds(long value) {milliseconds = value;}
    }

    @Test
    public void ctor_Test() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        VectorAnimation_Test.TestObj tester = new VectorAnimation_Test.TestObj();

        VectorAnimation testAnimation = new VectorAnimation(new PropertyInvoker<Vector>("Position", tester, Vector.class), new Vector(42.0f, 0.0f, 0.0f), true, false, false, 5.0f);

        assertNotNull(testAnimation);
    }

    @Test
    public void singleStepAnimate_Test()
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InterruptedException, Exception
    {
        TestObj tester = new TestObj();
        TestTimer testTimer = new TestTimer(1);

        VectorAnimation testAnimation = new VectorAnimation(new PropertyInvoker<Vector>("Position", tester, Vector.class), new Vector(42.0f, 0.0f, 0.0f), true, false, false, 5.0f);
        testAnimation.setTimer(testTimer);

        testAnimation.animate();
        assertEquals(0.0f, tester.getPosition().getX(), 0.1f);
        assertEquals(0.0f, tester.getPosition().getY(), 0.1f);
        assertEquals(0.0f, tester.getPosition().getZ(), 0.1f);

        testTimer.setMilliseconds(100);
        testAnimation.animate();
        assertEquals(0.84f, tester.getPosition().getX(), 0.1f);
        assertEquals(0.0f, tester.getPosition().getY(), 0.1f);
        assertEquals(0.0f, tester.getPosition().getZ(), 0.1f);

        testTimer.setMilliseconds(200);
        testAnimation.animate();
        assertEquals(1.68f, tester.getPosition().getX(), 0.1f);
        assertEquals(0.0f, tester.getPosition().getY(), 0.1f);
        assertEquals(0.0f, tester.getPosition().getZ(), 0.1f);

        testTimer.setMilliseconds(300);
        testAnimation.animate();
        assertEquals(2.51f, tester.getPosition().getX(), 0.1f);
        assertEquals(0.0f, tester.getPosition().getY(), 0.1f);
        assertEquals(0.0f, tester.getPosition().getZ(), 0.1f);

        testTimer.setMilliseconds(400);
        testAnimation.animate();
        assertEquals(3.36f, tester.getPosition().getX(), 0.1f);
        assertEquals(0.0f, tester.getPosition().getY(), 0.1f);
        assertEquals(0.0f, tester.getPosition().getZ(), 0.1f);

        testTimer.setMilliseconds(500);
        testAnimation.animate();
        assertEquals(4.2f, tester.getPosition().getX(), 0.1f);
        assertEquals(0.0f, tester.getPosition().getY(), 0.1f);
        assertEquals(0.0f, tester.getPosition().getZ(), 0.1f);

        testTimer.setMilliseconds(600);
        testAnimation.animate();
        assertEquals(5.04f, tester.getPosition().getX(), 0.1f);
        assertEquals(0.0f, tester.getPosition().getY(), 0.1f);
        assertEquals(0.0f, tester.getPosition().getZ(), 0.1f);

        testTimer.setMilliseconds(700);
        testAnimation.animate();
        assertEquals(5.88f, tester.getPosition().getX(), 0.1f);
        assertEquals(0.0f, tester.getPosition().getY(), 0.1f);
        assertEquals(0.0f, tester.getPosition().getZ(), 0.1f);

        testTimer.setMilliseconds(800);
        testAnimation.animate();
        assertEquals(6.72f, tester.getPosition().getX(), 0.1f);
        assertEquals(0.0f, tester.getPosition().getY(), 0.1f);
        assertEquals(0.0f, tester.getPosition().getZ(), 0.1f);

        testTimer.setMilliseconds(900);
        testAnimation.animate();
        assertEquals(7.56f, tester.getPosition().getX(), 0.1f);
        assertEquals(0.0f, tester.getPosition().getY(), 0.1f);
        assertEquals(0.0f, tester.getPosition().getZ(), 0.1f);

        testTimer.setMilliseconds(1000);
        testAnimation.animate();
        assertEquals(8.4f, tester.getPosition().getX(), 0.1f);
        assertEquals(0.0f, tester.getPosition().getY(), 0.1f);
        assertEquals(0.0f, tester.getPosition().getZ(), 0.1f);

        // call again without updating the timer to ensure the position didn't change.
        testAnimation.animate();
        assertEquals(8.4f, tester.getPosition().getX(), 0.1f);
        assertEquals(0.0f, tester.getPosition().getY(), 0.1f);
        assertEquals(0.0f, tester.getPosition().getZ(), 0.1f);
    }
}
