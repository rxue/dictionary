package rx.dictionary.rest.config.jsonb;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;

@Provider
public class JsonbConfigurator implements ContextResolver<Jsonb> {
    private final Jsonb jsonb;

    public JsonbConfigurator() {
        JsonbConfig config = new JsonbConfig()
                .withDeserializers(new LocaleDeserializer());
        this.jsonb = JsonbBuilder.create(config);
    }

    @Override
    public Jsonb getContext(Class<?> aClass) {
        return jsonb;
    }
}
