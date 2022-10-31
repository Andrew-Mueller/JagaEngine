package com.JagaEngine.state;

public interface ITransitionable
{
    /**
     *
     * @param targetState The state to transition to.
     */
    public void onTransition(IState targetState);
}
