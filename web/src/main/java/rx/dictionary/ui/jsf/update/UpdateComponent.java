package rx.dictionary.ui.jsf.update;


import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import rx.dictionary.DictionaryService;
import rx.dictionary.SearchKeyword;
import rx.dictionary.jpaentity.Explanation;
import rx.dictionary.jpaentity.LexicalItem;
import rx.dictionary.ui.jsf.addorupdate.AddOrUpdateInputComponent;
import rx.dictionary.ui.jsf.addorupdate.ExplanationDTO;

@Named
@ViewScoped
public class UpdateComponent extends AddOrUpdateInputComponent implements Serializable {
	@Inject
	private DictionaryService dictionaryService;
	private List<Explanation> explanations = Collections.emptyList();
	private List<ExplanationDTO> explanationDTOs = Collections.emptyList();
	public void setExplanationComponents(List<ExplanationDTO> explanations) {
		this.explanationDTOs = explanations;
	}
	public void search() {
		SearchKeyword itemVal = new SearchKeyword(super.getWord(), getLanguage());
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
	
	public List<ExplanationDTO> getExplanationDTOs() {
		return explanationDTOs;
	}

	public boolean hasExplanations() {
		return !explanationDTOs.isEmpty();
	}
}
