package rx.dictionary.rest;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import rx.dictionary.DictionaryService;
import rx.dictionary.vo.LexicalItemVO;
import rx.dictionary.dto.ExplanationByPartOfSpeechDTO;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.PartOfSpeech;

import static java.util.stream.Collectors.*;

@Path("vocabularies")
public class VocabulariesResource {
	@Inject
	private DictionaryService dictionaryService;
	
	@GET
	@Path("{language}/{word}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ExplanationByPartOfSpeechDTO> getExplanations(@PathParam("language") Locale language, @PathParam("word")String word,
															  @MatrixParam("explanation_language") Locale explanationLanguageQueryParam,
															  @HeaderParam(HttpHeaders.ACCEPT_LANGUAGE) List<Locale> acceptLanguages) {
		LexicalItemVO lexicalItemVO = new LexicalItemVO(word, language);
		final Locale explanationLanguage = new ExplanationLanguageResolver.Builder()
				.setExplanationLanguage(explanationLanguageQueryParam)
				.setAcceptLanguages(acceptLanguages)
				.setSearchKeywordLanguage(language)
				.build()
				.resolve();
		Map<PartOfSpeech, List<Explanation>> grouped = dictionaryService.find(lexicalItemVO, explanationLanguage)
				.stream()
				.collect(groupingBy(Explanation::getPartOfSpeech));
		return grouped.entrySet().stream()
				.map(e -> {
					ExplanationByPartOfSpeechDTO result = new ExplanationByPartOfSpeechDTO();
					result.setPartOfSpeech(e.getKey().toString());
					result.setExplanations(e.getValue().stream().map(Explanation::getExplanation).collect(toList()));
					return result;
				})
				.collect(toList());
	}
}