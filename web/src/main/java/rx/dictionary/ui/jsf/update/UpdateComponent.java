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

import rx.dictionary.DefinitionRepository;
import rx.dictionary.jpaentity.Definition;
import rx.dictionary.jpaentity.Entry;
import rx.dictionary.jpaentity.EntryValue;
import rx.dictionary.jpaentity.PartOfSpeech;
import rx.dictionary.ui.jsf.InputComponent;
import rx.dictionary.ui.jsf.addorupdate.ExplanationComponent;

@Named
@ViewScoped
public class UpdateComponent extends InputComponent implements Serializable {
	@Inject
	private DefinitionRepository definitionRepo;
	private List<Definition> definitions = Collections.emptyList();
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
		EntryValue entryVal = new EntryValue();
		entryVal.setLanguage(getFromLanguage());
		entryVal.setEntry(getWord());
		definitions = definitionRepo.find(entryVal, getToLanguage());
		explanations = toExplanationComponents(definitions);
	}
	
	private static List<ExplanationComponent> toExplanationComponents(List<Definition> definitions) {
		int i = 0;
		List<ExplanationComponent> explanations = new ArrayList<>();
		for(Definition definition : definitions) {
			ExplanationComponent newExplanation;
			if (i++ == 0) {
				newExplanation = new ExplanationComponent(true);
			} else newExplanation = new ExplanationComponent(false);
			newExplanation.setPartOfSpeech(definition.getEntry().getPoS());
			newExplanation.setExplanation(definition.getDefinition());
			explanations.add(newExplanation);
		}
		return explanations;
	}
	public void update() {
		int i = 0;
		for (ExplanationComponent explanationComp : explanations) {
			Definition defToUpdate = definitions.get(i++);
			Entry entry = defToUpdate.getEntry();
			entry.setPoS(explanationComp.getPartOfSpeech());
			defToUpdate.setDefinition(explanationComp.getExplanation());
		}
		definitionRepo.update(definitions);		
	}
}
