package rx.dictionary.rest;

import jakarta.json.Json;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonGeneratorFactory;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;
import rx.dictionary.dto.ExplanationByPartOfSpeech;
import rx.dictionary.jpaentity.Explanation;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Provider
public class ExplanationsByPartOfSpeechWriter implements MessageBodyWriter<List<ExplanationByPartOfSpeech>>{

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public long getSize(List<ExplanationByPartOfSpeech> explanationByPartOfSpeeches, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return MessageBodyWriter.super.getSize(explanationByPartOfSpeeches, type, genericType, annotations, mediaType);
	}

	@Override
	public void writeTo(List<ExplanationByPartOfSpeech> explanationsByPartOfSpeech, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
		Map<String, Object> config = new HashMap<>();
		config.put(JsonGenerator.PRETTY_PRINTING, true);
		JsonGeneratorFactory factory = Json.createGeneratorFactory(config);
		try(JsonGenerator gen = factory.createGenerator(outputStream)) {
			gen.writeStartArray();
			for (ExplanationByPartOfSpeech explanationByPartOfSpeech : explanationsByPartOfSpeech) {
				add(gen, explanationByPartOfSpeech);
			}
			gen.writeEnd();

		}
	}

	private static void add(JsonGenerator generator, ExplanationByPartOfSpeech explanation) {
		generator.writeStartObject()
				.write("partOfSpeech", explanation.getPartOfSpeech().toString());
		{
			generator.writeStartArray("explanations");
			explanation.getExplanations().forEach(generator::write);
			generator.writeEnd();
		}
				generator.writeEnd();
	}

}
