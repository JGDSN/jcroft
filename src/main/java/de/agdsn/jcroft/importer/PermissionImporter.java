package de.agdsn.jcroft.importer;

import de.agdsn.jcroft.database.data.PermissionCategoryRepository;
import de.agdsn.jcroft.database.model.PermissionCategory;
import de.agdsn.jcroft.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PermissionImporter {

    @Autowired
    protected PermissionCategoryRepository categoryRepository;

    public void importPermissions () {
        ClassLoader classLoader = getClass().getClassLoader();
        File categoriesFile = new File(classLoader.getResource("default-data/permissions/categories.txt").getFile());
        File permissionsFile = new File(classLoader.getResource("default-data/permissions/permissions.txt").getFile());

        //read categories files
        try {
            List<String> lines = FileUtils.readLines(categoriesFile.getAbsolutePath(), StandardCharsets.UTF_8);

            for (String line : lines) {
                //ignore comments
                if (line.startsWith("#")) {
                    continue;
                }

                String[] array = line.split(":");

                if (array.length != 2) {
                    throw new IOException("invalide permission categories file: " + categoriesFile.getAbsolutePath());
                }

                int id = Integer.parseInt(array[0]);
                String title = array[1];

                //check, if id already exists
                Optional<PermissionCategory> categoryOptional = categoryRepository.findById(id);

                if (!categoryOptional.isPresent()) {
                    Logger.getAnonymousLogger().log(Level.INFO, "import permission category: " + title);

                    //create new category
                    PermissionCategory category = new PermissionCategory(id, title);

                    //store in database
                    categoryRepository.save(category);
                }
            }
        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.WARNING, "IOException while importing default permission data", e);
        }
    }

}
