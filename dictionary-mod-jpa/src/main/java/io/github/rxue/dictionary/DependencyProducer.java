package io.github.rxue.dictionary;

import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class DependencyProducer {
    @Inject
    @PersistenceContext
    private EntityManager entityManager;
}
