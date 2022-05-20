package rx.dictionary.ui.jsf.addorupdate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;


import rx.dictionary.jpaentity.PartOfSpeech;

abstract class AbstractFillInComponent {
	protected List<ExplanationComponent> explanations;
	protected AbstractFillInComponent() {
		explanations = new ArrayList<>();
		explanations.add(new ExplanationComponent(true));
		IntStream.range(0, 5).forEach(e -> explanations.add(new ExplanationComponent()));
	}
	public void setExplanations(List<ExplanationComponent> explanations) {
		this.explanations = explanations;
	}

	public List<ExplanationComponent> getExplanations() {
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