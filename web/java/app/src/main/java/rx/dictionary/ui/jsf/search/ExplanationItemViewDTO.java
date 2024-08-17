package rx.dictionary.ui.jsf.search;

import io.github.rxue.dictionary.jpa.entity.PartOfSpeech;

import java.util.List;


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
