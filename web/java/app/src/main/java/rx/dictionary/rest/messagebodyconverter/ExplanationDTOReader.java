package rx.dictionary.rest.messagebodyconverter;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.Provider;
import rx.dictionary.dto.ExplanationDTO;
import rx.dictionary.jpa.entity.PartOfSpeech;
import rx.dictionary.vo.LexicalItemVO;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Locale;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class ExplanationDTOReader implements MessageBodyReader<ExplanationDTO> {
    @Override
    public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return mediaType.isCompatible(MediaType.APPLICATION_JSON_TYPE);
    }

    @Override
    public ExplanationDTO readFrom(Class<ExplanationDTO> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> multivaluedMap, InputStream inputStream) throws IOException, WebApplicationException {
        System.out.println("ExplanationDTOReader, ExplanationDTOReader, ExplanationDTOReader");
        JsonReader jsonReader = Json.createReader(new InputStreamReader(inputStream));
        JsonObject jsonObject = jsonReader.readObject();
        JsonObject lexicalItemVOJsonObject = jsonObject.getJsonObject("lexicalItem");
        ExplanationDTO.Builder builder = new ExplanationDTO.Builder(new LexicalItemVO(lexicalItemVOJsonObject.getString("value"), new Locale(lexicalItemVOJsonObject.getString("language"))))
            .setPartOfSpeech(PartOfSpeech.valueOf(jsonObject.getString("partOfSpeech")))
            .setExplanationLanguage(new Locale(jsonObject.getString("explanationLanguage")))
            .setExplanation(jsonObject.getString("explanation"));
        return builder.build();
    }
}
