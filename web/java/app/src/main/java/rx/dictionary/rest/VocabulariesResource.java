package rx.dictionary.rest;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import rx.dictionary.DictionaryService;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.rest.dto.ExplanationDTO;
import rx.dictionary.rest.dto.ExplanationsDTO;
import rx.dictionary.rest.dto.LexicalItemDTO;
import rx.dictionary.vo.LexicalItemVO;

import static java.util.stream.Collectors.*;

@Path("vocabularies")
public class VocabulariesResource {
	@Inject
	private VocabulariesService vocabulariesService;
	
	@GET
	@Path("{language}/{word}")
	@Produces(MediaType.APPLICATION_JSON)
	public ExplanationsDTO getExplanations(@PathParam("language") Locale language, @PathParam("word")String word,
												 @MatrixParam("explanation_language") Locale explanationLanguagelocale,
												 @HeaderParam(HttpHeaders.ACCEPT_LANGUAGE) List<Locale> acceptLanguages) {
		final LexicalItemVO lexicalItemVO = new LexicalItemVO(language, word);
		final ExplanationLanguageResolver explanationLanguageResolver = new ExplanationLanguageResolver.Builder()
				.setExplanationLanguage(explanationLanguagelocale)
				.setAcceptLanguages(acceptLanguages)
				.setSearchKeywordLanguage(language)
				.build();
		return vocabulariesService.find(lexicalItemVO, explanationLanguageResolver.resolve());
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(ExplanationsDTO explanationsDTO) {
		ExplanationsDTO createdExplanations = vocabulariesService.addExplanations(explanationsDTO);
		Function<ExplanationsDTO,URI> toURI = d -> {
			return URI.create("x");
		};
		return Response.created(toURI.apply(createdExplanations))
				.entity(createdExplanations)
				.build();
	}
}