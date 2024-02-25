package rx.dictionary.ui;

import java.io.Serializable;
import java.util.*;

import static java.util.stream.Collectors.*;

import jakarta.inject.Inject;

import rx.dictionary.ExplanationRepository;
import rx.dictionary.vo.LexicalItemVO;
import rx.dictionary.jpa.entity.Explanation;

public class SearchService implements Serializable {
	@Inject
	private ExplanationRepository explanationRepository;
	public Map<String, SearchResult> searchCandidates(LexicalItemVO keyword, Locale explanationLanguage) {
		/*
		List<Explanation> allExplanations = explanationRepository.findLike(keyword, explanationLanguage);
		Map<String,List<Explanation>> explanationsByLexicalItem = allExplanations.stream()
				.collect(groupingBy(e -> e.getLexicalItem().getValue()));
		Map<String,SearchResult> result = new HashMap<>();
		for (Map.Entry<String,List<Explanation>> entry : explanationsByLexicalItem.entrySet()) {
			result.put(entry.getKey(), new SearchResult(entry.getValue()));
		}
		return result;
		 */
		return Collections.EMPTY_MAP;
	}
	public SearchResult search(LexicalItemVO keyword, Locale explanationLanguage) {
		List<Explanation> allExplanations = explanationRepository.find(keyword, explanationLanguage);
		return new SearchResult(allExplanations);
	}
}
