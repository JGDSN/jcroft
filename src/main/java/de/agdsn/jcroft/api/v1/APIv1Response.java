package de.agdsn.jcroft.api.v1;

import java.util.Map;

public class APIv1Response {
    private Map<String, String> params;
    public APIv1Response(Map<String, String> params){
        this.params = params;
    }
}
