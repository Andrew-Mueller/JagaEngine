package com.JagaEngine.android;

/**
 * Interface to handle the touch press and touch drag.
 */
public interface ITouchHandler
{
    /**
     * Handler for the Pressed Touch component.
     * @param x Horizontal component of the point pressed
     * @param y Vertical component of the point pressed
     */
    public abstract void handleTouchPress(float x, float y);

    /**
     * Handler for the Drag Touch component.
     * @param x Horizontal component of the point dragged?
     * @param y Vertical component of the point dragged?
     */
    public abstract void handleTouchDrag(float x, float y);
}
