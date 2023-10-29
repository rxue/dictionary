package rx.dictionary.rest;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import rx.dictionary.rest.dto.ExplanationsDTO;
import rx.dictionary.rest.dto.LexicalItemWithExplanationsDTO;
import rx.dictionary.rest.dto.LexicalItemDTO;
import rx.dictionary.rest.vo.ExplanationUnitID;

@Path("vocabularies")
public class VocabulariesResource {
	@Inject
	private VocabulariesService vocabulariesService;
	
	@GET
	@Path("{language}/{word}")
	@Produces(MediaType.APPLICATION_JSON)
	public LexicalItemWithExplanationsDTO getExplanations(@PathParam("language") Locale language, @PathParam("word")String word,
														  @MatrixParam("explanation_language") Locale explanationLanguagelocale,
														  @HeaderParam(HttpHeaders.ACCEPT_LANGUAGE) List<Locale> acceptLanguages) {
		final ExplanationLanguageResolver explanationLanguageResolver = new ExplanationLanguageResolver.Builder()
				.setExplanationLanguage(explanationLanguagelocale)
				.setAcceptLanguages(acceptLanguages)
				.setSearchKeywordLanguage(language)
				.build();
		return vocabulariesService.findExplanations(new ExplanationUnitID(language, word, explanationLanguageResolver.resolve()));
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(LexicalItemWithExplanationsDTO lexicalItemWithExplanationsDTO) {
		LexicalItemWithExplanationsDTO createdExplanations = vocabulariesService.addExplanations(lexicalItemWithExplanationsDTO);
		return Response.created(getURI(createdExplanations))
				.entity(createdExplanations)
				.build();
	}

	@PUT
	@Path("{language}/{word}")
	@Produces(MediaType.APPLICATION_JSON)
	public LexicalItemWithExplanationsDTO update(@PathParam("language") Locale language,
												 @PathParam("word")String word,
												 @MatrixParam("explanation_language") Locale explanationLanguage,
												 ExplanationsDTO explanationsDTO) {
		return vocabulariesService.update(new ExplanationUnitID(language, word, explanationLanguage), explanationsDTO.getExplanations());
	}
	static URI getURI(LexicalItemWithExplanationsDTO dto) {
		final LexicalItemDTO lexicalItem = dto.getLexicalItem();
		List<String> pathElements = List.of("vocabularies",
					lexicalItem.getLanguage() + ";explanation_language=" + dto.getExplanationLanguage(),
					lexicalItem.getValue().replace(" ", "%20"));
		return URI.create(pathElements.stream().collect(Collectors.joining("/")));
	}
}