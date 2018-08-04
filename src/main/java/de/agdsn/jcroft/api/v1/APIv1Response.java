package de.agdsn.jcroft.api.v1;

import java.util.Map;

public class APIv1Response {
    private Map<String, String> params;
    private int httpCode;

    public APIv1Response(int httpCode, Map<String, String> params){
        this.httpCode = httpCode;
        this.params = params;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }
}
