package rx.dictionary.rest;

import io.github.rxue.dictionary.Keyword;
import io.github.rxue.dictionary.jpa.entity.Explanation;
import io.github.rxue.dictionary.jpa.entity.LexicalItem;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import io.github.rxue.dictionary.jpa.repository.ExplanationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rx.dictionary.rest.dto.ExplanationsByLexicalItemDTO;
import rx.dictionary.rest.dto.LexicalItemDTO;
import rx.dictionary.rest.vo.ExplanationVO;
import rx.dictionary.rest.dto.NewExplanationsByLexicalItemInputDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
@Path("glossary")
public class ExplanationsResource {
    private static final Logger LOGGER = LogManager.getLogger(ExplanationsResource.class);
    @Inject
    private EntityManager em;
    private ExplanationRepository explanationRepository;
    @Inject
    private ExplanationService explanationService;
    @PostConstruct
    public void inject() {
        //explanationRepository = new ExplanationRepository(em);
    }

    @GET
    @Path("{language}/{keywordValue}")
    @Produces(MediaType.APPLICATION_JSON)
    public ExplanationsByLexicalItemDTO getExplanationsByLexicalItemDTO(@PathParam("language") Locale language, @PathParam("keywordValue") String keywordValue) {
        LexicalItem keyword = new LexicalItem();
        keyword.setLanguage(language);
        keyword.setValue(keywordValue);
        List<Explanation> result = explanationRepository.findLike(keyword, Locale.ENGLISH);
        return ExplanationsByLexicalItemDTO.create(result);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ExplanationsByLexicalItemDTO createExplanationsByLexicalItemDTO(@Valid NewExplanationsByLexicalItemInputDTO explanationsByLexicalItem) {
        final LexicalItemDTO lexicalItemDTO = explanationsByLexicalItem.getLexicalItemDTO();
        LexicalItem lexicalItem = lexicalItemDTO.toLexicalItem();
        LOGGER.info("new lexical item ID is `{}`", lexicalItem.getId());
        LOGGER.info("new lexical item locale is `{}`", lexicalItem.getLanguage());
        LOGGER.info("new lexical item value is `{}`", lexicalItem.getValue());
        List<Explanation> newExplanations = new ArrayList<>();
        for (ExplanationVO explanationVO : explanationsByLexicalItem.getExplanations()) {
            newExplanations.add(explanationVO.toExplanation(lexicalItem));
        }
        List<Explanation> addedExplanations = explanationService.create(newExplanations);
        LexicalItem persistedLexicalitem = addedExplanations.get(0).getLexicalItem();
        LOGGER.info("Persisted lexical item has id {}", persistedLexicalitem.getId());
        //List<Explanation> foundAddedExplanations = explanationRepository.findLike(lexicalItem, Locale.ENGLISH);
        return ExplanationsByLexicalItemDTO.create(addedExplanations);
    }

}
