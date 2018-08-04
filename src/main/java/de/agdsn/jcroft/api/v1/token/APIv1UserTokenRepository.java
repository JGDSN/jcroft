package de.agdsn.jcroft.api.v1.token;

import java.util.*;

/**
 * Tokens get refreshed every time they are obtained, so they last for session_timeout seconds more
 * Tokens are used to obtain access to the api as a user (services have seperate tokens that do not time out)
 */
public class APIv1UserTokenRepository {
    private TreeMap<String, APIv1UserToken> tokenMap = new TreeMap<>();

    public synchronized APIv1UserToken issue(String username, int userId){
        revoke(username);
        APIv1UserToken token = new APIv1UserToken(username+UUID.randomUUID(), userId, username);
        tokenMap.put(token.getToken(), token);
        return token;
    }

    public synchronized void revoke(String username){
        List<APIv1UserToken> revoke = new ArrayList<APIv1UserToken>(2);
        for(Map.Entry<String, APIv1UserToken> entry : tokenMap.entrySet()){
            if(entry.getValue().getUsername().equalsIgnoreCase(username)){
                revoke.add(entry.getValue());
            }
        }
        revoke.iterator().forEachRemaining(token -> tokenMap.remove(token.getToken()));
    }

    public synchronized APIv1UserToken lookup(String token){
        if(token==null)return null;
        APIv1UserToken t = tokenMap.get(token);
        if(t!=null) {
            if (!t.isValid()) {
                tokenMap.remove(t.getToken());
                t = null;
            } else {
                t.refresh();
            }
        }
        return t;
    }

    public synchronized APIv1UserToken lookupByUser(String username){
        for(Map.Entry<String, APIv1UserToken> entry : tokenMap.entrySet()) {
            if (entry.getValue().getUsername().equalsIgnoreCase(username)) {
                if(!entry.getValue().isValid())continue;
                APIv1UserToken token = entry.getValue();
                token.refresh();
                return token;
            }
        }
        return null;
    }
}
