package com.JagaEngine.animation;

import com.JagaEngine.geometry.Vector;
import com.JagaEngine.util.PropertyInvoker;

import java.lang.reflect.InvocationTargetException;

public class VectorAnimation extends AnimationBase
{
    private long startTicks;

    private PropertyInvoker<Vector> currentPosition;

    protected boolean maskX;
    protected boolean maskY;
    protected boolean maskZ;

    public VectorAnimation(PropertyInvoker<Vector> currentPosition, Vector targetValue, double seconds)
            throws IllegalAccessException, InvocationTargetException
    {
        this.startTicks = 0;

        this.maskX = true;
        this.maskY = true;
        this.maskZ = true;

        this.startingTargetValue = new Vector(currentPosition.getValue());
        this.endingTargetValue = new Vector(targetValue);
        this.currentPosition = currentPosition;

        this.speedValue = java.lang.Math.abs(getDistance()) / seconds;

        this.speedUnit = SpeedUnits.PIXELS;
        this.speedRate = SpeedRate.PER_SECOND;

        this.repeat = RepeatAnimation.ONCE;
        this.animate = true;
    }

    public VectorAnimation(PropertyInvoker<Vector> currentPosition,
                           Vector targetValue,
                           boolean maskX,
                           boolean maskY,
                           boolean maskZ,
                           double seconds)
            throws IllegalAccessException, InvocationTargetException
    {
        this(currentPosition, targetValue, seconds);

        this.maskX = maskX;
        this.maskY = maskY;
        this.maskZ = maskZ;

        this.speedValue = java.lang.Math.abs(getDistance()) / seconds;
    }

    protected double getDistance() throws InvocationTargetException, IllegalAccessException
    {
        Vector maskedTargetVal = (Vector)endingTargetValue;
        Vector maskedCurrentPos = currentPosition.getValue();

        if (!maskX)
        {
            maskedTargetVal.setX(0.0f);
            maskedCurrentPos.setX(0.0f);
        }

        if (!maskY)
        {
            maskedTargetVal.setY(0.0f);
            maskedCurrentPos.setY(0.0f);
        }

        if (!maskZ)
        {
            maskedTargetVal.setZ(0.0f);
            maskedCurrentPos.setZ(0.0f);
        }

        return (double)Vector.difference(maskedTargetVal, maskedCurrentPos).length();
    }

    @Override
    public void animate() throws Exception
    {
        long intervalTicks;
        double delta = 0.0D;

        if (null == timer)
        {
            throw new Exception("timer has not yet been set.");
        }

        if (this.animate)
        {
            if (startTicks != 0)
            {
                //intervalTicks = PrecisionTimer.Instance().Stop() - startTicks;
                intervalTicks = timer.getMilliseconds() - startTicks;
            }
            else
            {
                //PrecisionTimer.Instance().Stop();
                intervalTicks = 0;
            }

            // Calculate the proportion of speed since the last animation frame.
            // This translates to the number of pixels that will be moved this frame.
            delta = (double)(speedValue * (double)(intervalTicks / 1000D));

            try
            {
                // Ensure that the position does not exceed the target
                if (java.lang.Math.abs(getDistance()) <= java.lang.Math.abs(delta))
                {
                    delta = getDistance();

                    switch (repeat)
                    {
                        case FOREVER:
                        {
                            // there is a little glitch here because it will never actually reach the endValue
                            delta = getDistance();

                            // set the currentPos back to the start position
                            currentPosition.setValue((Vector)startingTargetValue);

                            break;
                        }
                        case AUTOREVERSE:
                        {
                            Vector tempval = new Vector((Vector)endingTargetValue);
                            endingTargetValue = new Vector((Vector)startingTargetValue);
                            startingTargetValue = tempval;
                            // NOTE: the speed is not reversed for a vector animation.
                            break;
                        }
                        case ONCE:
                        default:
                        {
                            this.animate = false;

                            onAnimationComplete();
                            break;
                        }
                    }
                }

                Vector dir = Vector.difference((Vector)endingTargetValue, (Vector)currentPosition.getValue());


                // Apply the mask to filter out any values that you don't care about.
                // Unfortunately, bitwise AND is not allowed on floats in java... bummer.
                if (!maskX)
                {
                    dir.setX(0.0f);
                }
                if (!maskY)
                {
                    dir.setY(0.0f);
                }
                if (!maskZ)
                {
                    dir.setZ(0.0f);
                }

                dir.normalize();
                dir.scale((float)delta);

                currentPosition.setValue(Vector.sum(currentPosition.getValue(), dir));
            }
            catch (Exception ex)
            {
                // TODO: i'm not sure what exception was being generated by calculating the double
                android.util.Log.e("VectorAnimation", "Error calculating the new motion value", ex);
            }

            if (this.animate)
            {
                startTicks = timer.getMilliseconds();
            }
        }
    }

    @Override
    public void pause()
    {
        startTicks = 0;
    }
}
