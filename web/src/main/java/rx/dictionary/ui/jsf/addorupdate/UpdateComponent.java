package rx.dictionary.ui.jsf.addorupdate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import rx.dictionary.DefinitionRepository;
import rx.dictionary.jpaentity.Definition;
import rx.dictionary.jpaentity.EntryValue;
import rx.dictionary.jpaentity.PartOfSpeech;
import rx.dictionary.ui.jsf.SearchComponent;
@RequestScoped
@Named
public class UpdateComponent {
	@Inject
	private SearchComponent searchComponent;
	@Inject
	private DefinitionRepository repo;
	private List<ExplanationComponent> explanations;
	public void search() {
		EntryValue entryVal = new EntryValue();
		entryVal.setLanguage(searchComponent.getFromLanguage());
		entryVal.setEntry(searchComponent.getKeyword());
		List<Definition> definitions = repo.find(entryVal, searchComponent.getToLanguage());
		explanations = new ArrayList<>();
		for (Definition definition : definitions) {
			ExplanationComponent expComp = new ExplanationComponent(true);
			expComp.setPartOfSpeech(definition.getEntry().getPoS());
			expComp.setExplanation(definition.getDefinition());
			explanations.add(expComp);
		}
	}
	public List<ExplanationComponent> getExplanations() {
		return explanations;
	}
	public void setExplanations(List<ExplanationComponent> explanations) {
		this.explanations = explanations;
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
