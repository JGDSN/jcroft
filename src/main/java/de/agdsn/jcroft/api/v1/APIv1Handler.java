package de.agdsn.jcroft.api.v1;

import java.util.HashMap;

public class APIv1Handler {
    public APIv1Response performAction(String action, APIv1Request request){
        HashMap<String, String> response = new HashMap<>();
        response.put("it", "works");
        response.put("action", action);
        response.putAll(request.getParams());
        return new APIv1Response(response);
    }
}
