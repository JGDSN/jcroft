package de.agdsn.jcroft.permission;

import java.util.Map;

public class PermissionManager {
    public boolean hasPermissions(PermissionSet permissions, PermissionRequirement requirements){
        for(Map.Entry<String, Integer> entry : requirements.getRequiredPermissions().entrySet()){
            if(permissions.getPermissionLevel(entry.getKey())<entry.getValue())return false; //Real level below required
        }
        return true; //No conflicts found
    }
}
