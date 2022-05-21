package rx.dictionary.ui.jsf.addorupdate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.IntStream;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import rx.dictionary.application.Add;
import rx.dictionary.jpaentity.Definition;
import rx.dictionary.jpaentity.Entry;
import rx.dictionary.jpaentity.EntryValue;
import rx.dictionary.ui.jsf.CommonComponent;

@RequestScoped
@Named
public class AddComponent extends AbstractFillInComponent {
	private Locale sourceLanguage;
	private String entryValue;
	private Locale explainLanguage;
	@Inject
	private Add addService;
	public AddComponent() {
		explanations = new ArrayList<>();
		explanations.add(new ExplanationComponent(true));
		IntStream.range(0, 5).forEach(e -> explanations.add(new ExplanationComponent()));
	}
	
	public Locale getSourceLanguage() {
		return sourceLanguage;
	}


	public void setSourceLanguage(Locale sourceLanguage) {
		this.sourceLanguage = sourceLanguage;
	}
	
	public Map<String,String> getLanguageMap() {
		return CommonComponent.FRONTEND_LANGUAGE_OPTIONS;
	}


	public String getEntryValue() {
		return entryValue;
	}
	public void setEntryValue(String entryValue) {
		this.entryValue = entryValue;
	}

	public Locale getExplainLanguage() {
		return explainLanguage;
	}

	public void setExplainLanguage(Locale explainLanguage) {
		this.explainLanguage = explainLanguage;
	}
	
	private List<Definition> getDefinitions() {
		EntryValue entryValue = new EntryValue();
		entryValue.setEntry(this.entryValue);
		entryValue.setLanguage(sourceLanguage);
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
				def.setLanguage(explainLanguage);
				definitions.add(def);
			}
		}
		return definitions;
		
	}
	
	public void action() {
		addService.add(getDefinitions());
	}
	
}