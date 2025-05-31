package io.github.rxue.dictionary;

import io.github.rxue.dictionary.jpa.entity.Explanation;
import io.github.rxue.dictionary.jpa.entity.LexicalItem;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LexicalItem> getAllItems() {
        List<LexicalItem> lexicalItems = entityManager.createQuery("SELECT l FROM LexicalItem l", LexicalItem.class)
                .getResultList();
        return lexicalItems;
    }

    @Path("{word}/explanations")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Explanation> getExplanationsByLanguage(@PathParam("word") String word,
                                                       @MatrixParam("language") String languageLocaleTag,
                                                       @QueryParam("explanation-language") Locale explanationLanguageLocaleTag) {
        System.out.println("word: " + word);
        System.out.println("language: " + languageLocaleTag);
        System.out.println("explanation in language Locale " + explanationLanguageLocaleTag);
        List<Explanation> explanations = entityManager.createQuery("SELECT e FROM Explanation e", Explanation.class)
                .getResultList();
        return explanations;
    }
}
