package ar.edu.unq.desapp.grupoc.model.test;

import ar.edu.unq.desapp.grupoc.model.Ingress;
import junit.framework.TestCase;

public class TestIngress extends TestCase {

    public void testProcessAmountSumsTheAmountToTotal() {
        Ingress ingress = new Ingress();

        assertEquals(new Double(14),
                ingress.processAmount(new Double(12), new Double(2)));
    }
}