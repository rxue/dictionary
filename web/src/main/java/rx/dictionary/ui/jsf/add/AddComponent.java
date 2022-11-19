package rx.dictionary.ui.jsf.add;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


import rx.dictionary.DictionaryService;
import rx.dictionary.jpaentity.Explanation;
import rx.dictionary.jpaentity.LexicalItem;
import rx.dictionary.jpaentity.PartOfSpeech;
import rx.dictionary.ui.jsf.addorupdate.AddOrUpdateInputComponent;
import rx.dictionary.ui.jsf.addorupdate.ExplanationDTO;

@RequestScoped
@Named
public class AddComponent extends AddOrUpdateInputComponent {
	@Inject
	DictionaryService dictionaryService;
	private List<ExplanationDTO> explanationDTOs;
	public AddComponent() {
		explanationDTOs = new ArrayList<>();
		explanationDTOs.add(ExplanationDTO.empty());
	}
	
	public List<ExplanationDTO> getExplanationDTOs() {
		return explanationDTOs;
	}
	
	public void add() {
		List<Explanation> newMeanings = new ArrayList<>();
		List<LexicalItem> lexicalItems = new ArrayList<>();
		explanationDTOs.forEach(e -> {
			Optional<LexicalItem> optMatchedLexicalItem = get(lexicalItems, getWord(), e.getPartOfSpeech());
			Explanation newMeaning = new Explanation();
			if (optMatchedLexicalItem.isPresent()) {
				newMeaning.setLexicalItem(optMatchedLexicalItem.get());
			} else {
				LexicalItem newLexicalItem = new LexicalItem();
				newLexicalItem.setLanguage(super.language);
				newLexicalItem.setValue(getWord());
				newLexicalItem.setPoS(e.getPartOfSpeech());
				newMeaning.setLexicalItem(newLexicalItem);
				lexicalItems.add(newLexicalItem);
			}
			newMeaning.setLanguage(getExplainLanguage());
			newMeaning.setExplanation(e.getMeaning());
			newMeanings.add(newMeaning);
			
		});
		dictionaryService.add(newMeanings);
	}
	private static Optional<LexicalItem> get(List<LexicalItem> existingLexicalItems, String value, PartOfSpeech partOfSpeech) {
		return existingLexicalItems.stream()
				.filter(e -> partOfSpeech.equals(e.getPoS()))
				.findFirst();
						
	}
	
}