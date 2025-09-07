package io.github.rxue.dictionary;

import io.github.rxue.dictionary.dto.ExplanationsByLanguageDTO;
import io.github.rxue.dictionary.jpa.entity.LexicalItem;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Locale;

@Path("/lexical-items")
public class LexicalItemResource {
    @Inject
    private DictionaryService dictionaryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LexicalItem> getAllItems() {
        return dictionaryService.getAllLexicalItems();
    }

    @Path("{word}/explanations")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ExplanationsByLanguageDTO getExplanationsByLanguage(@PathParam("word") String word,
                                                               @MatrixParam("language") String languageTag,
                                                               @QueryParam("language") String explanationLanguageTag) {
        return dictionaryService.getExplanationsByLanguage(Locale.forLanguageTag(languageTag), word, Locale.forLanguageTag(explanationLanguageTag))
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

}
