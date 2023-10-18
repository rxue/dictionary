package rx.dictionary.rest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import rx.dictionary.ExplanationRepository;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.LexicalItem;
import rx.dictionary.jpa.entity.PartOfSpeech;
import rx.dictionary.rest.dto.ExplanationDTO;
import rx.dictionary.rest.dto.ExplanationsDTO;
import rx.dictionary.rest.dto.LexicalItemDTO;
import rx.dictionary.vo.LexicalItemVO;

import java.util.List;
import java.util.Locale;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class VocabulariesService {
    @Inject
    private ExplanationRepository explanationRepo;
    @Transactional
    public ExplanationsDTO addExplanations(ExplanationsDTO explanationsDTO) {
        Function<LexicalItemDTO, LexicalItem> toLexicalItem = d -> {
            LexicalItem lexicalItem = new LexicalItem();
            lexicalItem.setLanguage(Locale.forLanguageTag(d.getLanguage()));
            lexicalItem.setValue(d.getValue());
            return lexicalItem;
        };
        LexicalItem lexicalItem = toLexicalItem.apply(explanationsDTO.getLexicalItem());
        Locale explanationLanguage = Locale.forLanguageTag(explanationsDTO.getExplanationLanguage());
        List<Explanation> explanationsToAdd = explanationsDTO.getExplanations()
                .stream()
                .map(e -> {
                    Explanation result = new Explanation();
                    result.setLexicalItem(lexicalItem);
                    result.setLanguage(explanationLanguage);
                    result.setPartOfSpeech(PartOfSpeech.valueOf(e.getPartOfSpeech()));
                    result.setExplanation(e.getExplanation());
                    return result;
                })
                .collect(toList());
        explanationRepo.add(explanationsToAdd);
        return explanationsDTO;
    }
    @Transactional
    public ExplanationsDTO find(LexicalItemVO lexicalItemVO, Locale explanationLanguage) {
        List<Explanation> explanations = explanationRepo.find(lexicalItemVO, explanationLanguage);
        List<ExplanationDTO> explanationDTOs = explanations
                .stream()
                .map(e -> {
                    ExplanationDTO result = new ExplanationDTO();
                    result.setPartOfSpeech(e.getPartOfSpeech().toString());
                    result.setExplanation(e.getExplanation());
                    return result;
                })
                .collect(toList());
        Function<LexicalItemVO,LexicalItemDTO> toLexicalItemDTO = v -> {
            LexicalItemDTO result = new LexicalItemDTO();
            result.setLanguage(v.language().toLanguageTag());
            result.setValue(v.value());
            return result;
        };
        ExplanationsDTO result = new ExplanationsDTO();
        result.setLexicalItem(toLexicalItemDTO.apply(lexicalItemVO));
        result.setExplanationLanguage(explanationLanguage.toLanguageTag());
        result.setExplanations(explanationDTOs);
        return result;
    }

}
