package de.agdsn.jcroft.database.data;

import de.agdsn.jcroft.database.model.Property;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
/*@DataJpaTest
@ExtendWith(value = {SpringExtension.class})*/
@ActiveProfiles("test")
public class PropertyRepositoryTest {

    @Autowired
    protected PropertyRepository repository;

    @Test
    public void saveProperty() {
        //check for problems with spring, if spring cannot find bean
        assertNotNull(repository);

        Property property = new Property("test", "title");
        repository.save(property);

        //find property
        Optional<Property> propertyOptional = repository.findById("test");
        assertEquals(true, propertyOptional.isPresent());

        Property property1 = propertyOptional.get();
        assertEquals("test", property1.getToken());
        assertEquals("title", property1.getTitle());
    }

}
