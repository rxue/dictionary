package rx.dictionary.ui.jsf.addorupdate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import rx.dictionary.jpaentity.PartOfSpeech;

abstract class AbstractFillInComponent {
	protected List<ExplanationComponent> explanations;
	public void setExplanations(List<ExplanationComponent> explanations) {
		this.explanations = explanations;
	}

	public List<ExplanationComponent> getExplanations() {
		System.out.println("explaination : " + explanations);
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