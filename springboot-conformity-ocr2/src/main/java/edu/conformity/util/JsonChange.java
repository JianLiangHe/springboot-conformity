package edu.conformity.util;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonChange {

	public static <T> Map<String, Object> json2map(String jsonString) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		
		return mapper.readValue(jsonString, Map.class);
	}
	
}
