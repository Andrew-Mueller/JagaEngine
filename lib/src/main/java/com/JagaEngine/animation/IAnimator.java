package com.JagaEngine.animation;

public interface IAnimator
{
    public enum SpeedUnits
    {
        PIXELS,
        METERS,
    };

    public enum SpeedRate
    {
        PER_SECOND,
        PER_MILLISECOND,
    };

    public enum RepeatAnimation
    {
        FOREVER, // = 0,         /* Repeats the animation by instantly returning the value to the starting value when the animation reaches the ending value*/
        ONCE, // = 1,            /* Stop the animation when the value reaches the ending value. */
        AUTOREVERSE // = 2,     /* Repeat the animation by reversing the starting and ending positions when the ending value is reached (repeats forever) */
    };

    public abstract void animate() throws Exception;

    public abstract void pause();
}

