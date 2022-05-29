package rx.dictionary.ui.jsf.addorupdate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import rx.dictionary.ExplanationRepository;
import rx.dictionary.jpaentity.Explanation;
import rx.dictionary.jpaentity.ItemValue;
import rx.dictionary.jpaentity.LexicalItem;

@RequestScoped
@Named
public class AddComponent extends CompleteInputComponent {
	@Inject
	ExplanationRepository definitionRepo;
	private List<ExplanationComponent> explanationComponents;
	public AddComponent() {
		explanationComponents = new ArrayList<>();
		explanationComponents.add(new ExplanationComponent(true));
		IntStream.range(0, 5).forEach(e -> explanationComponents.add(new ExplanationComponent()));
	}
	
	private List<Explanation> getDefinitions() {
		ItemValue itemValue = new ItemValue();
		itemValue.setValue(this.getWord());
		itemValue.setLanguage(getFromLanguage());
		List<Explanation> explanations = new ArrayList<>();
		for (ExplanationComponent explanationComp : explanationComponents) {
			String explanation = explanationComp.getExplanation();
			if (!StringUtils.isEmpty(explanation)) {
				System.out.println("explanation: " + explanation);
				Explanation newExplanation = new Explanation();
				newExplanation.setExplanation(explanation);
				LexicalItem item = new LexicalItem();
				item.setPoS(explanationComp.getPartOfSpeech());
				item.setItemValue(itemValue);
				newExplanation.setLexicalItem(item);
				newExplanation.setLanguage(getToLanguage());
				explanations.add(newExplanation);
			}
		}
		return explanations;
		
	}
	
	public void action() {
		definitionRepo.add(getDefinitions());
	}
	
}