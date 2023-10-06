package rx.dictionary;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import rx.dictionary.dto.ExplanationDTO;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.vo.LexicalItemVO;

import java.util.Collections;
import java.util.Optional;

public class ExplanationService {
    @Inject
    private LexicalItemRepository lexicalItemRepo;
    @Inject
    private ExplanationRepository explanationRepo;
    @Transactional
    public Explanation add(ExplanationDTO explanationEntry) {
        final LexicalItemVO lexicalItemVO = explanationEntry.getLexicalItemVO();
        Optional<LexicalItem> optLexicalItem = lexicalItemRepo.find(lexicalItemVO);
        LexicalItem lexicalItem = optLexicalItem.orElseGet(() -> lexicalItemRepo.create(lexicalItemVO));
        Explanation addedExplanation = new Explanation();
        addedExplanation.setLexicalItem(lexicalItem);
        addedExplanation.setPartOfSpeech(explanationEntry.getPartOfSpeech());
        addedExplanation.setLanguage(explanationEntry.getExplanationLanguage());
        addedExplanation.setExplanation(explanationEntry.getExplanation());
        explanationRepo.add(Collections.singletonList(addedExplanation));
        return addedExplanation;
    }
}
