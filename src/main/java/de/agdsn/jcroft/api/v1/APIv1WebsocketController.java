package de.agdsn.jcroft.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class APIv1WebsocketController {
    @Autowired
    APIv1Handler apIv1Handler;

    /**
     * Request with a token
     * @param request
     * @return
     * @throws Exception
     */
    @MessageMapping("/v1/{action}/{callback_name}")
    @SendTo("/return/v1/{callback_name}")
    public APIv1Response onTokenRequest(@Payload APIv1Request request, @DestinationVariable String action){
        return apIv1Handler.onRequest(request, action);
    }
}
