package io.github.rxue.dictionary;

import io.github.rxue.dictionary.jpa.entity.Explanation;
import io.github.rxue.dictionary.jpa.entity.LexicalItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Path("/lexical-items")
public class LexicalItemResource {
    @PersistenceContext
    private EntityManager entityManager;

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
    public List<Explanation> getExplanationsByLanguage(@PathParam("word") String word,
                                                       @MatrixParam("language") String languageTag,
                                                       @QueryParam("explanation-language") String explanationLanguageTag) {
        System.out.println(Objects.equals(Locale.forLanguageTag(languageTag), Locale.US));
        System.out.println(Objects.equals(Locale.forLanguageTag(explanationLanguageTag), Locale.SIMPLIFIED_CHINESE));
        List<Explanation> explanations = entityManager.createQuery("SELECT e FROM LexicalItem l LEFT JOIN Explanation e ON l = e.lexicalItem WHERE l.value = :word AND l.language = :language and e.language =:explanationLanguage", Explanation.class)
                .setParameter("word", word)
                .setParameter("language", Locale.forLanguageTag(languageTag))
                .setParameter("explanationLanguage", Locale.forLanguageTag(explanationLanguageTag))
                .getResultList();
        return explanations;
    }
}
