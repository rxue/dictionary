package rx.dictionary.ui;

import java.util.List;
import static java.util.stream.Collectors.*;

import java.util.AbstractList;

import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.ui.jsf.search.ExplanationItemViewDTO;

public final class SearchResult extends AbstractList<ExplanationItemViewDTO> {
	private final List<ExplanationItemViewDTO> explanationItemViewDTOs;
	public SearchResult(List<Explanation> explanations) {
		explanationItemViewDTOs = explanations.stream()
				.collect(groupingBy(exp -> exp.getPartOfSpeech()))
				.entrySet()
				.stream()
				.map(e -> new ExplanationItemViewDTO(e.getKey(), toExplanationTexts(e.getValue())))
				.collect(toList());
	}
	private static List<String> toExplanationTexts(List<Explanation> explanations) {
		return explanations.stream()
				.map(Explanation::getExplanation)
				.collect(toList());
	}
	@Override
	public ExplanationItemViewDTO get(int index) {
		return explanationItemViewDTOs.get(index);
	}
	@Override
	public int size() {
		return explanationItemViewDTOs.size();
	}
}
