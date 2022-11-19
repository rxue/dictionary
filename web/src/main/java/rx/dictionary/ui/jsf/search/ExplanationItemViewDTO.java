package rx.dictionary.ui.jsf.search;

import java.util.List;

import rx.dictionary.jpaentity.PartOfSpeech;

public class ExplanationItemViewDTO {
	private final PartOfSpeech partOfSpeech;
	private final List<String> definitions;
	public ExplanationItemViewDTO(PartOfSpeech partOfSpeech, List<String> explanations) {
		this.partOfSpeech = partOfSpeech;
		this.definitions = explanations;
	}
	@Override
	public String toString() {
		return "(" + partOfSpeech + ")" + String.join(", ", definitions);
	}
	
}
