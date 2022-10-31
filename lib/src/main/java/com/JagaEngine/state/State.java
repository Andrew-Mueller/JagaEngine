package com.JagaEngine.state;

import java.util.ArrayList;

public class State implements IState
{
    protected ITransitionable transitionHandler;

    protected ArrayList<Transition> transitions;

    public State()
    {
        transitions = new ArrayList<Transition>();
    }

    @Override
    public void setTransitionHandler(ITransitionable transitionHandler)
    {
        this.transitionHandler = transitionHandler;
    }

    @Override
    public void enter()
    {
        // no common enter criteria
    }

    @Override
    public void exit()
    {
        // no common exit criteria
    }

    @Override
    public void addTransition(Transition transition)
    {
        transitions.add(transition);
    }

    @Override
    public void signal(int signal)
    {
        for (int i = 0; i < transitions.size(); i++)
        {
            if (signal == transitions.get(i).getSignal())
            {
                // change to the specified state by the transition
                if (null != this.transitionHandler)
                {
                    this.transitionHandler.onTransition(transitions.get(i).getTargetState());
                }

                // NOTE: assuming relationships between signals and transitions are 1 to 1.
                break;
            }
        }
    }
}
