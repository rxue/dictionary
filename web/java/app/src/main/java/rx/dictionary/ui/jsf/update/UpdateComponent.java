package rx.dictionary.ui.jsf.update;


import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import rx.dictionary.DictionaryService;
import rx.dictionary.vo.LexicalItemVO;
import rx.dictionary.jpa.entity.Explanation;
import rx.dictionary.ui.jsf.addorupdate.AddOrUpdateInputComponent;
import rx.dictionary.ui.jsf.addorupdate.JSFExplanationDTO;

@Named
@ViewScoped
public class UpdateComponent extends AddOrUpdateInputComponent implements Serializable {
	@Inject
	private DictionaryService dictionaryService;
	private List<Explanation> explanations = Collections.emptyList();
	public void search() {
		LexicalItemVO itemVal = new LexicalItemVO(super.getWord(), getLanguage());
		explanations = dictionaryService.find(itemVal, getExplainLanguage());
		explanations.forEach(m -> explanationDTOs.add(new JSFExplanationDTO(m.getPartOfSpeech(), m.getExplanation())));
	}
	public void update() {
		int i = 0;
		for (JSFExplanationDTO explanationComp : explanationDTOs) {
			updateExplanation(explanations.get(i++), explanationComp);
		}
		dictionaryService.update(explanations);
	}
	static Explanation updateExplanation(Explanation explanationToUpdate, JSFExplanationDTO JSFExplanationDTO) {
		explanationToUpdate.setPartOfSpeech(JSFExplanationDTO.getPartOfSpeech());
		explanationToUpdate.setExplanation(JSFExplanationDTO.getMeaning());
		return explanationToUpdate;
	}

	public boolean hasExplanations() {
		return !explanationDTOs.isEmpty();
	}
}
