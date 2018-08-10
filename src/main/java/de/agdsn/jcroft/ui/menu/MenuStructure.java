package de.agdsn.jcroft.ui.menu;

import de.agdsn.jcroft.permission.PermissionManager;
import de.agdsn.jcroft.permission.PermissionRequirement;
import de.agdsn.jcroft.permission.PermissionSet;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class MenuStructure {
    @Autowired
    PermissionManager permissionManager;

    private ArrayList<MenuElement> elements;

    public MenuStructure(){
        initStructure();
    }

    private void initStructure() {
        elements = new ArrayList<>();
        elements.add(new MenuElement("Home", "fa-tachometer-alt", "/", new PermissionRequirement("*", 0)));
        elements.add(new MenuElementList("User Management", "fa-user", "#", new PermissionRequirement("*", 0))
                        .add(new MenuElement("Search users", "fa-search", "/search_users", new PermissionRequirement("*", 0)))
                        .add(new MenuElement("Somepermission", "fa-search", "/some_permission", new PermissionRequirement("some.permission", 1))));
    }

    public String toHTML(PermissionSet permissionSet){
        StringBuilder build = new StringBuilder("<li class=\"header\">MAIN NAVIGATION</li>");
        for(MenuElement el : elements){
            build.append(el.toHTML(permissionManager, permissionSet));
        }
        return build.toString();
    }
}
