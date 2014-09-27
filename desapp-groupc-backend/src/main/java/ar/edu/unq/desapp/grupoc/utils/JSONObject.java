package ar.edu.unq.desapp.grupoc.utils;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

public class JSONObject {
	
	private static JSONObject instance;
	
	public static JSONObject getInstance(){
		if(instance == null){
			instance = new JSONObject();
		}
		return instance;
	}

	public String ObjectToJSON(Object object) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(object);
		return json;
	}
}
