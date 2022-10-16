package rx.dictionary.ui.jsf.addorupdate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import rx.dictionary.DictionaryService;
import rx.dictionary.SearchKeyword;
import rx.dictionary.jpaentity.Explanation;
import rx.dictionary.jpaentity.LexicalItem;

@RequestScoped
@Named
public class AddComponent extends CompleteInputComponent {
	@Inject
	DictionaryService dictionaryService;
	public AddComponent() {
		super();
	}
	
	private List<Explanation> getDefinitions() {
		SearchKeyword itemValue = new SearchKeyword(super.getKeyword(), super.getFromLanguage());
		List<Explanation> explanations = new ArrayList<>();
		List<LexicalItem> newLexicalItems = new ArrayList<>();
		for (ExplanationComponent explanationComp : getExplanations()) {
			String explanation = explanationComp.getExplanation();
			if (!StringUtils.isEmpty(explanation)) {
				System.out.println("explanation: " + explanation);
				Explanation newExplanation = new Explanation();
				newExplanation.setExplanation(explanation);
				LexicalItem item = new LexicalItem();
				item.setPoS(explanationComp.getPartOfSpeech());
				item.setValue(this.getKeyword());
				item.setLanguage(getFromLanguage());
				Optional<LexicalItem> existingItem = newLexicalItems.stream().filter(li -> li.equals(item))
						.findAny();
				if (existingItem.isPresent()) {
					System.out.println("add existing item!!!!!!!!!!!!!!!!!");
					newExplanation.setLexicalItem(existingItem.get());
				} else {
					newExplanation.setLexicalItem(item);
					newLexicalItems.add(item);
				}
				newExplanation.setLanguage(getToLanguage());
				explanations.add(newExplanation);
			}
		}
		return explanations;
		
	}
	
	public void action() {
		dictionaryService.add(getDefinitions());
	}
	
}