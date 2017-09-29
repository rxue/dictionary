package ruixue.mydictionary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ruixue.mydictionary.dto.WordDTO;
import ruixue.mydictionary.entity.Language;
import ruixue.mydictionary.entity.Word;
import ruixue.mydictionary.repository.RepositoryLanguage;
import ruixue.mydictionary.repository.RepositoryWord;

//import ruixue.mydictionary.entity.Word;
//import ruixue.mydictionary.repository.RepositoryWord;
@Service
public class WordService {
	@Autowired
	private RepositoryLanguage repositoryLanguage;
	@Autowired
	private RepositoryWord repositoryWord;
	public Word insertWord(String languageCode, WordDTO wordDTO) {
		Language language = repositoryLanguage.findByLanguageCodeIgnoreCase(languageCode);
		if (language == null) return null;
		Word word = new Word();
		word.setLanguage(language);
		word.setWord(wordDTO.getWord());
		return repositoryWord.save(word);
	}
	public List<Word> getAllWords() {
		return repositoryWord.findAll();
	}
	public Word deleteWord(String languageCode, String wordString) {
		Language language = repositoryLanguage.findByLanguageCodeIgnoreCase(languageCode);
		Word word = repositoryWord.findByLanguageAndWord(language, wordString);
		if (word != null)
			repositoryWord.delete(word);
		return word;
	}
}
