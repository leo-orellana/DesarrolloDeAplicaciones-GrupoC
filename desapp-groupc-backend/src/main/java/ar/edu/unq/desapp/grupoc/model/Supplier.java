package ar.edu.unq.desapp.grupoc.model;

public class Supplier {

	public Integer id;
	public String companyName;
	public String code;
	public String cuit;

	public Supplier() {
	};

	public Supplier(String companyName, String code,
			String cuit) {
		super();
		this.companyName = companyName;
		this.code = code;
		this.cuit = cuit;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
