package rx.dictionary;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import rx.dictionary.rest.dto.ExplanationDTO;
import rx.dictionary.rest.dto.LexicalItemDTO;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.entity.PartOfSpeech;

import java.util.Locale;

public class ExplanationService {
    @Inject
    private LexicalItemRepository lexicalItemRepo;
    @Inject
    private ExplanationRepository explanationRepo;
    @Transactional
    public Explanation add(ExplanationDTO explanationDTO) {
        Explanation addedExplanation = new Explanation();
        /*addedExplanation.setLexicalItem(getLexicalItem(explanationDTO.getLexicalItem()));
        addedExplanation.setLanguage(Locale.forLanguageTag(explanationDTO.getExplanationLanguage()));
        addedExplanation.setPartOfSpeech(PartOfSpeech.valueOf(explanationDTO.getPartOfSpeech()));
        addedExplanation.setExplanation(explanationDTO.getExplanation());*/
        explanationRepo.add(addedExplanation);
        return addedExplanation;
    }

    private LexicalItem getLexicalItem(LexicalItemDTO lexicalItemDTO) {
        return lexicalItemRepo.find(lexicalItemDTO)
                .orElseGet(() -> {
                    LexicalItem lexicalItem = new LexicalItem();
                    String language = lexicalItemDTO.getLanguage();
                    lexicalItem.setLanguage(new Locale(language));
                    lexicalItem.setValue(lexicalItemDTO.getValue());
                    return lexicalItem;
        });

    }
}
