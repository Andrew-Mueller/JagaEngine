package com.JagaEngine.animation;

import com.JagaEngine.util.ITimer;
import com.JagaEngine.util.PropertyInvoker;
import com.JagaEngine.util.PrecisionTimer;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;


public class DoubleAnimation_Test
{
    public class TestObj
    {
        protected Float position;

        public Float getPosition() { return position; }
        public void setPosition(Float pos) { position = pos; }

        public TestObj() { position = 0.0f; }
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
        TestObj tester = new TestObj();

        DoubleAnimation testAnimation = new DoubleAnimation(new PropertyInvoker<Float>("Position", tester, Float.class), 42.0f, 5.0f);

        assertNotNull(testAnimation);
    }

    @Test
    public void animate_Test()
            throws NoSuchMethodException,
                   IllegalAccessException,
                   InvocationTargetException,
                   InterruptedException,
                   Exception
    {
        TestObj tester = new TestObj();

        DoubleAnimation testAnimation = new DoubleAnimation(new PropertyInvoker<Float>("Position", tester, Float.class), 42.0f, 5.0f);
        testAnimation.setTimer(new PrecisionTimer());

        testAnimation.animate();
        assertEquals(0.0f, tester.getPosition(), 1.0f);

        Thread.sleep(5000);

        testAnimation.animate();
        assertEquals(42.0f, tester.getPosition(), 1.0f);
    }

    @Test
    public void partialAnimate_Test()
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InterruptedException, Exception
    {
        TestObj tester = new TestObj();

        DoubleAnimation testAnimation = new DoubleAnimation(new PropertyInvoker<Float>("Position", tester, Float.class), 42.0f, 5.0f);
        testAnimation.setTimer(new PrecisionTimer());

        testAnimation.animate();
        assertEquals(0.0f, tester.getPosition(), 1.0f);

        Thread.sleep(1000);

        testAnimation.animate();
        assertEquals(8.4f, tester.getPosition(), 1.0f);
    }

    @Test
    public void singleStepAnimate_Test()
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InterruptedException, Exception
    {
        TestObj tester = new TestObj();
        TestTimer testTimer = new TestTimer(1);

        DoubleAnimation testAnimation = new DoubleAnimation(new PropertyInvoker<Float>("Position", tester, Float.class), 42.0f, 5.0f);
        testAnimation.setTimer(testTimer);

        testAnimation.animate();
        assertEquals(0.0f, tester.getPosition(), 0.1f);

        testTimer.setMilliseconds(100);
        testAnimation.animate();
        assertEquals(0.84f, tester.getPosition(), 0.1f);

        testTimer.setMilliseconds(200);
        testAnimation.animate();
        assertEquals(1.68f, tester.getPosition(), 0.1f);

        testTimer.setMilliseconds(300);
        testAnimation.animate();
        assertEquals(2.51f, tester.getPosition(), 0.1f);

        testTimer.setMilliseconds(400);
        testAnimation.animate();
        assertEquals(3.36f, tester.getPosition(), 0.1f);

        testTimer.setMilliseconds(500);
        testAnimation.animate();
        assertEquals(4.2f, tester.getPosition(), 0.1f);

        testTimer.setMilliseconds(600);
        testAnimation.animate();
        assertEquals(5.04f, tester.getPosition(), 0.1f);

        testTimer.setMilliseconds(700);
        testAnimation.animate();
        assertEquals(5.88f, tester.getPosition(), 0.1f);

        testTimer.setMilliseconds(800);
        testAnimation.animate();
        assertEquals(6.72f, tester.getPosition(), 0.1f);

        testTimer.setMilliseconds(900);
        testAnimation.animate();
        assertEquals(7.56f, tester.getPosition(), 0.1f);

        testTimer.setMilliseconds(1000);
        testAnimation.animate();
        assertEquals(8.4f, tester.getPosition(), 0.1f);

        // call again without updating the timer to ensure the position didn't change.
        testAnimation.animate();
        assertEquals(8.4f, tester.getPosition(), 0.1f);
    }

    @Test
    public void autoReverseTest()
    {
        // TODO: write a test for an animation that utilizes the auto reverse repeat method
        assertTrue(false);

    }

    @Test
    public void foreverTest()
    {
        // TODO: write a test for an animation that utilizes the auto reverse repeat method
        assertTrue(false);


    }


}
