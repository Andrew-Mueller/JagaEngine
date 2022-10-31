package com.JagaEngine.state;

public interface IState
{
    /**
     * Logic to perform when entering the state.
     */
    void enter();

    /**
     * Logic to perform when exiting the state.
     */
    void exit();

    /**
     * Adds the specified transition to the state.
     * @param transition The transition to add to the state.
     */
    void addTransition(Transition transition);

    /**
     * Set the handler for when a transition occurs.
     * @param transitionHandler handler to call when a transition occurs.
     */
    void setTransitionHandler(ITransitionable transitionHandler);

    /**
     * Send the specified signal to the state.
     * @param signal The signal to send to the state.
     */
    void signal(int signal);
}
