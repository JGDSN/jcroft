package de.agdsn.jcroft.permission;

import de.agdsn.jcroft.database.model.Actor;
import de.agdsn.jcroft.database.model.Group;
import de.agdsn.jcroft.database.model.GroupPermission;

import java.util.*;
import java.util.regex.Pattern;

public class PermissionSet {
    Map<String, Integer> permissions;
    public PermissionSet(Actor actor){
        Set<Group> groups = actor.getGroups();
        //Build permission map
        this.permissions = new TreeMap<>();
        for(Group g : groups){
            for(GroupPermission p : g.getPermissions()){
                String token = p.getPermission().getToken();
                Integer level = p.getPower();
                applyPermission(token, level);
            }
        }
    }

    public void applyPermission(String token, Integer level) {
        Integer curr = permissions.get(token);
        if(curr==null){
            permissions.put(token, level);
        }else{
            if(curr>=0){ //If curr is negative it's already forbidden
                if(level<0){ //New group forbids previously granted permission
                    permissions.put(token, -1);
                }else if(level>curr){ //New group elevates permission level
                    permissions.put(token, level);
                }
            }
        }
    }

    public Map<String, Integer> getPermissions(){
        return permissions;
    }

    public int getPermissionLevel(String token){
        //Tokens in form of a.b.c.d or a.b.c.* or a.b.* (which all grant a.b.c.d)
        int maxlevel = 0;
        //Split the token in its parts
        String[] parts = token.split(Pattern.quote("."));
        for(int i = 0; i<=parts.length; i++){
            String perm = "*";
            if(i>0&&i<parts.length){
                StringBuilder permBuilder = new StringBuilder(parts[0]);
                for(int a = 1; a<i; a++){
                    permBuilder.append(".").append(parts[a]);
                }
                permBuilder.append(".*");
                perm = permBuilder.toString();
            }else if(i==parts.length)perm = token;
            //At this point, String perm holds all permissions (combined over every run)
            Integer comp = permissions.get(perm);
            if(comp!=null){
                if(comp==-1)maxlevel = -1;
                else if(maxlevel!=-1 && comp>maxlevel)maxlevel = comp;   //Permission is join over all parts
            }
        }
        return maxlevel;
    }
}
