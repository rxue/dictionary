package io.github.rxue.dictionary;

import io.github.rxue.dictionary.jpa.entity.Explanation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
@Path("/explanations")
public class ExplanationResource {
    @PersistenceContext
    private EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Explanation> getAllItems() {
        List<Explanation> explanations = entityManager.createQuery("SELECT e FROM Explanation e", Explanation.class)
                .getResultList();
        return explanations;
    }
}
