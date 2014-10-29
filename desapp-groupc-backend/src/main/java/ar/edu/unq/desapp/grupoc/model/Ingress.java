package ar.edu.unq.desapp.grupoc.model;

public class Ingress extends Movement {

	public Ingress(){}
	
    @Override
    public Double processAmount(Double total, Double amount) {
        return total + amount;
    }

	@Override
	public boolean isIngress() {
		return true;
	}

}
