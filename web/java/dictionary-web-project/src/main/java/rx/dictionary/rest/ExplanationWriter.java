package rx.dictionary.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import rx.dictionary.jpaentity.Explanation;

@Provider
@Produces("application/json")
public class ExplanationWriter implements MessageBodyWriter<List<Explanation>>{

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public long getSize(List<Explanation> t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeTo(List<Explanation> explanation, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		System.out.println(":::::::::::::::::::::::::::write now::::::::::::::");
		Map<String, Object> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);
		JsonGeneratorFactory factory = Json.createGeneratorFactory(config);
		try(JsonGenerator gen = factory.createGenerator(entityStream)) {
		System.out.println("explanation ID is " + explanation.get(0).getId());
		gen.writeStartObject()
		.write("idxxxxxxxxxxxxx", explanation.get(0).getId())
		.writeEnd();}
		
	}

}
