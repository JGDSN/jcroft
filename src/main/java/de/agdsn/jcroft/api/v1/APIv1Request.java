package de.agdsn.jcroft.api.v1;

import java.util.Map;

public class APIv1Request {
    private String token;
    private Map<String, String> params;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
