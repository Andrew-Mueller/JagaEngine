package com.JagaEngine.state;

public class Transition
{
    protected State targetState;

    protected int signal;

    public int getSignal()
    {
        return signal;
    }

    public State getTargetState()
    {
        return targetState;
    }

    public Transition(int signal, State targetState)
    {
        this.signal = signal;
        this.targetState = targetState;
    }
}
