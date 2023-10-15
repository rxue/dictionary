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
import rx.dictionary.rest.dto.ExplanationDTO;
import rx.dictionary.rest.dto.ExplanationsDTO;
import rx.dictionary.rest.dto.LexicalItemDTO;
import rx.dictionary.rest.service.VocabulariesService;
import rx.dictionary.vo.LexicalItemVO;

import static java.util.stream.Collectors.*;

@Path("vocabularies")
public class VocabulariesResource {
	@Inject
	private DictionaryService dictionaryService;
	@Inject
	private VocabulariesService vocabulariesService;
	
	@GET
	@Path("{language}/{word}")
	@Produces(MediaType.APPLICATION_JSON)
	public ExplanationsDTO getExplanations(@PathParam("language") Locale language, @PathParam("word")String word,
												 @MatrixParam("explanation_language") Locale explanationLanguagelocale,
												 @HeaderParam(HttpHeaders.ACCEPT_LANGUAGE) List<Locale> acceptLanguages) {
		LexicalItemVO lexicalItemVO = new LexicalItemVO(word, language);
		final Locale explanationLanguage = new ExplanationLanguageResolver.Builder()
				.setExplanationLanguage(explanationLanguagelocale)
				.setAcceptLanguages(acceptLanguages)
				.setSearchKeywordLanguage(language)
				.build()
				.resolve();

		List<ExplanationDTO> explanationDTOs = dictionaryService.find(lexicalItemVO, explanationLanguage)
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
		result.setExplanationLanguage(explanationLanguagelocale.toLanguageTag());
		result.setExplanations(explanationDTOs);
		return result;
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