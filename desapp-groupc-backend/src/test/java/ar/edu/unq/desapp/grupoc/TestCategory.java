package ar.edu.unq.desapp.grupoc;

import static org.mockito.Mockito.*;
import junit.framework.TestCase;

public class TestCategory extends TestCase{

	public void testConstructor(){
		Ingress mockIngress = mock(Ingress.class);
		Category category = new Category("name", mockIngress);
		
		assertEquals(category.getName(), "name");
		assertEquals(category.getMovement(), mockIngress);
	}
}
