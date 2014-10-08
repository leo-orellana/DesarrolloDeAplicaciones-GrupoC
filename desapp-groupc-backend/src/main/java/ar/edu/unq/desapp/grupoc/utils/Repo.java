package ar.edu.unq.desapp.grupoc.utils;

import ar.edu.unq.desapp.grupoc.model.Category;
import ar.edu.unq.desapp.grupoc.model.Egress;
import ar.edu.unq.desapp.grupoc.model.Ingress;

public class Repo {

	
	public void createObjects(){
	Ingress ingress = new Ingress();
		ingress.setName("Ingress");
		Egress egress = new Egress();
		egress.setName("Egress");
	
		Category ventas = new Category();
		ventas.setName("Ventas");
		ventas.setMovement(ingress);
		
		Category rifas = new Category();
		rifas.setName("Rifas");
		rifas.setMovement(ingress);
	}
}
