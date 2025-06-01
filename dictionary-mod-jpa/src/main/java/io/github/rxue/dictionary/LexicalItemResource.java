package io.github.rxue.dictionary;

import io.github.rxue.dictionary.dto.ExplanationsByLanguageDTO;
import io.github.rxue.dictionary.jpa.entity.Explanation;
import io.github.rxue.dictionary.jpa.entity.LexicalItem;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.Locale;

@Path("/lexical-items")
public class LexicalItemResource {
    @PersistenceContext
    private EntityManager entityManager;
    @Inject
    private DictionaryService dictionaryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LexicalItem> getAllItems() {
        List<LexicalItem> lexicalItems = entityManager.createQuery("SELECT l FROM LexicalItem l")
                .getResultList();
        return lexicalItems;
    }

    @Path("{word}/explanations")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ExplanationsByLanguageDTO getExplanationsByLanguage(@PathParam("word") String word,
                                                               @MatrixParam("language") String languageTag,
                                                               @QueryParam("explanation-language") String explanationLanguageTag) {
        return dictionaryService.getExplanationsByLanguage(Locale.forLanguageTag(languageTag), word, Locale.forLanguageTag(explanationLanguageTag));
    }

}
