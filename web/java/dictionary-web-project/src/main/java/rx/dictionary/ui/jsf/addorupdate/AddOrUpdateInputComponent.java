package rx.dictionary.ui.jsf.addorupdate;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import rx.dictionary.jpaentity.PartOfSpeech;
import rx.dictionary.ui.jsf.InputComponent;

public abstract class AddOrUpdateInputComponent extends InputComponent {
	private static final Map<String,String> LANGUAGE_NAME_TO_TAG;
	static {
		Map<String,String> result = new HashMap<>();
		result.put("繁體中文", Locale.TRADITIONAL_CHINESE.toLanguageTag());
		result.put("简体中文", Locale.SIMPLIFIED_CHINESE.toLanguageTag());
		result.put("English", Locale.ENGLISH.toLanguageTag());
		result.put("suomi", new Locale("fi").toLanguageTag());
		result.put("français", Locale.FRENCH.toLanguageTag());
		LANGUAGE_NAME_TO_TAG = result;
	}
	@Inject
	protected ExplanationsComponent explanationDTOs;
	public final Map<String,String> getLanguageNameToTag() {
		return LANGUAGE_NAME_TO_TAG;
	}
	public final Map<String,String> getPartOfSpeechOptions() {
		PartOfSpeech[] partOfSpeeches = PartOfSpeech.values();
		Map<String,String> result = new HashMap<>();
		for (PartOfSpeech partOfSpeech : partOfSpeeches) {
			result.put(partOfSpeech.name(), partOfSpeech.name());
		}
		return result;
	}
	public final void setLanguage(Locale language) {
		super.language = language;
	}
	public final Locale getLanguage() {
		return super.language;
	}
	
	public final void setExplainLanguage(Locale explainLanguage) {
		super.explainLanguage = explainLanguage;
	}
	public final Locale getExplainLanguage() {
		return super.explainLanguage;
	}
	public final void setExplanationDTOs(ExplanationsComponent explanationDTOs) {
		this.explanationDTOs = explanationDTOs;
	}
	public final ExplanationsComponent getExplanationDTOs() {
		return explanationDTOs;
	}
	
}
