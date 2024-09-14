package rx.dictionary.rest;

import io.github.rxue.dictionary.jpa.entity.Explanation;
import io.github.rxue.dictionary.jpa.repository.ExplanationRepository;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public class ExplanationService {
    @Inject
    private EntityManager em;
    private ExplanationRepository explanationRepo;
    @PostConstruct
    public void inject() {
        this.explanationRepo = new ExplanationRepository(em);
    }
    public List<Explanation> create(List<Explanation> newExplanations) {
        return explanationRepo.cascadeAdd(newExplanations);
    }
}
