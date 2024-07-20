package rx.dictionary.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import rx.dictionary.rest.vo.DictionaryEntryByLanguage;

import java.util.Locale;
@Path("glossary")
public class DictionaryResource {
    @GET
    @Path("{language}")
    @Produces(MediaType.APPLICATION_JSON)
    public DictionaryEntryByLanguage getDictionaryEntry(@PathParam("language") Locale language) {
        System.out.println("DEBUG::DEBUG::DEBUG::DEBUG");
        return new DictionaryEntryByLanguage(Locale.ENGLISH, "test");
    }
}
