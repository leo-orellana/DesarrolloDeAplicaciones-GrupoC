package ar.edu.unq.desapp.grupoc.model;

public class Category {

	public Integer id;
    public String name;
    public Movement movement;
    
    public Category(){}

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

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
