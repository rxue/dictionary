package rx.dictionary.jpa.repository;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.repository.input.Keyword;

import java.util.List;
import java.util.Locale;

public class ExplanationRepositoryWithEntityGraph implements ExplanationRepository {
    private final EntityManager entityManager;
    public ExplanationRepositoryWithEntityGraph(EntityManager entityManager) {
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

    @Override
    public void create(List<Explanation> explanation1) {
        throw new UnsupportedOperationException();
    }

    private EntityGraph<Explanation> addGraph() {
        EntityGraph<Explanation> explanationGraph = entityManager.createEntityGraph(Explanation.class);
        explanationGraph.addAttributeNodes("lexicalItem");
        explanationGraph.addSubgraph("lexicalItem", LexicalItem.class);
        return explanationGraph;
    }

}
