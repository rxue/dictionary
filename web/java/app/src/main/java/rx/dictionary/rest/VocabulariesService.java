package rx.dictionary.rest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import rx.dictionary.ExplanationRepository;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.PartOfSpeech;
import rx.dictionary.rest.dto.LegacyExplanationDTO;
import rx.dictionary.rest.dto.LexicalItemWithLegacyExplanationsDTO;
import rx.dictionary.rest.dto.LegacyLexicalItemDTO;
import rx.dictionary.rest.vo.ExplanationUnitID;
import rx.dictionary.vo.LexicalItemVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.util.stream.Collectors.toList;

public class VocabulariesService {
    @Inject
    private ExplanationRepository explanationRepo;
    @Transactional
    public LexicalItemWithLegacyExplanationsDTO addExplanations(LexicalItemWithLegacyExplanationsDTO lexicalItemWithExplanationsDTO) {
        /*
        Function<LexicalItemDTO, LexicalItem> toLexicalItem = d -> {
            LexicalItem lexicalItem = new LexicalItem();
            lexicalItem.setLanguage(Locale.forLanguageTag(d.getLanguage()));
            lexicalItem.setValue(d.getValue());
            return lexicalItem;
        };
        LexicalItem lexicalItem = toLexicalItem.apply(lexicalItemWithExplanationsDTO.getLexicalItem());
        Locale explanationLanguage = Locale.forLanguageTag(lexicalItemWithExplanationsDTO.getExplanationLanguage());
        List<Explanation> explanationsToAdd = lexicalItemWithExplanationsDTO.getExplanations()
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
        return lexicalItemWithExplanationsDTO;*/
        return null;
    }
    @Transactional
    public LexicalItemWithLegacyExplanationsDTO findExplanations(ExplanationUnitID explanationUnitID) {
        LexicalItemVO lexicalItemVO = explanationUnitID.lexicalItem();
        Locale explanationLanguage = explanationUnitID.explanationLanguage();
        List<Explanation> explanations = explanationRepo.find(lexicalItemVO, explanationLanguage);
        LexicalItemWithLegacyExplanationsDTO result = new LexicalItemWithLegacyExplanationsDTO();
        result.setLexicalItem(DataTransformationUtil.toLexicalItemDTO(lexicalItemVO));
        result.setExplanationLanguage(explanationLanguage.toLanguageTag());
        result.setExplanations(DataTransformationUtil.toExplanationDTOs(explanations));
        return result;
    }

    @Transactional
    public LexicalItemWithLegacyExplanationsDTO update(ExplanationUnitID explanationUnitID, List<LegacyExplanationDTO> newExplanations) {
        List<Explanation> explanations = explanationRepo.find(explanationUnitID.lexicalItem(), explanationUnitID.explanationLanguage());
        List<Explanation> updatedExplanations = combine(explanations, newExplanations);
        explanationRepo.update(updatedExplanations);
        LexicalItemWithLegacyExplanationsDTO result = new LexicalItemWithLegacyExplanationsDTO();
        result.setLexicalItem(DataTransformationUtil.toLexicalItemDTO(explanationUnitID.lexicalItem()));
        result.setExplanationLanguage(explanationUnitID.explanationLanguage().toLanguageTag());
        result.setExplanations(DataTransformationUtil.toExplanationDTOs(updatedExplanations));
        return result;
    }
    static List<Explanation> combine(List<Explanation> explanations, List<LegacyExplanationDTO> newExplanations) {
        List<Explanation> result = new ArrayList<>();
        for (int i = 0; i < explanations.size(); i++) {
            Explanation currentExplanation = explanations.get(i);
            update(currentExplanation, newExplanations.get(i));
            result.add(currentExplanation);
        }
        return result;
    }
    private static void update(Explanation explanation, LegacyExplanationDTO newExplanation) {
        explanation.setPartOfSpeech(PartOfSpeech.valueOf(newExplanation.getPartOfSpeech()));
        explanation.setExplanation(newExplanation.getExplanation());
    }

    private static class DataTransformationUtil {
        static LegacyLexicalItemDTO toLexicalItemDTO(LexicalItemVO lexicalItemVO) {
            LegacyLexicalItemDTO result = new LegacyLexicalItemDTO();
            result.setLanguage(lexicalItemVO.language().toLanguageTag());
            result.setValue(lexicalItemVO.value());
            return result;
        }
        static List<LegacyExplanationDTO> toExplanationDTOs(List<Explanation> explanations) {
            return explanations
                    .stream()
                    .map(e -> {
                        LegacyExplanationDTO result = new LegacyExplanationDTO();
                        result.setPartOfSpeech(e.getPartOfSpeech().toString());
                        result.setExplanation(e.getExplanation());
                        result.setSentences(e.getSentences());
                        return result;
                    })
                    .collect(toList());
        }
    }
}
