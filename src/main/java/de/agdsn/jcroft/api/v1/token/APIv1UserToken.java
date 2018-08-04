package de.agdsn.jcroft.api.v1.token;

import de.agdsn.jcroft.JCroftConfiguration;

public class APIv1UserToken {
    String token;
    long validUntil;
    int userId;
    String username;

    public APIv1UserToken(String token, int userId, String username) {
        this.token = token;
        this.userId = userId;
        this.username = username;
        refresh();
    }

    public void refresh(){
        validUntil = System.currentTimeMillis() + (JCroftConfiguration.getValueInt("session_timeout")*1000L);
    }

    public boolean isValid(){
        return validUntil > System.currentTimeMillis();
    }

    public String getToken() {
        return token;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
