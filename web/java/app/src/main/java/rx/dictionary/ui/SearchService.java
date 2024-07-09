package rx.dictionary.ui;

import java.io.Serializable;
import java.util.*;

import jakarta.inject.Inject;

import rx.dictionary.vo.LexicalItemVO;
import rx.dictionary.jpa.entity.Explanation;

public class SearchService implements Serializable {
	/*
	@Inject
	private ExplanationRepository explanationRepository;
	public Map<String, SearchResult> searchCandidates(LexicalItemVO keyword, Locale explanationLanguage) {
		return Collections.EMPTY_MAP;
	}
	public SearchResult search(LexicalItemVO keyword, Locale explanationLanguage) {
		List<Explanation> allExplanations = explanationRepository.find(keyword, explanationLanguage);
		return new SearchResult(allExplanations);
	}
	*/
}
