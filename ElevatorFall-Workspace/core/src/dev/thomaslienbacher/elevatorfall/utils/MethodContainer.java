package dev.thomaslienbacher.elevatorfall.utils;

/**
 * @author Thomas Lienbacher
 *
 * TODO: why don't use lambdas
 */
public abstract class MethodContainer {
    public Object[] refs;

    public MethodContainer(Object... refs){
        this.refs = refs;
    }

    public abstract void method();
}
