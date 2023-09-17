package rx.dictionary.ui;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import rx.dictionary.jpaentity.Explanation;
import rx.dictionary.jpaentity.PartOfSpeech;
import rx.dictionary.ui.jsf.search.ExplanationItemViewDTO;

public final class Results {
	private final Map<String,List<Explanation>> explanationCandidates;
	public Results() {
		this(Collections.EMPTY_LIST);
	}
	public Results(List<Explanation> explanationCandidates) {
		this.explanationCandidates = Collections.unmodifiableMap(explanationCandidates.stream()
				.collect(Collectors.groupingBy(exp -> exp.getLexicalItem().getItemValue())));
	}
	public boolean isEmpty() {
		return explanationCandidates.isEmpty();
	}
	public List<String> getCandidateWords() {
		return explanationCandidates.keySet().stream().collect(Collectors.toList());
	}
	public List<ExplanationItemViewDTO> getOne() {
		if (isEmpty()) return Collections.EMPTY_LIST;
		String firstWord = getCandidateWords().get(0);
		Map<PartOfSpeech, List<Explanation>> groupedExplanations = explanationCandidates.get(firstWord)
				.stream()
				.collect(Collectors.groupingBy(exp -> exp.getPartOfSpeech()));
		return groupedExplanations.entrySet()
				.stream()
				.map(e -> new ExplanationItemViewDTO(e.getKey(), e.getValue().stream().map(exp -> exp.getLexicalItem().getItemValue()).collect(Collectors.toList())))
				.collect(Collectors.toList());
			
	}
}
