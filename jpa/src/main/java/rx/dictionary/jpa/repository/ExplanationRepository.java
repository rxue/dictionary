package rx.dictionary.jpa.repository;

import jakarta.persistence.EntityManager;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.repository.input.Keyword;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ExplanationRepository {
    private final EntityManager entityManager;
    public ExplanationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Explanation> find(Keyword test) {
        return Collections.EMPTY_LIST;
    }
}
