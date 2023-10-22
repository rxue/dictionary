package rx.dictionary.rest;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import rx.dictionary.rest.dto.ExplanationsDTO;
import rx.dictionary.rest.dto.LexicalItemDTO;
import rx.dictionary.vo.LexicalItemVO;

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
		return vocabulariesService.findExplanations(lexicalItemVO, explanationLanguageResolver.resolve());
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(ExplanationsDTO explanationsDTO) {
		ExplanationsDTO createdExplanations = vocabulariesService.addExplanations(explanationsDTO);
		return Response.created(getURI(createdExplanations))
				.entity(createdExplanations)
				.build();
	}
	static URI getURI(ExplanationsDTO dto) {
		final LexicalItemDTO lexicalItem = dto.getLexicalItem();
		String lexicalItemValue = null;
		try {
			lexicalItemValue = URLEncoder.encode(lexicalItem.getValue(), StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			throw new WebApplicationException("UTF_8 has to be supported on server");
		}
		List<String> pathElements = List.of("vocabularies",
					lexicalItem.getLanguage() + ";explanation_language=" + dto.getExplanationLanguage(),
					lexicalItemValue);
		return URI.create(pathElements.stream().collect(Collectors.joining("/")));
	}
}