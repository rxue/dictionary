package rx.dictionary.ui.jsf.addorupdate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import javax.inject.Inject;

import rx.dictionary.ExplanationRepository;
import rx.dictionary.jpaentity.PartOfSpeech;
import rx.dictionary.ui.jsf.InputComponent;

abstract class CompleteInputComponent extends InputComponent {
	private List<ExplanationDTO> explanations;
	@Inject
	ExplanationRepository definitionRepo;
	protected CompleteInputComponent() {
		explanations = new ArrayList<>();
		//explanations.add(new ExplanationDTO());
		//IntStream.range(0, 5).forEach(e -> explanations.add(new ExplanationDTO()));
	}
	public void setExplanations(List<ExplanationDTO> explanations) {
		this.explanations = explanations;
	}

	public List<ExplanationDTO> getExplanations() {
		return explanations;
	}
	
	public Map<String,String> getPartOfSpeechOptions() {
		PartOfSpeech[] partOfSpeeches = PartOfSpeech.values();
		Map<String,String> result = new HashMap<>();
		for (PartOfSpeech partOfSpeech : partOfSpeeches) {
			result.put(partOfSpeech.name(), partOfSpeech.name());
		}
		return result;
	}
}