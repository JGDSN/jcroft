package de.agdsn.jcroft.api.v1;

import de.agdsn.jcroft.database.data.ServiceRepository;
import de.agdsn.jcroft.database.model.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Optional;

public class APIv1Handler {
    @Autowired
    ServiceRepository serviceRepository;

    public APIv1Response onRequest(APIv1Request request, String action){
        Optional<Service> service = serviceRepository.findByToken(request.getToken());
        return service.map(serviceInst -> this.performAction(action, request))
                .orElseGet(()->{
                    HashMap<String, String> response = new HashMap<>();
                    response.put("msg", "You did not supply a valid token with your request!");
                    return new APIv1Response(403, response);
                });
    }
    public APIv1Response performAction(String action, APIv1Request request){
        HashMap<String, String> response = new HashMap<>();
        response.put("it", "works");
        response.put("action", action);
        response.putAll(request.getParams());
        return new APIv1Response(200, response);
    }
}
