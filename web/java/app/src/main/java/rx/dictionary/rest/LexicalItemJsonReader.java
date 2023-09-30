package rx.dictionary.rest;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.Provider;
import rx.dictionary.jpa.entity.LexicalItem;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class LexicalItemJsonReader implements MessageBodyReader<LexicalItem> {
    @Override
    public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return mediaType.isCompatible(MediaType.APPLICATION_JSON_TYPE);
    }

    @Override
    public LexicalItem readFrom(Class<LexicalItem> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> multivaluedMap, InputStream inputStream) throws IOException, WebApplicationException {
        JsonReader jsonReader = Json.createReader(new InputStreamReader(inputStream));
        // Read the JSON object from the InputStream
        JsonObject jsonObject = jsonReader.readObject();
        LexicalItem lexicalItem = new LexicalItem();
        lexicalItem.setLanguage(new Locale(jsonObject.getString("language")));
        lexicalItem.setValue(jsonObject.getString("itemValue"));
        return lexicalItem;
    }
}
