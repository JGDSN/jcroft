package de.agdsn.jcroft.api.v1;

import de.agdsn.jcroft.database.data.ServiceRepository;
import de.agdsn.jcroft.database.model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Optional;

@Controller
public class APIv1Controller {
    @Autowired
    ServiceRepository serviceRepository;

    /**
     * Request without a token (session bound)
     * @param request
     * @param action
     * @param callback_name
     * @return
     * @throws Exception
     */
    @MessageMapping("/v1/session/{action}/{callback_name}")
    @SendTo("/return/v1/{callback_name}")
    public APIv1Response onSessionBoundRequest(APIv1Request request, @DestinationVariable String action, @DestinationVariable String callback_name) throws Exception {
        return new APIv1Response(new HashMap<>());
    }

    /**
     * Request with a token
     * @param request
     * @return
     * @throws Exception
     */
    @MessageMapping("/v1/token/{action}/{callback_name}")
    @SendTo("/return/v1/{callback_name}")
    public APIv1Response onTokenRequest(APIv1Request request, @RequestParam String token, @DestinationVariable String action, @DestinationVariable String callback_name) throws Exception {
        Optional<Service> service = serviceRepository.findByToken(token);
        return service.map((service_inst)->{
            return new APIv1Response(new HashMap<>());
        }).orElseGet(()->{
            HashMap<String, String> response = new HashMap<>();
            response.put("state", "403");
            response.put("msg", "You did not supply a valid token with your request!");
            return new APIv1Response(response);
        });
    }
}
