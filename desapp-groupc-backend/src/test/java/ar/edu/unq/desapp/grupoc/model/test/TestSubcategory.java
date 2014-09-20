package ar.edu.unq.desapp.grupoc.model.test;

import ar.edu.unq.desapp.grupoc.model.Category;
import ar.edu.unq.desapp.grupoc.model.Subcategory;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

public class TestSubcategory extends TestCase {

    public void testConstructor() {
        Category mockCategory = mock(Category.class);
        Subcategory subcategory = new Subcategory("name", mockCategory);

        assertEquals("name", subcategory.getName());
        assertEquals(mockCategory, subcategory.getCategory());
    }

}
