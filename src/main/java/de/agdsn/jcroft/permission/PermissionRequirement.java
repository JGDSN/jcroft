package de.agdsn.jcroft.permission;

import java.util.Map;
import java.util.TreeMap;

public class PermissionRequirement {
    private TreeMap<String, Integer> map = new TreeMap<>();
    public PermissionRequirement(String token, int power){
        map.put(token, power);
    }
    public PermissionRequirement append(String token, int power){
        map.put(token, power);
        return this;
    }
    public Map<String, Integer> getRequiredPermissions(){
        return map;
    }
}
