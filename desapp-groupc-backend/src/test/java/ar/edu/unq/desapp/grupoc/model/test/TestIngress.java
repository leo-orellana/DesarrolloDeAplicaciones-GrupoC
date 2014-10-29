package ar.edu.unq.desapp.grupoc.model.test;

import junit.framework.TestCase;
import ar.edu.unq.desapp.grupoc.model.Ingress;

public class TestIngress extends TestCase {

    public void testProcessAmountSumsTheAmountToTotal() {
        Ingress ingress = new Ingress();

        assertEquals(new Double(14),
                ingress.processAmount(new Double(12), new Double(2)));
    }
    
    public void testIsIngressAndReturnTrue(){
    	Ingress ingress = new Ingress();
    	assertTrue(ingress.isIngress());
    }
}
