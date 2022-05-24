package rx.dictionary.ui.jsf.search;

import java.util.List;

import rx.dictionary.jpaentity.PartOfSpeech;

public class DefinitionItemViewDTO {
	private final PartOfSpeech partOfSpeech;
	private final List<String> definitions;
	public DefinitionItemViewDTO(PartOfSpeech partOfSpeech, List<String> definitions) {
		this.partOfSpeech = partOfSpeech;
		this.definitions = definitions;
	}
	@Override
	public String toString() {
		return "(" + partOfSpeech + ")" + String.join(", ", definitions);
	}
	
}
