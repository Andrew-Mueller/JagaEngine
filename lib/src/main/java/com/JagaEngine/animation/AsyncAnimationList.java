package com.JagaEngine.animation;

/**
 * Asynchronous Animation list contains animations that will all be played simultaneously.
 * All animations in the list start at the same time, but end based on their respective target values.
 */
public class AsyncAnimationList extends AnimationList implements IAnimationCompleteHandler
{
    @Override
    public void animationComplete(Object animationObject)
    {
        // intentionally left blank
    }

    public void add(AnimationBase anim)
    {
        anim.handleComplete = this;

        super.add(anim);
    }

    @Override
    public void animate() throws Exception
    {
        IAnimator currentAnim = null;

        for(int i = 0; i < this.size(); i++)
        {
            currentAnim = this.get(i);
            currentAnim.animate();
        }
    }

    @Override
    public void pause()
    {
        IAnimator currentAnim = null;

        for(int i = 0; i < this.size(); i++)
        {
            currentAnim = this.get(i);
            currentAnim.pause();
        }
    }
}
