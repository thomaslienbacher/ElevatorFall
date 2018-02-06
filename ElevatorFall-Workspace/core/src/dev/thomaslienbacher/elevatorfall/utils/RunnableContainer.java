package dev.thomaslienbacher.elevatorfall.utils;

/**
 * @author Thomas Lienbacher
 *
 * TODO: why don't use lambdas
 */
public abstract class RunnableContainer implements Runnable {
    protected Object[] refs;

    public RunnableContainer(Object... refs) {
        super();
        this.refs = refs;
    }
}
