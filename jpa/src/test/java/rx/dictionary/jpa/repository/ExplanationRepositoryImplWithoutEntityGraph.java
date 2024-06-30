package rx.dictionary.jpa.repository;

import jakarta.persistence.EntityManager;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.repository.input.Keyword;

import java.util.List;
import java.util.Locale;

class ExplanationRepositoryImplWithoutEntityGraph implements ExplanationRepository {
    private final EntityManager entityManager;
    public ExplanationRepositoryImplWithoutEntityGraph(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Explanation> findLike(Keyword keyword, Locale explanationLanguage) {

        return entityManager.createQuery("from Explanation e where e.language =: explanationLanguage" +
                        " and e.lexicalItem.language =: language and e.lexicalItem.value like :value", Explanation.class)
                .setParameter("explanationLanguage", explanationLanguage)
                .setParameter("language", keyword.language())
                .setParameter("value", keyword.value())
                .getResultList();
    }

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