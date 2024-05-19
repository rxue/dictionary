package rx.dictionary.rest;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import rx.dictionary.jpa.repository.LexicalItemRepository;
import rx.dictionary.jpa.entity.LexicalItem;

class LexicalItemService {
    private LexicalItemRepository lexicalItemRepo;
    @Inject
    public LexicalItemService(EntityManager em) {
        this.lexicalItemRepo = new LexicalItemRepository(em);
    }
    public LexicalItem findById(Long id) {
        return lexicalItemRepo.findById(id);
    }
    @Transactional
    public LexicalItem create(LexicalItem lexicalItem) {
        lexicalItemRepo.add(lexicalItem);
        return lexicalItem;
    }
}
