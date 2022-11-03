package rx.dictionary.ui.jsf.update;


import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import rx.dictionary.DictionaryService;
import rx.dictionary.SearchKeyword;
import rx.dictionary.jpaentity.Explanation;
import rx.dictionary.jpaentity.LexicalItem;
import rx.dictionary.jpaentity.PartOfSpeech;
import rx.dictionary.ui.jsf.CommonComponent;
import rx.dictionary.ui.jsf.InputComponent;
import rx.dictionary.ui.jsf.addorupdate.ExplanationDTO;

@Named
@ViewScoped
public class UpdateComponent extends InputComponent implements Serializable {
	@Inject
	private DictionaryService dictionaryService;
	private List<Explanation> explanations = Collections.emptyList();
	private List<ExplanationDTO> explanationDTOs = Collections.emptyList();
	public void setExplanationComponents(List<ExplanationDTO> explanations) {
		this.explanationDTOs = explanations;
	}
	public List<ExplanationDTO> getExplanationDTOs() {
		return explanationDTOs;
	}
	
	public Map<String,String> getPartOfSpeechOptions() {
		PartOfSpeech[] partOfSpeeches = PartOfSpeech.values();
		Map<String,String> result = new HashMap<>();
		for (PartOfSpeech partOfSpeech : partOfSpeeches) {
			result.put(partOfSpeech.name(), partOfSpeech.name());
		}
		return result;
	}

	public Map<String,String> getLanguageNameToTag() {
		return CommonComponent.getLanguageNameToTag();
	}
	public boolean hasExplanations() {
		return !explanationDTOs.isEmpty();
	}
	public void setSearchLanguage(Locale searchLanguage) {
		super.searchLanguage = searchLanguage;
	}
	public Locale getSearchLanguage() {
		return super.searchLanguage;
	}
	
	public void setExplainLanguage(Locale explainLanguage) {
		super.explainLanguage = explainLanguage;
	}
	public Locale getExplainLanguage() {
		return super.explainLanguage;
	}
	
	public void search() {
		SearchKeyword itemVal = new SearchKeyword(super.getWord(), getSearchLanguage());
		explanations = dictionaryService.find(itemVal, getExplainLanguage());
		explanationDTOs = explanations.stream()
				.map(m -> new ExplanationDTO(m.getLexicalItem().getPoS(), m.getExplanation()))
				.collect(Collectors.toList());
	}
	public void update() {
		int i = 0;
		for (ExplanationDTO explanationComp : explanationDTOs) {
			updateExplanation(explanations.get(i++), explanationComp);
		}
		dictionaryService.update(explanations);		
	}
	private static Explanation updateExplanation(Explanation explanationToUpdate, ExplanationDTO explanationDTO) {
		LexicalItem item = explanationToUpdate.getLexicalItem();
		item.setPoS(explanationDTO.getPartOfSpeech());
		explanationToUpdate.setExplanation(explanationDTO.getMeaning());
		return explanationToUpdate;
	}
	
}
