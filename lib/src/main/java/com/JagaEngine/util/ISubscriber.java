package com.JagaEngine.util;

/**
 * Signalable interface allows an object to receive signals from objects that know about it.
 */
public interface ISubscriber
{
    /**
     * Handle the specific signal sent by the publisher.
     * @param signal Unique signal value received from the publisher to process.
     */
    public void handle(int signal);
}
