package rx.dictionary.ui.jsf.update;


import java.io.Serializable;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import rx.dictionary.ui.jsf.addorupdate.AddOrUpdateInputComponent;

@Named
@ViewScoped
public class UpdateComponent extends AddOrUpdateInputComponent implements Serializable {
	/*
	@Inject
	private ExplanationsService explanationsService;
	private List<Explanation> explanations = Collections.emptyList();
	public void search() {
		LexicalItemVO itemVal = new LexicalItemVO(getLanguage(), super.getWord());
		explanations = explanationsService.find(itemVal, getExplainLanguage());
		explanations.forEach(m -> explanationDTOs.add(new JSFExplanationDTO(m.getPartOfSpeech(), m.getExplanation())));
	}
	public void update() {
		int i = 0;
		for (JSFExplanationDTO explanationComp : explanationDTOs) {
			updateExplanation(explanations.get(i++), explanationComp);
		}
		explanationsService.update(explanations);
	}
	static Explanation updateExplanation(Explanation explanationToUpdate, JSFExplanationDTO JSFExplanationDTO) {
		explanationToUpdate.setPartOfSpeech(JSFExplanationDTO.getPartOfSpeech());
		explanationToUpdate.setExplanation(JSFExplanationDTO.getMeaning());
		return explanationToUpdate;
	}

	public boolean hasExplanations() {
		return !explanationDTOs.isEmpty();
	}
	*/
}
