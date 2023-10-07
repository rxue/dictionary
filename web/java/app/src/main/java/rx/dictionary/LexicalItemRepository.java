package rx.dictionary;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.vo.LexicalItemVO;

public class LexicalItemRepository {
    @Inject
    private EntityManager em;
    public void create(LexicalItem lexicalItem) {
        em.persist(lexicalItem);
        System.out.println("right after persist and before commit the entity LexicalItem id is " + lexicalItem.getId());
    }
    public LexicalItem addOrUpdate(LexicalItemVO lexicalItemVO) {
        LexicalItem lexicalItem = new LexicalItem();
        lexicalItem.setLanguage(lexicalItem.getLanguage());
        lexicalItem.setValue(lexicalItemVO.getValue());
        return em.merge(lexicalItem);
    }
}
