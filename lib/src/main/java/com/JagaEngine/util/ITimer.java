package com.JagaEngine.util;

/**
 * Interface to a generic timer object for some basic timing functions.
 */
public interface ITimer
{
    /**
     * returns the number of seconds given the specified
     * @param interval
     * @return The number of seconds of the timer calculated from the specified interval.
     */
    public abstract float getSeconds(float interval);

    /**
     * return the raw number of milliseconds from the timer.
     * @return The number of milliseconds returned from the internal clock.
     */
    public abstract long getMilliseconds();

    /**
     * start the timer.
     * @return The number of milliseconds of the timer when the timer is started.
     */
    public abstract long start();

    /**
     * Stops the timer.
     * @return The difference between the number of milliseconds when the timer
     * was started and when the timer was stopped.
     */
    public abstract long stop();
}
