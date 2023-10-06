package rx.dictionary.rest.messagebodyconverter;

import jakarta.json.Json;
import jakarta.json.stream.JsonGenerator;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;


public class ExplanationWriter implements MessageBodyWriter<Explanation> {


    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return mediaType.isCompatible(MediaType.APPLICATION_JSON_TYPE);
    }

    @Override
    public void writeTo(Explanation explanation, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
        try (JsonGenerator gen = Json.createGenerator(outputStream)) {
            gen.writeStartObject()
                    .write("id", explanation.getId());
            writeLexicalItem(gen, explanation.getLexicalItem());
            gen.writeEnd();
        }
    }
    private static JsonGenerator writeLexicalItem(JsonGenerator jsonGen, LexicalItem lexicalItem) {
        return jsonGen.writeStartObject()
                .write("id", lexicalItem.getId())
                .write("itemValue", lexicalItem.getValue())
                .write("language", lexicalItem.getLanguage().getDisplayLanguage())
                .writeEnd();
    }
}