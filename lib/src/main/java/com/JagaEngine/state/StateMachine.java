package com.JagaEngine.state;

import java.util.ArrayList;

public class StateMachine implements ITransitionable
{
    protected ArrayList<IState> stateList;

    protected int lastGeneratedId;

    protected IState currentState;

    public IState getCurrentState() { return currentState; }

    /**
     * Initialize the state machine with no states, and the current state is empty.
     */
    public StateMachine()
    {
        currentState = null;

        stateList = new ArrayList<IState>();

        lastGeneratedId = 0;
    }

    /**
     * Sends the specified signal to the current state.
     * @param signal Signal to send to the state machine.
     */
    public void signal(int signal)
    {
        currentState.signal(signal);
    }

    /**
     * Realization of the handler when a state transitions from one.
     * @param targetState The state to transition to.
     */
    @Override
    public void onTransition(IState targetState)
    {
        if (stateList.contains(targetState))
        {
            // call the exit method of the previous state
            currentState.exit();

            // switch to the new state
            currentState = targetState;

            // perform the state enter functionality.
            currentState.enter();
        }
    }

    /**
     * Creates an ID to be used as a unique transition number as long as all of the transition
     * numbers are generated from the same state manager.
     * @return Unique transition id.
     */
    public int generateID()
    {
        return lastGeneratedId++;
    }

    /**
     * Adds the specified state to the state machine.  This becomes the current state if it is the
     * first state to be added.
     * @param state The state to add to this state machine.
     */
    public void add(IState state)
    {
        if (null == currentState)
        {
            currentState = state;
        }

        // set this state machine as the transition handler for the state (function above).
        state.setTransitionHandler(this);

        stateList.add(state);
    }
}
