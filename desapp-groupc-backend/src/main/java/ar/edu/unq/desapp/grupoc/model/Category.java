package ar.edu.unq.desapp.grupoc.model;

public class Category {

    public String name;
    public Movement movement;

    public Category(String name, Movement movement) {
        super();
        this.name = name;
        this.movement = movement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setTypeOperation(Movement movement) {
        this.movement = movement;
    }

}
