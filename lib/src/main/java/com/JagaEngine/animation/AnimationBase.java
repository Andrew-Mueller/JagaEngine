package com.JagaEngine.animation;

import com.JagaEngine.util.ITimer;
import com.JagaEngine.util.PropertyInvoker;

import java.lang.reflect.InvocationTargetException;

/**
 * Base for all objects that can be animated.
 */
public abstract class AnimationBase implements IAnimator
{
    /**
     * event handler for when the animation completes.
     */
    public IAnimationCompleteHandler handleComplete;

    /**
     * flag of whether the animation is currently running or paused.
     */
    protected boolean animate;

    /**
     * reference to the property to animate.
     */
    protected Object targetProperty;

    /**
     * the value of the "animating property" when the animation began.
     */
    protected Object startingTargetValue;

    /**
     * The value of the "animating property" that is the goal of the animation.
     * When this value is reached the RepeatAnimation value is evaluated to decide
     * on the action to take.
     */
    protected Object endingTargetValue;


    /**
     * currently unused...
     */
    protected double duration;

    /**
     * Calculated relative speed that the animation must travel at to make
     * it from the start value to the end value in the amount of time requested.
     */
    protected double speedValue;

    /**
     * Enumerated type to define the units the speedValue is in.
     */
    protected SpeedUnits speedUnit;

    /**
     * Enumerated type defining the rate that speedValue is in.
     */
    protected SpeedRate speedRate;

    /**
     * Enumerated type defining the action the animation should take when the end value is reached.
     */
    protected RepeatAnimation repeat;

    /**
     * the timer interface to use to advance the animation from the start value to the end value.
     */
    protected ITimer timer;

    public Object getEndingTargetValue() { return endingTargetValue; }
    public void setEndingTargetValue(Object targetValue) { endingTargetValue = targetValue; }

    public void setStartingTargetValue(Object targetValue) { startingTargetValue = targetValue; }

    public RepeatAnimation getRepeat() { return repeat; }
    public void setRepeat(RepeatAnimation repeatAnim) { repeat = repeatAnim; }

    public void setTimer(ITimer timer) { this.timer = timer; }

    public abstract void setSpeed(double seconds) throws InvocationTargetException, IllegalAccessException;

    public abstract void animate() throws Exception;
    public abstract void pause();

    /**
     * The implementor of the concrete animation class must provide the method of calculating
     * the distance between the starting value and the target value.
     * @return The 'distance' between the starting value and the target value.
     */
    protected abstract double getDistance() throws InvocationTargetException, IllegalAccessException;

    /**
     * Safe calling of the AnimationComplete event.
     */
    protected void onAnimationComplete(Object animationObject)
    {
        if (handleComplete != null)
        {
            handleComplete.animationComplete(animationObject);
        }
    }

    public enum signals
    {
        STOP_ANIMATION,
        START_ANIMATION
    }
}
