package rx.dictionary.ui.jsf.addorupdate;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import rx.dictionary.jpaentity.PartOfSpeech;
import rx.dictionary.ui.jsf.InputComponent;

public abstract class AddOrUpdateInputComponent extends InputComponent {
	public final Map<String,String> getLanguageNameToTag() {
		return CommonComponent.getLanguageNameToTag();
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

}
