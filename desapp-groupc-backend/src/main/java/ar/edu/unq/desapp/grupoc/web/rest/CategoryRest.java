package ar.edu.unq.desapp.grupoc.web.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoc.model.Category;
import ar.edu.unq.desapp.grupoc.utils.JSONObject;

@Service
@Path("/categories")
public class CategoryRest {

	@GET
	@Path("/")
	@Produces("application/json")
	public String getCategoryById() throws JsonGenerationException, JsonMappingException, IOException{
		List<Category> categorias = new ArrayList<Category>();
		categorias.add(new Category("hola", null));
		categorias.add(new Category("chau",null));
		return JSONObject.getInstance().ObjectToJSON(categorias);
	}
	
}
