package rx.dictionary.rest.config.jsonb;

import jakarta.json.bind.serializer.DeserializationContext;
import jakarta.json.bind.serializer.JsonbDeserializer;
import jakarta.json.stream.JsonParser;

import java.lang.reflect.Type;
import java.util.Locale;

class LocaleDeserializer implements JsonbDeserializer<Locale> {
    /**
     * input String to Locale
     * @param jsonParser
     * @param deserializationContext
     * @param type
     * @return
     */
    @Override
    public Locale deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Type type) {
        String localeString = jsonParser.getString();
        return Locale.forLanguageTag(localeString);
    }
}
