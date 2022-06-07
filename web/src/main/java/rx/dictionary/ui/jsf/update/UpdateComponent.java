package rx.dictionary.ui.jsf.update;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import rx.dictionary.DictionaryService;
import rx.dictionary.jpaentity.Explanation;
import rx.dictionary.jpaentity.ItemValue;
import rx.dictionary.jpaentity.LexicalItem;
import rx.dictionary.jpaentity.PartOfSpeech;
import rx.dictionary.ui.jsf.InputComponent;
import rx.dictionary.ui.jsf.addorupdate.ExplanationComponent;

@Named
@ViewScoped
public class UpdateComponent extends InputComponent implements Serializable {
	@Inject
	private DictionaryService dictionaryService;
	private List<Explanation> definitions = Collections.emptyList();
	private List<ExplanationComponent> explanations = Collections.emptyList();
	public void setExplanationComponents(List<ExplanationComponent> explanations) {
		this.explanations = explanations;
	}
	public List<ExplanationComponent> getExplanationComponents() {
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
	public boolean hasExplanations() {
		return !explanations.isEmpty();
	}
	
	public void search() {
		System.out.println("Search action at JSF phace: " + FacesContext.getCurrentInstance().getCurrentPhaseId());
		ItemValue itemVal = new ItemValue();
		itemVal.setLanguage(getFromLanguage());
		itemVal.setValue(getWord());
		definitions = dictionaryService.find(itemVal, getToLanguage());
		explanations = toExplanationComponents(definitions);
	}
	
	private static List<ExplanationComponent> toExplanationComponents(List<Explanation> explanations) {
		int i = 0;
		List<ExplanationComponent> explanationComponents = new ArrayList<>();
		for(Explanation explanation : explanations) {
			ExplanationComponent newExplanation;
			if (i++ == 0) {
				newExplanation = new ExplanationComponent(true);
			} else newExplanation = new ExplanationComponent(false);
			newExplanation.setPartOfSpeech(explanation.getLexicalItem().getPoS());
			newExplanation.setExplanation(explanation.getExplanation());
			explanationComponents.add(newExplanation);
		}
		return explanationComponents;
	}
	public void update() {
		int i = 0;
		for (ExplanationComponent explanationComp : explanations) {
			Explanation explanationToUpdate = definitions.get(i++);
			LexicalItem item = explanationToUpdate.getLexicalItem();
			item.setPoS(explanationComp.getPartOfSpeech());
			explanationToUpdate.setExplanation(explanationComp.getExplanation());
		}
		dictionaryService.update(definitions);		
	}
}
