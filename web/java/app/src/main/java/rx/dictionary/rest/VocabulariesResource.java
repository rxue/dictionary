package rx.dictionary.rest;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.HttpHeaders;
import rx.dictionary.DictionaryService;
import rx.dictionary.vo.LexicalItemVO;
import rx.dictionary.dto.ExplanationByPartOfSpeech;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.jpa.entity.PartOfSpeech;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Path("vocabularies")
public class VocabulariesResource {
	@Inject
	private DictionaryService dictionaryService;
	
	@GET
	@Path("{language}/{word}")
	public List<ExplanationByPartOfSpeech> getExplanations(@PathParam("language") Locale language, @PathParam("word")String word,
														   @QueryParam("explain_in_language") Locale explanationLanguageQueryParam,
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
		Function<List<Explanation>, List<String>> toExplanationStrings = explanations -> explanations.stream()
				.map(Explanation::getExplanation)
				.collect(toList());
		return grouped.entrySet().stream()
				.map(e -> new ExplanationByPartOfSpeech(e.getKey(), toExplanationStrings.apply(e.getValue())))
				.collect(toList());
	}
}