package ar.edu.unq.desapp.grupoc.model;

public abstract class TypeReceipt {
    
	private int id;
	private String name;
	
    public Double calculateDetail(Receipt receipt){
        receipt.setUntaxed(0.0);
        receipt.setIva(0.0);
    	return 0.0;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
