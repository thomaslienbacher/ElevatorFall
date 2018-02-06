package dev.thomaslienbacher.elevatorfall.enemies;

/**
 * @author Thomas Lienbacher
 */
public abstract class Behavior {
    protected Object ref;

    public Behavior(Object ref){
        this.ref = ref;
    }

    public abstract void behave();
}
