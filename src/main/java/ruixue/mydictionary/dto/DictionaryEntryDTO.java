package ruixue.mydictionary.dto;

import java.util.List;
import java.util.Map;

public class DictionaryEntryDTO {
	private String language;
	private String word;
	private String explainedLanguage;
	private Map<String, List<String>> explanations;
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
	public Map<String, List<String>> getExplanations() {
		return explanations;
	}
	public String getExplainedLanguage() {
		return this.explainedLanguage;
	}
}
