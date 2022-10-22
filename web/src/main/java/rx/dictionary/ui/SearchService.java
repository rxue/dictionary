package rx.dictionary.ui;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import static java.util.stream.Collectors.*;

import javax.inject.Inject;

import rx.dictionary.ExplanationRepository;
import rx.dictionary.SearchKeyword;
import rx.dictionary.jpaentity.Explanation;

public class SearchService implements Serializable {
	@Inject
	private ExplanationRepository explanationRepository;
	public Map<String, SearchResult> searchCandidates(SearchKeyword keyword, Locale explanationLanguage) {
		List<Explanation> allExplanations = explanationRepository.findLike(keyword, explanationLanguage);
		Map<String,List<Explanation>> explanationsByLexicalItem = allExplanations.stream()
				.collect(groupingBy(e -> e.getLexicalItem().getValue()));
		Map<String,SearchResult> result = new HashMap<>();
		for (Map.Entry<String,List<Explanation>> entry : explanationsByLexicalItem.entrySet()) {
			result.put(entry.getKey(), new SearchResult(entry.getValue()));
		}
		return result;
	}
	public SearchResult search(SearchKeyword keyword, Locale explanationLanguage) {
		List<Explanation> allExplanations = explanationRepository.find(keyword, explanationLanguage);
		return new SearchResult(allExplanations);
	}
}
