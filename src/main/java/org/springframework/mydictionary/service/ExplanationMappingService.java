package org.springframework.mydictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mydictionary.repository.RepositoryExplanationMapping;
import org.springframework.mydictionary.repository.RepositoryLanguage;
import org.springframework.mydictionary.repository.RepositoryWord;
import org.springframework.stereotype.Service;
import org.springframework.mydictionary.entity.ExplanationMapping;
import org.springframework.mydictionary.entity.Language;
import org.springframework.mydictionary.entity.Word;
@Service
public class ExplanationMappingService {
	
	@Autowired
	private RepositoryExplanationMapping repositoryExplanationMapping;
	
	@Autowired
	private RepositoryWord repositoryWord;
	
	@Autowired
	private RepositoryLanguage repositoryLanguage;
	
	public ExplanationMapping getExplanationMappingByWordAndLanguageCode(String languageCode, String word, String explanationLanguageCode) {
		Language language = repositoryLanguage.findByLanguageCodeIgnoreCase(languageCode);
		Language explanationLanguage = repositoryLanguage.findByLanguageCodeIgnoreCase(explanationLanguageCode);
		Word pertinentWord = repositoryWord.findByLanguageAndWord(language, word);
		return repositoryExplanationMapping.findByWordAndExplanationLanguage(pertinentWord, explanationLanguage);
	}
}