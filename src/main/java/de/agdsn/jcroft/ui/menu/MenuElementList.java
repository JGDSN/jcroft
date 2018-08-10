package de.agdsn.jcroft.ui.menu;

import de.agdsn.jcroft.permission.PermissionManager;
import de.agdsn.jcroft.permission.PermissionRequirement;
import de.agdsn.jcroft.permission.PermissionSet;

import java.util.ArrayList;

public class MenuElementList extends MenuElement{
    private ArrayList<MenuElement> elements = new ArrayList<>();

    protected MenuElementList(String name, String icon, String href, PermissionRequirement permissionRequirement) {
        super(name, icon, href, permissionRequirement);
    }

    public MenuElementList add(MenuElement el){
        if(el instanceof MenuElementList)throw new IllegalArgumentException("You can't add a list into a list");
        el.level = 1;
        elements.add(el);
        return this;
    }

    @Override
    protected String toHTML(PermissionManager manager, PermissionSet set){
        if(manager.hasPermissions(set, permissionRequirement)){
            StringBuilder encBuilder = new StringBuilder();
            for(MenuElement el : elements)encBuilder.append(el.toHTML(manager, set));
            String enc = encBuilder.toString();
            if(enc.isEmpty()){
                //There are no subitems here for our user
                if(href.equalsIgnoreCase("#")){
                    //There is no link on this tab, so we can exclude it from the view
                    return "";
                }else{
                    //There is a link on this element, so we render it as normal one
                    return super.toHTML(manager, set);
                }
            }else{
                //Render as dropdown
                String build = "<li class=\"treeview\"><a href=\"javascript:void(0)\" onclick=\"navigate('";
                build += href;
                build += "')\"><i class=\"fa ";
                build += icon;
                build += "\"></i><span>";
                build += name;
                build += "</span><span class=\"pull-right-container\"><i class=\"fa fa-angle-left pull-right\"></i></span></a><ul class=\"treeview-menu\">";
                build += enc; //Add all our elements
                build += "</ul></li>";
                return build;
            }
        }else return ""; //No permission -> hide element
    }
}
