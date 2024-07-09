package rx.dictionary.ui.jsf.add;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import rx.dictionary.ui.jsf.addorupdate.AddOrUpdateInputComponent;

@RequestScoped
@Named
public class AddComponent extends AddOrUpdateInputComponent {
	/*
	@Inject
	private ExplanationsService explanationsService;
	@PostConstruct
	public void addExplanationPlaceholder() {
		if (explanationDTOs.isEmpty())
			explanationDTOs.add(JSFExplanationDTO.empty());
	}
		
	public void add() {
		LexicalItem newLexicalItem = new LexicalItem();
		newLexicalItem.setLanguage(super.language);
		newLexicalItem.setValue(getWord());
		List<Explanation> newMeanings = new ArrayList<>();
		explanationDTOs.forEach(e -> {
			Explanation newMeaning = new Explanation();
			newMeaning.setPartOfSpeech(e.getPartOfSpeech());
			newMeaning.setLanguage(getExplainLanguage());
			newMeaning.setExplanation(e.getMeaning());
			newMeanings.add(newMeaning);
		});
		explanationsService.add(newMeanings);
	}
	*/
}