package com.JagaEngine.util;

/**
 * implementation of the Timer interface
 */
public class PrecisionTimer implements ITimer
{
    double frequency;

    public PrecisionTimer()
    {
        // TODO: ?
        frequency = 1000.0f;
    }

    @Override
    public float getSeconds(float interval)
    {
        return 0;
    }

    @Override
    public long getMilliseconds()
    {
        return System.currentTimeMillis();
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
}
