package rx.dictionary.ui.jsf.update;


import java.io.Serializable;
import java.util.Collections;
import java.util.List;
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
	public void search() {
		SearchKeyword itemVal = new SearchKeyword(super.getWord(), getLanguage());
		explanations = dictionaryService.find(itemVal, getExplainLanguage());
		explanations.forEach(m -> explanationDTOs.add(new ExplanationDTO(m.getPartOfSpeech(), m.getExplanation())));
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
		explanationToUpdate.setExplanation(explanationDTO.getMeaning());
		return explanationToUpdate;
	}

	public boolean hasExplanations() {
		return !explanationDTOs.isEmpty();
	}
}
