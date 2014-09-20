package ar.edu.unq.desapp.grupoc.model.test;

import static org.mockito.Mockito.*;
import ar.edu.unq.desapp.grupoc.model.Category;
import ar.edu.unq.desapp.grupoc.model.Ingress;
import junit.framework.TestCase;

public class TestCategory extends TestCase {

    public void testConstructor() {
        Ingress mockIngress = mock(Ingress.class);
        Category category = new Category("name", mockIngress);

        assertEquals(category.getName(), "name");
        assertEquals(category.getMovement(), mockIngress);
    }
}
