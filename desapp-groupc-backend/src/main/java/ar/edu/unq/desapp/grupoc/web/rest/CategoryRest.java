package ar.edu.unq.desapp.grupoc.web.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

@Service
@Path("/categories")
public class CategoryRest {

	@GET
	@Path("/single/{id}")
	public String getCategoryById(@PathParam("id") final String id){
		return "category: " + id;
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String getCategories(){
		return "<p>hola</p>";
	}
}
