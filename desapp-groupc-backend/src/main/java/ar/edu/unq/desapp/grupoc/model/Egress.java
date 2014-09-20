package ar.edu.unq.desapp.grupoc.model;

public class Egress extends Movement {

    @Override
    public Double processAmount(Double total, Double amount) {
        return total - amount;
    }

}
