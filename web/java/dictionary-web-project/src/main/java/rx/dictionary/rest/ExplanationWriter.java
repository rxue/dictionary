package rx.dictionary.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.json.Json;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonGeneratorFactory;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;

import rx.dictionary.jpaentity.Explanation;

@Provider
public class ExplanationWriter implements MessageBodyWriter<List<Explanation>>{

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		System.out.println("LEARNING DEBUG:: class type is " + type);
		System.out.println("LEARNING DEBUG:: generic type is " + genericType);
		System.out.println("LEARNING DEBUG:: annotation is " + annotations);
		System.out.println("LEARNING DEBUG:: media type is " + mediaType);
		return true;
	}

	@Override
	public long getSize(List<Explanation> t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeTo(List<Explanation> explanations, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		Map<String, Object> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);
		JsonGeneratorFactory factory = Json.createGeneratorFactory(config);
		try(JsonGenerator gen = factory.createGenerator(entityStream)) {
			gen.writeStartArray();
			for (Explanation explanation : explanations) {
				add(gen, explanation);
			}
			gen.writeEnd();
			
		}
	}
	private static void add(JsonGenerator generator, Explanation explanation) {
		generator.writeStartObject()
				.write("explanation in language", explanation.getLanguage().getDisplayLanguage())
				.write("partOfSpeech", explanation.getPartOfSpeech().name())
				.write("explanation", explanation.getExplanation())
				.writeEnd();
	}
}
