package rx.dictionary.ui.jsf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import rx.dictionary.PartOfSpeech;

@RequestScoped
@Named
public class Add {
	private List<String> explanations;
	public List<String> getExplanations() {
		return explanations;
	}

	public void setExplanations(List<String> explanations) {
		this.explanations = explanations;
	}
	
	public Map<String,String> getPartOfSpeech() {
		PartOfSpeech[] partOfSpeeches = PartOfSpeech.values();
		Map<String,String> result = new HashMap<>();
		for (PartOfSpeech partOfSpeech : partOfSpeeches) {
			result.put(partOfSpeech.name(), partOfSpeech.name());
		}
		return result;
	}
}