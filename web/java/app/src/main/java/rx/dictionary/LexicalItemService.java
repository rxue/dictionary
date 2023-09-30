package rx.dictionary;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import rx.dictionary.jpa.entity.LexicalItem;

public class LexicalItemService {
    @Inject
    private LexicalItemRepository repo;
    @Transactional
    public void create(LexicalItem lexicalItem) {
        repo.create(lexicalItem);
    }
}
