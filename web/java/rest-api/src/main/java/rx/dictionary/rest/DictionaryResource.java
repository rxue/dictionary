package rx.dictionary.rest;

import io.github.rxue.dictionary.jpa.entity.Explanation;
import io.github.rxue.dictionary.jpa.entity.LexicalItem;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import io.github.rxue.dictionary.jpa.repository.ExplanationRepository;
import rx.dictionary.rest.dto.ExplanationDTO;
import rx.dictionary.rest.dto.ExplanationsByLexicalItemDTO;
import rx.dictionary.rest.vo.ExplanationVO;
import rx.dictionary.rest.vo.ExplanationsByLexicalItemVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
@Path("glossary")
public class DictionaryResource {
    @Inject
    private EntityManager em;
    private ExplanationRepository explanationRepository;
    @PostConstruct
    public void inject() {
        explanationRepository = new ExplanationRepository(em);
    }
    @GET
    @Path("{language}/{keywordValue}")
    @Produces(MediaType.APPLICATION_JSON)
    public ExplanationsByLexicalItemDTO getExplanationsByLexicalItemDTO(@PathParam("language") Locale language, @PathParam("keywordValue") String keywordValue) {
        System.out.println("DEBUG::DEBUG::DEBUG::DEBUG");
        LexicalItem keyword = new LexicalItem();
        keyword.setLanguage(language);
        keyword.setValue(keywordValue);
        List<Explanation> result = explanationRepository.findLike(keyword, Locale.ENGLISH);
        List<ExplanationDTO> explanationDTOs = result.stream()
                .map(DictionaryResource::toExplanationDTO)
                .toList();

        return new ExplanationsByLexicalItemDTO(result.get(0).getLexicalItem(), explanationDTOs);
    }
    private static ExplanationDTO toExplanationDTO(Explanation explanation) {
        return new ExplanationDTO.Builder()
                .setId(explanation.getId())
                .setExplanationLanguage(explanation.getLanguage().toLanguageTag())
                .setPartOfSpeech(explanation.getPartOfSpeech().toString())
                .setDefinition(explanation.getDefinition())
                .build();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ExplanationsByLexicalItemDTO createExplanationsByLexicalItemDTO(ExplanationsByLexicalItemVO explanations) {
        LexicalItem newLexicalItem = new LexicalItem();
        newLexicalItem.setLanguage(Locale.forLanguageTag(explanations.getLexicalItemLanguage()));
        newLexicalItem.setValue(explanations.getLexicalItemValue());
        System.out.println("Got language Locale of Lexical Item: " + Locale.forLanguageTag(explanations.getLexicalItemLanguage()));
        List<Explanation> newExplanations = new ArrayList<>();
        for (ExplanationVO explanationVO : explanations.getExplanations()) {
            newExplanations.add(explanationVO.toExplanation(newLexicalItem));
        }
        List<Explanation> addedExplanations = explanationRepository.cascadeAdd(newExplanations);
        List<ExplanationDTO> responseExplanationDTOs = addedExplanations.stream()
                .map(DictionaryResource::toExplanationDTO)
                .toList();
        return new ExplanationsByLexicalItemDTO(addedExplanations.get(0).getLexicalItem(), responseExplanationDTOs);
    }

}
