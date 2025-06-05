package io.github.rxue.dictionary;

import io.github.rxue.dictionary.dto.ExplanationsByLanguageDTO;
import io.github.rxue.dictionary.dto.ExplanationsByPartOfSpeech;
import io.github.rxue.dictionary.jpa.entity.Explanation;
import io.github.rxue.dictionary.jpa.entity.LexicalItem;
import io.github.rxue.dictionary.jpa.entity.PartOfSpeech;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@ApplicationScoped
public class LexicalItemRepository {

    private EntityManager entityManager;
    public LexicalItemRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    Long addLexicalItem(LexicalItem lexicalItem) {
        entityManager.persist(lexicalItem);
        return lexicalItem.getId();
    }

}
