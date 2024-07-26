package rx.dictionary.rest;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.repository.ExplanationRepository;
import rx.dictionary.rest.vo.ExplanationsByLanguage;
import rx.dictionary.vo.LexicalItem;

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
    @Path("{language}")
    @Produces(MediaType.APPLICATION_JSON)
    public ExplanationsByLanguage getDictionaryEntry(@PathParam("language") Locale language) {
        System.out.println("DEBUG::DEBUG::DEBUG::DEBUG");
        LexicalItem keyword = new LexicalItem() {
            @Override
            public Locale getLanguage() {
                return Locale.ENGLISH;
            }

            @Override
            public String getValue() {
                return "dry";
            }
        };
        List<Explanation> explanations = explanationRepository.findLike(keyword, Locale.ENGLISH);

        ExplanationsByLanguage.Builder outputBuilder = new ExplanationsByLanguage.Builder(1L, keyword.getLanguage(), keyword.getValue())
                .setExplanationLanguage(Locale.ENGLISH);
        for (Explanation exp : explanations) {
            outputBuilder.addDefinition(exp.getPartOfSpeech(), 1L, exp.getDefinition());
        }

        return outputBuilder.build();
    }
}
