package com.JagaEngine.util;

import java.util.List;

/**
 * Send a signal to a  object that knows about
 */
public interface IPublisher
{
    /**
     * Send the specified signal to the handler of the signal.
     * @param signal The signal to send to the object handling the signal.
     */
    public void publish(int signal);

    /**
     * Add the specified subscriber to this lists of publishers
     * @param subscriber The object that is subscribing to this publisher.
     * @param signals List of signals to subscribe to from the publisher
     */
    public void addSubscriber(ISubscriber subscriber, List<Integer> signals);
}
