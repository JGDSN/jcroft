package de.agdsn.jcroft.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIv1RestController {
    @Autowired
    APIv1Handler apIv1Handler;

    /**
     * Request with a token
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/api/rest/v1/{action}")
    public APIv1Response onRequest(APIv1Request request, @DestinationVariable String action){
        return apIv1Handler.onRequest(request, action);
    }
}
