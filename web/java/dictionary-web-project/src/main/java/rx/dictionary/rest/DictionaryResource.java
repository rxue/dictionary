package rx.dictionary.rest;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import rx.dictionary.DictionaryService;
import rx.dictionary.SearchKeyword;
import rx.dictionary.dto.ExplanationByPartOfSpeech;
import rx.dictionary.jpaentity.Explanation;
import rx.dictionary.jpaentity.PartOfSpeech;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Path("vocabulary")
public class DictionaryResource {
	@Inject
	private DictionaryService dictionaryService;
	
	@GET
	@Path("{language}/{word}")
	public List<ExplanationByPartOfSpeech> getExplanations(@PathParam("language") Locale language, @PathParam("word")String word) {
		SearchKeyword searchKeyword = new SearchKeyword(word, language);
		Map<PartOfSpeech, List<Explanation>> grouped = dictionaryService.find(searchKeyword, Locale.SIMPLIFIED_CHINESE)
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