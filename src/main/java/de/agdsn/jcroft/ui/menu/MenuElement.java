package de.agdsn.jcroft.ui.menu;

import de.agdsn.jcroft.permission.PermissionManager;
import de.agdsn.jcroft.permission.PermissionRequirement;
import de.agdsn.jcroft.permission.PermissionSet;

public class MenuElement {
    protected int level = 0;
    protected String name;
    protected String icon;
    protected String href;
    protected PermissionRequirement permissionRequirement;

    protected MenuElement(String name, String icon, String href, PermissionRequirement permissionRequirement){
        this.name = name;
        this.icon = icon;
        this.href = href;
        this.permissionRequirement = permissionRequirement;
    }

    protected String toHTML(PermissionManager manager, PermissionSet set){
        if(manager.hasPermissions(set, permissionRequirement)){
            if(level==0){
                //Element on the primary level
                return "<li><a href=\"javascript:void(0)\" onclick=\"navigate('" + href +
                        "')\"><i class=\"fa " + icon +
                        "\"></i><span>" + name +
                        "</span></a></li>";
            }else{
                //Element in a dropdown
                return "<li><a href=\"javascript:void(0)\" onclick=\"navigate('" + href
                        + "')\">" + name + "</a></li>";
            }
        }else return ""; //No permission -> hide element
    }
}
