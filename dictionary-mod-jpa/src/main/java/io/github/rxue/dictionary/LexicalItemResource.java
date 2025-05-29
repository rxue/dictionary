package io.github.rxue.dictionary;

import io.github.rxue.dictionary.jpa.entity.LexicalItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

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
}
