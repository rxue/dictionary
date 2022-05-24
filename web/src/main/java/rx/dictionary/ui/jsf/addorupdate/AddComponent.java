package rx.dictionary.ui.jsf.addorupdate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import rx.dictionary.DefinitionRepository;
import rx.dictionary.jpaentity.Definition;
import rx.dictionary.jpaentity.Entry;
import rx.dictionary.jpaentity.EntryValue;

@RequestScoped
@Named
public class AddComponent extends CompleteInputComponent {
	@Inject
	DefinitionRepository definitionRepo;
	private List<ExplanationComponent> explanations;
	public AddComponent() {
		explanations = new ArrayList<>();
		explanations.add(new ExplanationComponent(true));
		IntStream.range(0, 5).forEach(e -> explanations.add(new ExplanationComponent()));
	}
	
	private List<Definition> getDefinitions() {
		EntryValue entryValue = new EntryValue();
		entryValue.setEntry(this.getWord());
		entryValue.setLanguage(getFromLanguage());
		List<Definition> definitions = new ArrayList<>();
		for (ExplanationComponent explanationComp : explanations) {
			String explanation = explanationComp.getExplanation();
			if (!StringUtils.isEmpty(explanation)) {
				System.out.println("explanation: " + explanation);
				Definition def = new Definition();
				def.setDefinition(explanation);
				Entry entry = new Entry();
				entry.setPoS(explanationComp.getPartOfSpeech());
				entry.setValue(entryValue);
				def.setEntry(entry);
				def.setLanguage(getToLanguage());
				definitions.add(def);
			}
		}
		return definitions;
		
	}
	
	public void action() {
		definitionRepo.add(getDefinitions());
	}
	
}