package ruixue.mydictionary.dto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ruixue.mydictionary.entity.Explanation;
import ruixue.mydictionary.entity.ExplanationMapping;

public class DictionaryEntryDTO {
	private String language;
	private String word;
	private String explainedLanguage;
	private Map<String, List<String>> explanations;
	//DTO design pattern
	public DictionaryEntryDTO(ExplanationMapping explanationMapping) {
		this (explanationMapping.getWord().getLanguage().getLanguage(),
				explanationMapping.getWord().getWord(),
				explanationMapping.getExplanationLanguage().getLanguage(),
				explanationMapping.getExplanations().stream().collect(
						Collectors.groupingBy(
								e -> e.getPartOfSpeech().getPartOfSpeech(), 
								Collectors.mapping(Explanation::getExplanation, Collectors.toList())
						)));
	}
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
