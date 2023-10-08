package rx.dictionary;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import rx.dictionary.dto.ExplanationDTO;
import rx.dictionary.dto.LexicalItemDTO;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;

import java.util.Locale;

public class ExplanationService {
    @Inject
    private LexicalItemRepository lexicalItemRepo;
    @Inject
    private ExplanationRepository explanationRepo;
    @Transactional
    public Explanation add(ExplanationDTO explanationEntry) {
        Explanation addedExplanation = new Explanation();
        LexicalItem lexicalItem = getLexicalItem(explanationEntry);
        addedExplanation.setLexicalItem(lexicalItem);
        explanationRepo.add(addedExplanation);
        return addedExplanation;
    }

    private LexicalItem getLexicalItem(ExplanationDTO explanationDTO) {
        LexicalItem lexicalItem = new LexicalItem();
        final LexicalItemDTO lexicalItemDTO = explanationDTO.getLexicalItem();
        String language = lexicalItemDTO.getLanguage();
        lexicalItem.setLanguage(new Locale(language));
        lexicalItem.setValue(lexicalItemDTO.getValue());
        return lexicalItem;
    }
}
