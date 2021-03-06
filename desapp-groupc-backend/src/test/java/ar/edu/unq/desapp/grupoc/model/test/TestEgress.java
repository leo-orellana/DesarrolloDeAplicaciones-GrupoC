package ar.edu.unq.desapp.grupoc.model.test;

import ar.edu.unq.desapp.grupoc.model.Egress;
import junit.framework.TestCase;

public class TestEgress extends TestCase {

    public void testProcessAmountSubtracsTheAmountToTotal() {
        Egress egress = new Egress();

        assertEquals(new Double(10),
                egress.processAmount(new Double(12), new Double(2)));
    }
    
    public void testIsIngressAndReturnFalse(){
    	Egress egress = new Egress();
    	assertFalse(egress.isIngress());
    }
}
