package com.JagaEngine.animation;

import java.util.ArrayList;

public abstract class AnimationList extends ArrayList<IAnimator> implements IAnimator
{
    public abstract void animate() throws Exception;

    public abstract void pause();
}
