package ruixue.mydictionary.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Immutable DTO
 * 
 * @author dingding
 *
 */
public final class DictionaryEntryDTO {
	private final String language;
	private final String word;
	private final String explainedLanguage;
	private final Map<String, List<String>> explanations;
	//DTO design pattern
	public DictionaryEntryDTO(String language, String word, String explainedLanguage, Map<String, List<String>> explanations) {
		this.language = language;
		this.word = word;
		this.explainedLanguage = explainedLanguage;
		this.explanations = explanations;
	}
	public String getLanguage() {
		return language;
	}
	public String getWord() {
		return word;
	}
	public String getExplainedLanguage() {
		return explainedLanguage;
	}
	public Map<String, List<String>> getExplanations() {
		return new HashMap<>(explanations);
	}
}
