package de.agdsn.jcroft.api.v1;

import de.agdsn.jcroft.api.v1.token.APIv1UserToken;
import de.agdsn.jcroft.api.v1.token.APIv1UserTokenRepository;
import de.agdsn.jcroft.database.data.ServiceRepository;
import de.agdsn.jcroft.database.data.UserRepository;
import de.agdsn.jcroft.database.model.Actor;
import de.agdsn.jcroft.database.model.Service;
import de.agdsn.jcroft.database.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Optional;

public class APIv1Handler {
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    APIv1UserTokenRepository apIv1UserTokenRepository;
    @Autowired
    APIv1ActionRegistry apIv1ActionRegistry;

    public APIv1Response onRequest(APIv1Request request, String action){
        Optional<Service> service = serviceRepository.findByToken(request.getToken());
        return service.map(serviceInst -> this.performAction(serviceInst.getActor(), action, request))
                .orElseGet(()->{
                    APIv1UserToken token = apIv1UserTokenRepository.lookup(request.getToken());
                    if(token==null) {
                        HashMap<String, String> response = new HashMap<>();
                        response.put("msg", "You did not supply a valid token with your request!");
                        return new APIv1Response(403, response);
                    }else{
                        Optional<User> user = userRepository.findById(token.getUserId());
                        return user.map(u -> this.performAction(u.getActor(), action, request))
                                .orElseGet(()->{
                                    HashMap<String, String> response = new HashMap<>();
                                    response.put("msg", "Your identity could not be verified! Did you just get deleted?");
                                    return new APIv1Response(403, response);
                                });
                    }
                });
    }
    public APIv1Response performAction(Actor actor, String actionName, APIv1Request request){
        APIv1Action action = apIv1ActionRegistry.getAction(actionName);
        if(action==null){
            HashMap<String, String> response = new HashMap<>();
            response.put("error", "404 - Action not found");
            response.putAll(request.getParams());
            return new APIv1Response(404, response);
        }
        return action.handle(actor, actor.getPermissions(), request);
    }
}
