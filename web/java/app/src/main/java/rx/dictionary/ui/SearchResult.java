package rx.dictionary.ui;

public final class SearchResult {
	/*
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
	}*/
}
