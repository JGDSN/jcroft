package de.agdsn.jcroft.importer;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class PermissionImporter {

    public void importPermissions () {
        ClassLoader classLoader = getClass().getClassLoader();
        File categoriesFile = new File(classLoader.getResource("default-data/permissions/categories.txt").getFile());
        File permissionsFile = new File(classLoader.getResource("default-data/permissions/permissions.txt").getFile());
    }

}
