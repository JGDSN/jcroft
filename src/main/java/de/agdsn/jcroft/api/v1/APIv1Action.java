package de.agdsn.jcroft.api.v1;

import de.agdsn.jcroft.database.model.Actor;
import de.agdsn.jcroft.permission.PermissionSet;

public interface APIv1Action {
    public String getActionName();
    public APIv1Response handle(Actor a, PermissionSet p, APIv1Request request);
}
