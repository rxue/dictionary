package ruixue.mydictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ruixue.mydictionary.entity.ExplanationMapping;
import ruixue.mydictionary.entity.Language;
import ruixue.mydictionary.entity.Word;
import ruixue.mydictionary.repository.RepositoryExplanationMapping;
import ruixue.mydictionary.repository.RepositoryLanguage;
import ruixue.mydictionary.repository.RepositoryWord;
@Service
public class ExplanationMappingService {
	
	@Autowired
	private RepositoryExplanationMapping repositoryExplanationMapping;
	
	@Autowired
	private RepositoryWord repositoryWord;
	
	@Autowired
	private RepositoryLanguage repositoryLanguage;
	
	public ExplanationMapping getExplanationMappingByWordAndLanguageCode(String languageCode, String explanationLanguageCode, String word) {
		Language language = repositoryLanguage.findByLanguageCodeIgnoreCase(languageCode);
		Language explanationLanguage = repositoryLanguage.findByLanguageCodeIgnoreCase(explanationLanguageCode);
		Word pertinentWord = repositoryWord.findByLanguageAndWord(language, word);
		return repositoryExplanationMapping.findByWordAndExplanationLanguage(pertinentWord, explanationLanguage);
	}
}