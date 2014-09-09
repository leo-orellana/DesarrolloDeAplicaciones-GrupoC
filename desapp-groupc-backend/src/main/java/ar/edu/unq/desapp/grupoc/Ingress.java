package ar.edu.unq.desapp.grupoc;

public class Ingress extends Movement {

    @Override
    public Double processAmount(Double total, Double amount) {
        return total + amount;
    }

}
