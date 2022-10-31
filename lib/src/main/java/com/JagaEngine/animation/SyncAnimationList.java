package com.JagaEngine.animation;


/**
 * Collection for animations that are to be executed one after another in the order
 * that they are added to this list.
 */
public class SyncAnimationList extends AnimationList implements IAnimationCompleteHandler
{
    protected IAnimator currentAnim;
    protected int currentAnimIndex;

    public SyncAnimationList()
    {
        currentAnim = null;
        currentAnimIndex = 0;
    }

    public void add(AnimationBase anim)
    {
        anim.handleComplete = this;

        if (this.size() == 0)
        {
            currentAnim = anim;
            currentAnimIndex = 0;
        }

        super.add(anim);
    }

    /**
     * Iterates over the list and looks for animation items in the list
     * that have a property that will keep the item from moving through
     * each animation in the list.
     * @return The DXAnimationBase object that causes the animations in the list
     *         to not be continuous.
    */
    public AnimationBase checkForBreaksInList()
    {
        AnimationBase breakingAnimation = null;

        for(int i = 0; i < this.size(); i++)
        {
            IAnimator animation = this.get(i);

            if ((animation instanceof AnimationBase) &&
                    ((AnimationBase)animation).getRepeat() != RepeatAnimation.ONCE)
            {
                breakingAnimation = (AnimationBase)animation;
            }
        }

        return breakingAnimation;
    }

    /**
     * IAnimationCompleteHandler member
     */
    public void animationComplete()
    {
        currentAnimIndex++;

        if (currentAnimIndex < this.size())
        {
            currentAnim = this.get(currentAnimIndex);
        }
        else
        {
            currentAnim = null;
        }
    }

    public void animate() throws Exception
    {
        if (currentAnim != null)
        {
            currentAnim.animate();
        }
    }

    public void pause()
    {
        if (currentAnim != null)
        {
            currentAnim.pause();
        }
    }
}
