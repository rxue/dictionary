package rx.dictionary.jpa.repository;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.repository.input.Keyword;

import java.util.List;
import java.util.Locale;

public class ExplanationRepositoryImpl implements ExplanationRepository {
    private final EntityManager entityManager;
    public ExplanationRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Explanation> findLike(Keyword keyword, Locale explanationLanguage) {
        return entityManager.createQuery("from Explanation e where e.language =: explanationLanguage" +
                        " and e.lexicalItem.language =: language and e.lexicalItem.value like :value", Explanation.class)
                .setHint("jakarta.persistence.loadgraph", addGraph())
                .setParameter("explanationLanguage", explanationLanguage)
                .setParameter("language", keyword.language())
                .setParameter("value", keyword.value())
                .getResultList();
    }

    private EntityGraph<Explanation> addGraph() {
        EntityGraph<Explanation> explanationGraph = entityManager.createEntityGraph(Explanation.class);
        explanationGraph.addAttributeNodes("lexicalItem");
        explanationGraph.addSubgraph("lexicalItem", LexicalItem.class);
        return explanationGraph;
    }
    @Override
    public void create(List<Explanation> explanations) {
        LexicalItem lexicalItem = explanations.get(0).getLexicalItem();
        if (lexicalItem.getId() == null) {
            entityManager.persist(lexicalItem);
            System.out.println("generated lexical item id is " + lexicalItem.getId());
        }
        for (Explanation e : explanations)
            entityManager.persist(e);
    }

}
