package rx.dictionary.rest;

import jakarta.json.Json;
import jakarta.json.stream.JsonGenerator;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;
import rx.dictionary.jpa.entity.LexicalItem;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class LexicalItemWriter implements MessageBodyWriter<LexicalItem> {
    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return mediaType.isCompatible(MediaType.APPLICATION_JSON_TYPE);
    }

    @Override
    public void writeTo(LexicalItem lexicalItem, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
        System.out.println("write:::::::::::::::::::::::::::::");
        try (JsonGenerator gen = Json.createGenerator(outputStream)) {
            gen.writeStartObject()
                    .write("id", lexicalItem.getId())
                    .write("itemValue", lexicalItem.getValue())
                    .write("language", lexicalItem.getLanguage().getDisplayLanguage())
                    .writeEnd();
        }
    }
}
