package de.agdsn.jcroft.api.v1;

import java.util.TreeMap;

public class APIv1ActionRegistry {
    private TreeMap<String, APIv1Action> actions;

    public APIv1ActionRegistry(){
        actions = new TreeMap<>();
        init();
    }

    /**
     * Adds all actions to this list
     */
    public void init(){

    }

    public void addAction(APIv1Action action){
        if(actions.containsKey(action.getActionName().toLowerCase())){
            throw new IllegalArgumentException("Action \""+action.getActionName()+"\" is already defined!");
        }
        actions.put(action.getActionName().toLowerCase(), action);
    }

    public APIv1Action getAction(String name){
        return actions.get(name.toLowerCase());
    }
}
