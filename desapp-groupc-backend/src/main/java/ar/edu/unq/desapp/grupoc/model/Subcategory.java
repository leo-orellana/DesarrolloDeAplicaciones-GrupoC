package ar.edu.unq.desapp.grupoc.model;

public class Subcategory {

    public String name;
    public Category category;

    public Subcategory(String name, Category category) {
        super();
        this.setName(name);
        this.setCategory(category);
    }

    // ////////

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
