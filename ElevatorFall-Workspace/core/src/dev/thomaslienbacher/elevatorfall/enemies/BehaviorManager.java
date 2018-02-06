package dev.thomaslienbacher.elevatorfall.enemies;

import java.util.HashMap;

/**
 * @author Thomas Lienbacher
 */
public class BehaviorManager {

    private HashMap<Integer, Behavior> behaviors;

    public BehaviorManager(){
        behaviors = new HashMap<Integer, Behavior>();
    }

    public void addBehavior(int key, Behavior b){
        behaviors.put(key, b);
    }

    public void behave(int state){
        Behavior b = behaviors.get(state);
        if(b == null) {
            System.err.println("Couldn't find behavior at state: " + state + "!");
            return;
        }
        b.behave();
    }

}
